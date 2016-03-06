package rs.spaceinvade.pingagent.exception;

/**
 * Custom exception used in the program. Error message is set by throwing code.
 * @author Landry Soules
 *
 */
public class PingAgentException extends Exception {

	private static final long serialVersionUID = -7934500382856236954L;

	public PingAgentException(){
		super();
	}
	
	public PingAgentException(String errorMessage){
		super(errorMessage);
	}
}
