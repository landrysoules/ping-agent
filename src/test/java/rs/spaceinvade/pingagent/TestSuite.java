package rs.spaceinvade.pingagent;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import rs.spaceinvade.pingagent.config.ConfigManagerTest;
import rs.spaceinvade.pingagent.run.CommandLauncherTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ConfigManagerTest.class, CommandLauncherTest.class })

public class TestSuite {
}