package rs.spaceinvade.pingagent.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Specific ConnectionSupervisor implementation. For use by supervisors which
 * basically call a command-line program such as ping or traceroute.
 * 
 * @author Landry Soules
 *
 */
public abstract class CommandLauncher extends SimpleConnectionSupervisor {

	private static Logger logger = Logger.getLogger(CommandLauncher.class.getName());

	private ProcessBuilder processBuilder;
	private BufferedReader bufferedReader;
	private StringBuilder stringBuilder;

	public CommandLauncher() {
		super();
	}

	public CommandLauncher(Agent callingAgent, String host) {
		super(callingAgent, host);
	}

	public ProcessBuilder getProcessBuilder() {
		return processBuilder;
	}

	public void setProcessBuilder(ProcessBuilder processBuilder) {
		this.processBuilder = processBuilder;
	}

	/**
	 * Executes ping program.
	 */
	@Override
	public BufferedReader sendInstruction() throws Exception {
		processBuilder.redirectErrorStream(true);
		Process p = processBuilder.start();
		bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		return bufferedReader;
	}

	/**
	 * Format output from ping program into String.
	 */
	@Override
	public String formatResponse(BufferedReader bufferedReader) throws IOException {
		stringBuilder = new StringBuilder();
		String line = null;
		final String LINE_SEP = System.getProperty("line.separator");
		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(LINE_SEP);
		}
		return stringBuilder.toString();
	}

	/**
	 * Checks if result returned from ping program is in error.
	 */
	@Override
	public void analyzeResponse(String response) {
		String packetLossRegex = "(\\d+)\\%\\spacket\\sloss";
		Pattern pattern = Pattern.compile(packetLossRegex, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(response);
		if (matcher.find()) {
			logger.info(matcher.group(0));
			if (!matcher.group(1).equals("0")) {
				sendReport();
			}
		} else {
			sendReport();
		}
	}

}
