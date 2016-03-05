package rs.spaceinvade.pingagent.storage;

import java.util.Map;

/**
 * Stores results from supervision operations. They are stored in a map, the key being host, and the value being corresponding ControlResult object.
 * @author Landry Soules
 *
 */
public interface Storage {


	/**
	 * Returns the map containing the results for all the hosts. 
	 */
	public Map<String,ControlResult> getControlResults();
	
	/**
	 * Returns {@link ControlResult} associated with given host. 
	 * 
	 */
	public ControlResult getControlResultForHost(String host);
}
