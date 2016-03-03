package rs.spaceinvade.pingagent.config;

import java.util.Map;
import java.util.Properties;

/**
 * In charge of loading configuration from poperty file as well as command-line. 
 * @author Landry Soules
 *
 */
public interface ConfigManager {
	
	/**
	 * Loads configuration.
	 */
	public void loadConfig();
	

	public Map<String, String> getCliParams();

	public void setCliParams(Map<String, String> cliParams);
	
	public void addCliParam(String paramName, String paramValue);

	public Properties getProperties();

	void setProperties(Properties properties);

}
