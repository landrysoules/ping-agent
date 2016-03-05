package rs.spaceinvade.pingagent.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import rs.spaceinvade.pingagent.App;
import rs.spaceinvade.pingagent.exception.PingAgentException;

public class ConfigManagerImpl implements ConfigManager {

	private Map<String, String> cliParams = new HashMap<String, String>();
	private Properties properties = new Properties();
	private String[] mandatoryProperties = new String[] { "hosts", "ping.icmp.interval", "ping.http.interval",
			"trace.interval", "log.file", "log.level" };
	private static Logger logger = Logger.getLogger(ConfigManagerImpl.class.getName());
	/**
	 * Raw String array parameters from program's main method
	 */
	private String[]mainArgs = null;

	public ConfigManagerImpl() {

	}

	public ConfigManagerImpl(Map<String, String> cliParams) {
		this.cliParams = cliParams;
	}
	
	public ConfigManagerImpl(String[] mainArgs){
		this.mainArgs = mainArgs;
		cliParams = processMainArgs(mainArgs);
	}

	@Override
	public void loadConfig() throws PingAgentException {
		InputStream inputStream = getConfigFile();
		if (inputStream != null) {
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				logger.info(
						e.getMessage() + " ==> We do nothing: it's ok if the properties have been passed through CLI");
			}
		}
		getCliParams().forEach((k, v) -> properties.put(k, v));
		List<String> missingProperties = checkProperties(mandatoryProperties);
		StringBuffer missingPropertiesFormatted = new StringBuffer();
		missingProperties.forEach((prop) -> missingPropertiesFormatted.append(prop + System.lineSeparator()));
		if (!missingProperties.isEmpty()) {
			throw new PingAgentException(
					"Properties are missing. Please provide following properties: " + missingPropertiesFormatted);
		}
	}

	@Override
	public Map<String, String> getCliParams() {
		return cliParams;
	}

	@Override
	public void setCliParams(Map<String, String> cliParams) {
		this.cliParams = cliParams;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public void addCliParam(String paramName, String paramValue) {
		cliParams.put(paramName, paramValue);
	}

	@Override
	public List<String> checkProperties(String[] mandatoryProperties) {
		Properties properties = getProperties();
		List<String> missingProperties = new ArrayList<String>();
		for (String mandatoryProperty : mandatoryProperties) {
			if (!properties.containsKey(mandatoryProperty)) {
				missingProperties.add(mandatoryProperty);
			}
		}
		return missingProperties;
	}

	@Override
	public InputStream getConfigFile() {
		InputStream inputStream = App.class.getResourceAsStream("config.properties");
		return inputStream;
	}

	@Override
	public String[] getMainArgs() {
		return mainArgs;
	}

	@Override
	public void setMainArgs(String[] mainArgs) {
		this.mainArgs = mainArgs;
	}

	@Override
	public Map<String, String> processMainArgs(String[] mainArgs) {
		Map<String,String> cliParams = new HashMap<>();
		for(String arg:mainArgs){
			String[]args = arg.split("=");
			cliParams.put(args[0], args[1]);
		}
		return cliParams;
	}
}
