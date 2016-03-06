package rs.spaceinvade.pingagent.run;

/**
 * Implemenation dealing with TraceRoute ping.
 * @author Landry Soules
 *
 */
public class RouteManager extends CommandLauncher {
	
	public RouteManager(Agent callingAgent, String host) {
		super(callingAgent, host);
		setAlias("trace");
		setProcessBuilder(new ProcessBuilder("traceroute", host));
	}

}
