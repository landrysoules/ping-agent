package rs.spaceinvade.pingagent.run;

import java.io.IOException;

import rs.spaceinvade.pingagent.config.ConfigManager;

public class PingManagerICMP extends CommandLauncher {

	public PingManagerICMP(String host) {
		super(host);
		setProcessBuilder(new ProcessBuilder("ping", "-n", "-c 5", host));
	}


}
