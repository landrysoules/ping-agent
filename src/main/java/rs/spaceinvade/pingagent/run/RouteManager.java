package rs.spaceinvade.pingagent.run;

public class RouteManager extends CommandLauncher {

	public RouteManager(String host) {
		super(host);
		setProcessBuilder(new ProcessBuilder("traceroute", host));
	}
}
