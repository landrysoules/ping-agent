package rs.spaceinvade.pingagent.run;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation dealing with ICMP ping.
 * @author Landry Soules
 *
 */
public class PingManagerICMP extends CommandLauncher {
	private static Logger logger = Logger.getLogger(PingManagerICMP.class.getName());
	
	public PingManagerICMP(){
		super();
	}

	public PingManagerICMP(Agent callingAgent, String host) {
		super(callingAgent,host);
		setAlias("icmp_ping");
		setProcessBuilder(new ProcessBuilder("ping", "-n", "-c 5", host));
	}
}
