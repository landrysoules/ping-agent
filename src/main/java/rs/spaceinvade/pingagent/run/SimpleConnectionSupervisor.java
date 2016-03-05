package rs.spaceinvade.pingagent.run;

import rs.spaceinvade.pingagent.config.ConfigManager;

public abstract class SimpleConnectionSupervisor implements ConnectionSupervisor {

	private String host;

	SimpleConnectionSupervisor(String host) {
		this.host = host;
	}

	@Override
	public void sendReport() {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeResult() {
		// TODO Auto-generated method stub

	}

}
