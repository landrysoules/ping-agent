package rs.spaceinvade.pingagent.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class PingManagerTCP extends SimpleConnectionSupervisor {

	private static Logger logger = Logger.getLogger(PingManagerTCP.class.getName());
	
	public PingManagerTCP(Agent callingAgent,String host) {
		super(callingAgent,host);
	}

	@Override
	public BufferedReader sendInstruction() throws Exception {
		URL url = new URL("http://" + getHost());

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		HttpURLConnection.setFollowRedirects(false);
		connection.setConnectTimeout(5 * 1000); //FIXME: parameterize
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36");
		long startTime = System.currentTimeMillis();
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		long endTime = System.currentTimeMillis();
		long timeElapsed = endTime - startTime;
		String connectionSummary = connection.getResponseCode() + ":" + timeElapsed;
		BufferedReader bufferedReader = new BufferedReader(new StringReader(connectionSummary)); //Here we could add page content from inputStream, but it's not currently needed.
		return bufferedReader;
		}

	@Override
	public String formatResponse(BufferedReader bufferedReader) throws IOException {
		
		String response = bufferedReader.readLine();
		logger.warning(response);
//		String formattedResponse = "Response code: "+ response[0] + "\nConnection time: " + response[1];
		return response;
	}

	@Override
	public void analyzeResponse(String response) {
		// TODO Auto-generated method stub
		
	}
	
}
