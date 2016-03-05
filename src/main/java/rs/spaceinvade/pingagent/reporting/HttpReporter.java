package rs.spaceinvade.pingagent.reporting;

import java.util.List;

import rs.spaceinvade.pingagent.run.ConnectionSupervisor;

public class HttpReporter implements Reporter {
	
	List<ConnectionSupervisor>supervisors;
	
	public HttpReporter(List<ConnectionSupervisor>supervisors){
		this.supervisors = supervisors;
	}

	@Override
	public void generateReport() {
		supervisors.forEach((supervisor)->{
			
			supervisor.getLastResult();
			});

	}

	@Override
	public void sendReport() {
		// TODO Auto-generated method stub

	}

}
