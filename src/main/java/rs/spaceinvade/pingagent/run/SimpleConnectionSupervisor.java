package rs.spaceinvade.pingagent.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class SimpleConnectionSupervisor implements ConnectionSupervisor{

	private static Logger logger = Logger.getLogger(SimpleConnectionSupervisor.class.getName());

	private String host;

	private String lastResult;

	private Agent callingAgent;
	
	SimpleConnectionSupervisor(){
		
	}
	
	SimpleConnectionSupervisor(Agent callingAgent, String host) {
		this.callingAgent = callingAgent;
		this.host = host;
	}

	@Override
	public void sendReport() {
		logger.warning("Send report called !!");
	}

	@Override
	public void setLastResult(String lastResult) {
		this.lastResult = lastResult;
	}

	@Override
	public String getLastResult() {
		return lastResult;

	}

	@Override
	public Agent getCallingAgent() {
		return callingAgent;
	}

	@Override
	public void setCallingAgent(Agent callingAgent) {
		this.callingAgent = callingAgent;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public void runCommand() {
		try {
			BufferedReader bufferedReader = sendInstruction();
			String formattedResponse = formatResponse(bufferedReader);
			setLastResult(formattedResponse);
			analyzeResponse(formattedResponse);
			logger.info("last result: " + getLastResult());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			sendReport();
		}
	}

	public abstract BufferedReader sendInstruction() throws Exception;

	public abstract String formatResponse(BufferedReader bufferedReader) throws IOException;

	public abstract void analyzeResponse(String response);
	
	@Override
	public void run() {
		try {
			runCommand();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

}
