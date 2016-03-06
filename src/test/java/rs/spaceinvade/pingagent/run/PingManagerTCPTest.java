package rs.spaceinvade.pingagent.run;

import static org.mockito.Mockito.*;

import java.util.Properties;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import rs.spaceinvade.pingagent.config.ConfigManager;
import rs.spaceinvade.pingagent.config.ConfigManagerImpl;

public class PingManagerTCPTest {
	private static Logger logger = Logger.getLogger(PingManagerTCPTest.class.getName());

	private Agent pingAgent;
	
	@Before
	public void setUp(){
		pingAgent = mock(Agent.class);
		doNothing().when(pingAgent).report(any(String.class));
	}
	
	@Test
	public void callReportIfStatusCodeNot200() {
		SimpleConnectionSupervisor pingManagerTCP = spy(PingManagerTCP.class);
		pingManagerTCP.setCallingAgent(pingAgent);
		String response = "404:1000";
		pingManagerTCP.analyzeResponse(response);
		verify(pingManagerTCP).sendReport();
	}
	
	@Test
	public void callReportIfResponseTimeOverLimit(){
		Properties properties = new Properties();
		properties.setProperty("ping.http.max.response.time", "1000");
		ConfigManager configManager = new ConfigManagerImpl();
		configManager.setProperties(properties);
		when(pingAgent.getConfigManager()).thenReturn(configManager);
		SimpleConnectionSupervisor pingManagerTCP = spy(PingManagerTCP.class);
		pingManagerTCP.setCallingAgent(pingAgent);
		String response = "200:5000";
		pingManagerTCP.analyzeResponse(response);
		verify(pingManagerTCP).sendReport();
	}

}
