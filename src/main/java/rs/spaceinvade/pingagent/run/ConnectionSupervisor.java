package rs.spaceinvade.pingagent.run;

/**
 * In charge of executign pinging operations. Base interface for every supervisor, being a program launcher or a custom supervisor.
 * @author Landry Soules
 *
 */
public interface ConnectionSupervisor extends Runnable{

	/**
	 * Run ping command.
	 */
	public void runCommand();

	
	
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

	public void setCallingAgent(Agent callingAgent);

	public String getHost();

	public void setHost(String host);
	
	public String formatReportActivity();
	
	public String getAlias();
	
	public void setAlias(String alias);
	
}
