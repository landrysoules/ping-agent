package rs.spaceinvade.pingagent.run;

public class RouteManager extends CommandLauncher {

	public RouteManager(Agent callingAgent, String host) {
		super(callingAgent, host);
		setProcessBuilder(new ProcessBuilder("traceroute", host));
	}
}
