package rs.spaceinvade.pingagent.run;

import java.io.IOException;
import java.util.Properties;

/**
 * In charge of organising pinging operations.
 * @author Landry Soules
 *
 */
public interface ConnectionSupervisor {

	public String runCommand() throws IOException;

}
