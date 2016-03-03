package rs.spaceinvade.pingagent.config;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class ConfigManagerTest {
	private static Logger logger = Logger.getLogger("ConfigManagerTest");
	@Test
	public void cliParamsOverridesPropsFile() {
		ConfigManager configManager = new ConfigManagerImpl();
		configManager.addCliParam("urls", "http://www.google.com");
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.test.properties");
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			logger.log(Level.SEVERE,e.getMessage(),e);
		}
		configManager.getCliParams().forEach((k,v)->properties.put(k, v));
		configManager.setProperties(properties);
		logger.fine( configManager.getProperties().toString());
		assertEquals("{ping.http.interval=5000, urls=http://www.google.com}", configManager.getProperties().toString());
	}

}
