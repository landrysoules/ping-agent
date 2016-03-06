package rs.spaceinvade.pingagent.run;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import rs.spaceinvade.pingagent.utils.TestUtils;

public class PingManagerICMPTest {

	private static Logger logger = Logger.getLogger(PingManagerICMPTest.class.getName());

	@Test
	public void callReportIfPacketLossIsGreaterThanZero() {
		SimpleConnectionSupervisor pingManagerICMP = spy(PingManagerICMP.class);
		String response;
		try {
			response = TestUtils.readInputStream(getClass().getClassLoader().getResourceAsStream("ping.icmp.response.packet.loss"));
			logger.info(response);
			pingManagerICMP.analyzeResponse(response);
			verify(pingManagerICMP).sendReport();
		} catch (IOException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	


}
