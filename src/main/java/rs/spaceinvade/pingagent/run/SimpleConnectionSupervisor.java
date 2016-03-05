package rs.spaceinvade.pingagent.run;

public abstract class SimpleConnectionSupervisor implements ConnectionSupervisor {

	private String host;
	
	private String lastResult;

	private Agent callingAgent;

	SimpleConnectionSupervisor(Agent callingAgent, String host) {
		this.callingAgent = callingAgent;
		this.host = host;
	}

	@Override
	public void sendReport() {
		// TODO Auto-generated method stub

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

}
