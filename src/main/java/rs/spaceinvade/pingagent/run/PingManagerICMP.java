package rs.spaceinvade.pingagent.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;

import rs.spaceinvade.pingagent.config.ConfigManager;
import rs.spaceinvade.pingagent.config.ConfigManagerImpl;

public class PingManagerICMP implements ConnectionSupervisor {

	public PingManagerICMP(String[] cliParams) {
		ConfigManager configManager = new ConfigManagerImpl();
		// configManager.setCliParams(cliParams);
	}

	@Override
	public String runCommand() throws IOException {

		ProcessBuilder pb = new ProcessBuilder("ping", "-n", "-c 5", "spaceinvade.rs");
		pb.redirectErrorStream(true);
		Process p = pb.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String line = null;
		final String LINE_SEP = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(LINE_SEP);
		}
		String result = builder.toString();
		// TODO: manage return code
		assert p.getInputStream().read() == -1;
		return result;
	}

}
