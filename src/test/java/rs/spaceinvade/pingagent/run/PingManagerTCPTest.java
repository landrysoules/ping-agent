package rs.spaceinvade.pingagent.run;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class PingManagerTCPTest {
	
	private static Logger log = Logger.getLogger(PingManagerTCPTest.class.getName());

	@Test
	public void runReturnsCommandResult() {
		ConnectionSupervisor pingManager = new PingManagerTCP();
		try {
			String pingResult = pingManager.runCommand();
			log.info(pingResult);
			assertNotNull(pingResult);
		} catch (IOException e) {
			log.log(Level.SEVERE,e.getMessage(),e);
		}
	}
}
