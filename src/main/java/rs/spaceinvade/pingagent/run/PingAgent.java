package rs.spaceinvade.pingagent.run;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rs.spaceinvade.pingagent.config.ConfigManager;

/**
 * In charge of running the processes, and sending reports.
 * @author Landry Soules
 *
 */
public class PingAgent implements Agent {

	private static Logger logger = Logger.getLogger(PingAgent.class.getName());
	
	private ConfigManager configManager;

	/**
	 * Map grouping supervisors by host.
	 */
	private Map<String, List<ConnectionSupervisor>> supervisors = new HashMap<String, List<ConnectionSupervisor>>();

	/**
	 * Adds provided supervisor to supervisors' map.
	 * @param supervisor
	 */
	private synchronized void addSupervisor(ConnectionSupervisor supervisor) {
		List<ConnectionSupervisor> storedSupervisors = supervisors.get(supervisor.getHost());
		if (storedSupervisors == null) {
			storedSupervisors = new ArrayList<ConnectionSupervisor>();
			supervisors.put(supervisor.getHost(), storedSupervisors);
		}
		storedSupervisors.add(supervisor);
	}

	public PingAgent(ConfigManager configManager) {
		this.configManager = configManager;
	}

	@Override
	public ConfigManager getConfigManager() {
		return configManager;
	}

	@Override
	public void setConfigManager(ConfigManager configManager) {
		this.configManager = configManager;
	}

	/**
	 * Launch all the processes, for every host specified. Each supervisor is added to supervisors' map.
	 */
	@Override
	public void launchAllProcesses() {
		FileHandler fileHandler;
		try {
			fileHandler = new FileHandler(configManager.getProperties().getProperty("log.file"));
			logger.addHandler(fileHandler);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(8);
		int initialDelay = 0;

		String[] hosts = configManager.getProperties().getProperty("hosts").split(",");
		for (String host : hosts) {
			ConnectionSupervisor pingManagerICMP = new PingManagerICMP(this, host);
			ConnectionSupervisor pingManagerTCP = new PingManagerTCP(this, host);
			ConnectionSupervisor routeManager = new RouteManager(this, host);
			executor.scheduleWithFixedDelay(pingManagerICMP, initialDelay,
					Long.parseLong(configManager.getProperties().getProperty("ping.icmp.interval")),
					TimeUnit.MILLISECONDS);
			executor.scheduleWithFixedDelay(pingManagerTCP, initialDelay,
					Long.parseLong(configManager.getProperties().getProperty("ping.http.interval")),
					TimeUnit.MILLISECONDS);
			executor.scheduleWithFixedDelay(routeManager, initialDelay,
					Long.parseLong(configManager.getProperties().getProperty("trace.interval")), TimeUnit.MILLISECONDS);
			addSupervisor(pingManagerICMP);
			addSupervisor(pingManagerTCP);
			addSupervisor(routeManager);
		}
	}

	/**
	 * Creates the report and call sending function.
	 */
	@Override
	public void report(String host) {
		
		// get all connectionSupervisors for calling host
		Map<String, String> reportMap = new HashMap<String, String>();
		reportMap.put("host", host);
		supervisors.get(host).forEach((supervisor) -> {
			reportMap.put(supervisor.getAlias(), supervisor.formatReportActivity());
		});
		String jsonReport = new Gson().toJson(reportMap);
		logger.warning(jsonReport);
		try {
			sendReport(jsonReport);
		} catch (Exception e) {
		logger.log(Level.SEVERE, e.getMessage(),e);
		}
	}

	/**
	 * Send the report
	 * @param jsonBody
	 * JSON formatted String
	 * @throws Exception
	 * Any exception occuring while sending the report.
	 */
	public void sendReport(String jsonBody) throws Exception {
		final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		String reportURL = getConfigManager().getProperties().getProperty("report.url");

		OkHttpClient client = new OkHttpClient();

		RequestBody body = RequestBody.create(JSON, jsonBody);
		Request request = new Request.Builder().url(reportURL).post(body).build();
		Response response = client.newCall(request).execute();
	}

}
