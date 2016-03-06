package rs.spaceinvade.pingagent.run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import rs.spaceinvade.pingagent.config.ConfigManager;

public class PingAgent implements Agent {
	
	private ConfigManager configManager;
	
	private Map<String,List<ConnectionSupervisor>> supervisors = new HashMap<String,List<ConnectionSupervisor>>();
	
	private synchronized void addSupervisor(ConnectionSupervisor supervisor){
		List<ConnectionSupervisor> storedSupervisors = supervisors.get(supervisor.getHost());
		if(storedSupervisors == null){
			storedSupervisors = new ArrayList<ConnectionSupervisor>();
			supervisors.put(supervisor.getHost(), storedSupervisors);
		}
		storedSupervisors.add(supervisor);
	}
	
	public PingAgent(ConfigManager configManager){
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

	@Override
	public void launchAllProcesses() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(8);
		int initialDelay = 0;
		
		String[]hosts = configManager.getProperties().getProperty("hosts").split(",");
		for(String host:hosts){
			ConnectionSupervisor pingManagerICMP = new PingManagerICMP(this, host);
			ConnectionSupervisor pingManagerTCP = new PingManagerTCP(this, host);
			ConnectionSupervisor routeManager = new RouteManager(this, host);
			executor.scheduleWithFixedDelay(pingManagerICMP, initialDelay, Long.parseLong(configManager.getProperties().getProperty("ping.icmp.interval")), TimeUnit.MILLISECONDS);
			executor.scheduleWithFixedDelay(pingManagerTCP, initialDelay, Long.parseLong(configManager.getProperties().getProperty("ping.http.interval")), TimeUnit.MILLISECONDS);
			executor.scheduleWithFixedDelay(routeManager, initialDelay, Long.parseLong(configManager.getProperties().getProperty("trace.interval")), TimeUnit.MILLISECONDS);
			addSupervisor(pingManagerICMP);
			addSupervisor(pingManagerTCP);
			addSupervisor(routeManager);
		}
	}

	@Override
	public void report(String host) {
		// TODO Auto-generated method stub
		
	}



}
