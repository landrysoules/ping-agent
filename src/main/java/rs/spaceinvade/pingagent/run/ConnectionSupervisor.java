package rs.spaceinvade.pingagent.run;

import java.io.IOException;

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
	public void setLastResult(String lastResult);
	
	public String getLastResult();
	
	/**
	 * Send report.
	 */
	public void sendReport();

	public Agent getCallingAgent();

	void setCallingAgent(Agent callingAgent);

	String getHost();

	void setHost(String host);
	
	

}
