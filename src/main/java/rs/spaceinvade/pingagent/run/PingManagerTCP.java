package rs.spaceinvade.pingagent.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class PingManagerTCP implements ConnectionSupervisor {

	private static Logger log = Logger.getLogger(PingManagerTCP.class.getName());

	@Override
	public String runCommand() throws IOException {
		try {
			URL url = new URL("http://spaceinvade.rs");

			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
			HttpURLConnection.setFollowRedirects(false);
			huc.setConnectTimeout(5 * 1000);
			huc.setRequestMethod("GET");
			huc.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
			long startTime = System.currentTimeMillis();
			huc.connect();
			InputStream input = huc.getInputStream();
			long timeElapsed = System.currentTimeMillis() - startTime;
			log.info(""+huc.getResponseCode());
			System.out.println("Time elapsed: " + timeElapsed);

			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String strTemp = "";
			br.lines().forEach(System.out::println);
			return br.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
