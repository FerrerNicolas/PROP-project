package domini.GameJUNIT;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//Victor
@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	GameJtester.class, 
	GameTest2.class })
public class MasterTestSuiteGame {} //Por alguna razón, no ejecuta el test GameJtester