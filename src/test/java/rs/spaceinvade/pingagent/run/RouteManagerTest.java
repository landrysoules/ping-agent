package rs.spaceinvade.pingagent.run;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class RouteManagerTest {

	private static Logger log = Logger.getLogger(RouteManager.class.getName());
	@Test
	public void runReturnsCommandResult() {
		log.setLevel(Level.FINEST);
		ConnectionSupervisor routeManager = new RouteManager();
		try {
			String pingResult = routeManager.runCommand();
			log.fine(pingResult);
			assertNotNull(pingResult);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
