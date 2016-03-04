package rs.spaceinvade.pingagent.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RouteManager implements ConnectionSupervisor {

	@Override
	public String runCommand() throws IOException {
		ProcessBuilder pb = new ProcessBuilder("traceroute", "spaceinvade.rs");
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
