package rs.spaceinvade.pingagent;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import rs.spaceinvade.pingagent.config.ConfigManagerTest;
import rs.spaceinvade.pingagent.run.ConnectionSupervisorTest;
import rs.spaceinvade.pingagent.run.PingManagerICMPTest;
import rs.spaceinvade.pingagent.run.PingManagerTCPTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ConfigManagerTest.class, ConnectionSupervisorTest.class, PingManagerICMPTest.class, PingManagerTCPTest.class })

public class TestSuite {
}