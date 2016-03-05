package rs.spaceinvade.pingagent.run;

import rs.spaceinvade.pingagent.config.ConfigManager;

public interface Agent {

	public ConfigManager getConfigManager();
	public void setConfigManager(ConfigManager configManager);
	public void launchAllProcesses();
}
