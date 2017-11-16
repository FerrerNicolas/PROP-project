package domini.GameJUNIT;

import static org.junit.Assert.*;

import org.junit.Test;


import model.Diff;
//Victor
public class GameJtester {
	@Test
	public void creatorAndGetters() {
		Game g = new Game(true, Diff.EASY);
		assertTrue(g.getUserIsBreaker());
		assertEquals(Diff.EASY, g.getDifficulty());
		assertNotNull(g.getBoard());
		//fail("Not yet implemented");
	}
}
