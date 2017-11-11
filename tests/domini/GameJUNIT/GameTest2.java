package domini.GameJUNIT;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import model.Code;
import model.Diff;
//Victor
@RunWith(Parameterized.class)
public class GameTest2 {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 { false, Diff.EASY, 0000 }, 
                 { false, Diff.EASY, 1224 }, 
                 { true,  Diff.EASY, 1234 }, 
                 { false, Diff.NORMAL, 0000 }, 
                 { true,  Diff.NORMAL, 1224 }, 
                 { true,  Diff.NORMAL, 1234 }, 
                 { true,  Diff.HARD, 0000 }, 
                 { true,  Diff.HARD, 1224 }, 
                 { true,  Diff.HARD, 1234 }, 
           });
    }
    // expected, diff, code
    private Boolean expected;
    private Code c;
    private Game g;

    public GameTest2(Boolean e, Diff d2, Integer code) {
        expected = e;
        g = new Game(true, d2);
        try {
        	c = new Code(code);
        } catch (Exception ex) {
        	//THIS SHOULD NEVER HAPPEN
        }
    }

    @Test
    public void test() {
        assertEquals(expected, g.codeIsValid(c));
    }
}