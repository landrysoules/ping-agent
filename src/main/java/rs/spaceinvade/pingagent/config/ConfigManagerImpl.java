package rs.spaceinvade.pingagent.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigManagerImpl implements ConfigManager {
	
	private Map<String, String> cliParams = new HashMap<String,String>();
	private Properties properties;

	@Override
	public void loadConfig() {
		// TODO Auto-generated method stub
	}

	@Override
	public Map<String, String> getCliParams() {
		return cliParams;
	}

	@Override
	public void setCliParams(Map<String,String> cliParams) {
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
}
