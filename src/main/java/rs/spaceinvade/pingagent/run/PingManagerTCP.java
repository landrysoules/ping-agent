package rs.spaceinvade.pingagent.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Implementation dealing with TCP ping.
 * @author Landry Soules
 *
 */
public class PingManagerTCP extends SimpleConnectionSupervisor {

	private static Logger logger = Logger.getLogger(PingManagerTCP.class.getName());
	
	public PingManagerTCP() {
		super();
	}

	public PingManagerTCP(Agent callingAgent, String host) {
		super(callingAgent, host);
		setAlias("tcp_ping");
	}

	/**
	 * This implementation doesn't call a CLI program but instead provides necessary code to perform HTTP request.
	 */
	@Override
	public BufferedReader sendInstruction() throws Exception {
		URL url = new URL("http://" + getHost());

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// HttpURLConnection.setFollowRedirects(false);
		connection.setConnectTimeout(Integer
				.parseInt(getCallingAgent().getConfigManager().getProperties().getProperty("ping.http.timeout")));
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36");
		long startTime = System.currentTimeMillis();
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;
		String connectionSummary = connection.getResponseCode() + ":" + timeElapsed;
		BufferedReader bufferedReader = new BufferedReader(new StringReader(connectionSummary));
		return bufferedReader;
	}

	@Override
	public String formatResponse(BufferedReader bufferedReader) throws IOException {

		String response = bufferedReader.readLine();
		logger.warning(response);
		return response;
	}

	@Override
	public void analyzeResponse(String response) {
		String[] resp = response.split(":");
		if (!resp[0].equals("200")) {
			sendReport();
			return;
		}
		Long maxResponseTime = Long.parseLong(
				getCallingAgent().getConfigManager().getProperties().getProperty("ping.http.max.response.time"));
		Long responseTime = Long.parseLong(resp[1]);
		if (responseTime > maxResponseTime) {
			sendReport();
		}
	}

	@Override
	public String formatReportActivity() {
		String lastResult = getLastResult();
		String[] splittedResult = lastResult.split(":");
		String formattedResult = "Response code: " + splittedResult[0] + " - " + "Connection time: "
				+ splittedResult[1];
		return formattedResult;
	}
}
