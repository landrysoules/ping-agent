package rs.spaceinvade.pingagent.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import rs.spaceinvade.pingagent.config.ConfigManager;
import rs.spaceinvade.pingagent.config.ConfigManagerTest;

public class CommandLauncher extends SimpleConnectionSupervisor {

	private static Logger logger = Logger.getLogger(CommandLauncher.class.getName());
	
	public CommandLauncher(String host) {
		super(host);
	}

	private ProcessBuilder processBuilder;
	
	public ProcessBuilder getProcessBuilder() {
		return processBuilder;
	}

	public void setProcessBuilder(ProcessBuilder processBuilder) {
		this.processBuilder = processBuilder;
	}

	@Override
	public String runCommand() throws IOException {
		processBuilder.redirectErrorStream(true);
		Process p = processBuilder.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String line = null;
		final String LINE_SEP = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(LINE_SEP);
		}
		String result = builder.toString();
		logger.warning(result);
		// TODO: manage return code
		assert p.getInputStream().read() == -1;
		return result;
	}
	
	@Override
	public void run() {
		try {
			runCommand();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
