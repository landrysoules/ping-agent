package rs.spaceinvade.pingagent.exception;

public class PingAgentException extends Exception {

	private static final long serialVersionUID = -7934500382856236954L;

	public PingAgentException(){
		super();
	}
	
	public PingAgentException(String errorMessage){
		super(errorMessage);
	}
}
