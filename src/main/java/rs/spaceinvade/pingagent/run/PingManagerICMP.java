package rs.spaceinvade.pingagent.run;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PingManagerICMP extends CommandLauncher {
	private static Logger logger = Logger.getLogger(PingManagerICMP.class.getName());
	public PingManagerICMP(){
		super();
	}

	public PingManagerICMP(Agent callingAgent, String host) {
		super(callingAgent,host);
		setProcessBuilder(new ProcessBuilder("ping", "-n", "-c 5", host));
	}

	@Override
	public void analyzeResponse(String response) {
		String packetLossRegex = "(\\d+)\\%\\spacket\\sloss";
		Pattern pattern = Pattern.compile(packetLossRegex, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(response);
		if(matcher.find()){
			logger.info(matcher.group(0));
			if(! matcher.group(1).equals("0")){
				sendReport();
			}
		}else{
			sendReport();
		}
		
	}

}
