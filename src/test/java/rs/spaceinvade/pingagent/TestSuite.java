package rs.spaceinvade.pingagent;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import rs.spaceinvade.pingagent.config.ConfigManagerTest;
import rs.spaceinvade.pingagent.run.PingManagerICMPTest;
import rs.spaceinvade.pingagent.run.PingManagerTCPTest;
import rs.spaceinvade.pingagent.run.RouteManagerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	ConfigManagerTest.class, 
	PingManagerICMPTest.class, 
	PingManagerTCPTest.class,
	RouteManagerTest.class})
public class TestSuite {
}