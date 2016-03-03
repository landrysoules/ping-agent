package rs.spaceinvade.pingagent.run;

import java.util.Properties;

import rs.spaceinvade.pingagent.config.ConfigManager;
import rs.spaceinvade.pingagent.config.ConfigManagerImpl;

public class PingManagerImpl implements PingManager {


	


	public PingManagerImpl(String[] cliParams){
		ConfigManager configManager = new ConfigManagerImpl();
//		configManager.setCliParams(cliParams);
	}
	

}
