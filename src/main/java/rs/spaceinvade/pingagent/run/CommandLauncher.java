package rs.spaceinvade.pingagent.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

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

	@Override
	public BufferedReader sendInstruction() throws Exception {
		processBuilder.redirectErrorStream(true);
		Process p = processBuilder.start();
		bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		return bufferedReader;
	}

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

}
