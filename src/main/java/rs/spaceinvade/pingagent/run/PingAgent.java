package rs.spaceinvade.pingagent.run;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import rs.spaceinvade.pingagent.config.ConfigManager;

public class PingAgent implements Agent {
	
	private ConfigManager configManager;
	
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
		int initialDelay = 2;
		int period = 3;
		

		String[]hosts = configManager.getProperties().getProperty("hosts").split(",");
		for(String host:hosts){
			ConnectionSupervisor pingManagerICMP = new PingManagerICMP(host);
			executor.scheduleWithFixedDelay(pingManagerICMP, initialDelay, period, TimeUnit.SECONDS);
		}
	}



}
