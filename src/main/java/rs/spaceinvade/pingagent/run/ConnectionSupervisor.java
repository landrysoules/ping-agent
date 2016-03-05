package rs.spaceinvade.pingagent.run;

import java.io.IOException;
import java.util.Properties;

import rs.spaceinvade.pingagent.config.ConfigManager;

/**
 * In charge of executign pinging operations.
 * @author Landry Soules
 *
 */
public interface ConnectionSupervisor extends Runnable{

	/**
	 * Run ping command.
	 */
	public String runCommand() throws IOException;

	/**
	 * Store result of last operation.
	 */
	public void storeResult();
	
	/**
	 * Send report.
	 */
	public void sendReport();

}
