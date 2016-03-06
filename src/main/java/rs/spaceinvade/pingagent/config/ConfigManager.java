package rs.spaceinvade.pingagent.config;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import rs.spaceinvade.pingagent.exception.PingAgentException;

/**
 * In charge of loading configuration from poperty file as well as command-line. 
 * @author Landry Soules
 *
 */
public interface ConfigManager {
	
	/**
	 * Loads configuration from properties file as well as command line arguments.
	 */
	public void loadConfig()throws PingAgentException;
	
	public String[] getMainArgs();
	
	public void setMainArgs(String[] mainArgs);

	public Map<String, String> getCliParams();

	public void setCliParams(Map<String, String> cliParams);
	
	public void addCliParam(String paramName, String paramValue);

	public Properties getProperties();

	public void setProperties(Properties properties);
	
	public InputStream getConfigFile();
	
	public Map<String, String>  processMainArgs(String[]mainArgs);
	
	/**
	 * Checks that all mandatory properties are present before the processes are launched.
	 * @param mandatoryProperties
	 * @return
	 * list containing the names of all missing parameters.
	 */
	public List<String> checkProperties(String[] mandatoryProperties);
}
