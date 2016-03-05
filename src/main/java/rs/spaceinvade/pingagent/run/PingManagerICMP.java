package rs.spaceinvade.pingagent.run;

public class PingManagerICMP extends CommandLauncher {

	public PingManagerICMP(Agent callingAgent, String host) {
		super(callingAgent,host);
		setProcessBuilder(new ProcessBuilder("ping", "-n", "-c 5", host));
	}


}
