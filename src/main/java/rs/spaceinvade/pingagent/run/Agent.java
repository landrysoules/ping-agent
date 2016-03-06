package rs.spaceinvade.pingagent.run;

import rs.spaceinvade.pingagent.config.ConfigManager;

/**
 * Launches the various processes.
 * @author Landry Soules
 *
 */
public interface Agent {

	public ConfigManager getConfigManager();
	public void setConfigManager(ConfigManager configManager);
	public void launchAllProcesses();
	
	/**
	 * Calls report generation for given host.
	 * @param host
	 */
	public void report(String host);
}
