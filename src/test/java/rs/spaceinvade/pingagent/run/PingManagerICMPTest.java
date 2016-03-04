package rs.spaceinvade.pingagent.run;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class PingManagerICMPTest {

	@Test
	public void runReturnsCommandResult() {
		ConnectionSupervisor pingManager = new PingManagerICMP(new String[0]);
		try {
			String pingResult = pingManager.runCommand();
			System.out.println(pingResult);
			assertNotNull(pingResult);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
