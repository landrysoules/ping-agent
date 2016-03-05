package rs.spaceinvade.pingagent;

import java.util.logging.Level;
import java.util.logging.Logger;

import rs.spaceinvade.pingagent.config.ConfigManager;
import rs.spaceinvade.pingagent.config.ConfigManagerImpl;
import rs.spaceinvade.pingagent.exception.PingAgentException;
import rs.spaceinvade.pingagent.run.Agent;
import rs.spaceinvade.pingagent.run.PingAgent;

/**
 * PingAgent entry point.
 *
 */
public class App {
	
	private static Logger logger = Logger.getLogger(App.class.getName());
	
	public static void main(String[] args) {
		ConfigManager configManager = new ConfigManagerImpl(args);
		try {
			configManager.loadConfig();
			Agent pingAgent = new PingAgent(configManager);
			pingAgent.launchAllProcesses();
		} catch (PingAgentException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
		
	}
}
