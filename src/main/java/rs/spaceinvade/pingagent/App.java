package rs.spaceinvade.pingagent;

import java.io.IOException;

import rs.spaceinvade.pingagent.run.ConnectionSupervisor;
import rs.spaceinvade.pingagent.run.PingManagerICMP;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ConnectionSupervisor pingManager = new PingManagerICMP(args);
		try {
			pingManager.runCommand();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
