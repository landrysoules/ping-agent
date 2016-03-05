package rs.spaceinvade.pingagent.storage;

/**
 * Storage unity for a single host.
 * @author Landry Soules
 *
 */
public interface ControlResult {

	public String getHost();

	public void setHost(String host);

	public String getLastICMPPing();

	public void setLastICMPPing(String lastICMPPing);

	public String getLastTCPPing();

	public void setLastTCPPing(String lastTCPPing);

	public String getLastTrace();

	public void setLastTrace(String lastTrace);
}
