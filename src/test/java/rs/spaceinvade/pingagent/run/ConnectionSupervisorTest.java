package rs.spaceinvade.pingagent.run;

import org.junit.Test;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

import rs.spaceinvade.pingagent.utils.TestUtils;

public class ConnectionSupervisorTest {

	private static Logger logger = Logger.getLogger(ConnectionSupervisorTest.class.getName());

	@Test
	public void callReportIfExceptionDuringCommand() throws Exception {
		SimpleConnectionSupervisor connectionSupervisor = spy(SimpleConnectionSupervisor.class);
		doThrow(new RuntimeException()).when(connectionSupervisor).sendInstruction();
		connectionSupervisor.runCommand();
		verify(connectionSupervisor).sendReport();
	}

	@Test
	public void storageContainsLastResultForGivenHost() throws Exception {
		PingManagerICMP pingManagerICMP = spy(PingManagerICMP.class);
		pingManagerICMP.setProcessBuilder(new ProcessBuilder("dummy command"));
		String response;
		try {
			response = TestUtils.readInputStream(getClass().getClassLoader().getResourceAsStream("ping.icmp.response.ok"));
			logger.info(response);
			doReturn(new BufferedReader(new StringReader(response))).when(pingManagerICMP).sendInstruction();
			pingManagerICMP.runCommand();
			logger.info(pingManagerICMP.getLastResult());
			assertEquals(response.trim(), pingManagerICMP.getLastResult().trim());
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}
}
