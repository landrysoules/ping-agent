package rs.spaceinvade.pingagent.config;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import static org.junit.Assert.*;

import rs.spaceinvade.pingagent.exception.PingAgentException;

public class ConfigManagerTest {
	private static Logger logger = Logger.getLogger(ConfigManagerTest.class.getName());

	@Test
	public void cliParamsOverridesPropsFile() {
		Map<String, String>cliParams = new HashMap<>();
		cliParams.put("hosts", "spaceinvade.rs");
		ConfigManager configManager = new ConfigManagerImpl(cliParams);
		try {
			configManager.loadConfig();
		} catch (PingAgentException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
		assertEquals("spaceinvade.rs", configManager.getProperties().getProperty("hosts"));
	}

	@Test(expected = PingAgentException.class)
	public void throwExceptionIfSomePropertiesAreMissing() throws PingAgentException {
		ConfigManager configManager = spy(ConfigManagerImpl.class);
		doReturn(getClass().getResourceAsStream("config.test.properties")).when(configManager).getConfigFile();
		configManager.loadConfig();
	}
	
	@Test
	public void transformCorrectlyRawMainParamsToMap(){
		String[]rawParams = new String[]{"log.file=/home/pingmanager/pingmanager.log","log.level=warn"};
		ConfigManager configManager = new ConfigManagerImpl(rawParams);
		Map<String, String> processedParameters = configManager.processMainArgs(rawParams);
		assertEquals("/home/pingmanager/pingmanager.log", processedParameters.get("log.file"));
		assertEquals("warn", processedParameters.get("log.level"));
	}

}
