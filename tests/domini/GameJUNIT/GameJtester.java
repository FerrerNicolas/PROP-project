package domini.GameJUNIT;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import model.Diff;
//Victor
class GameJtester {
	@Test
	void creatorAndGetters() {
		Game g = new Game(true, Diff.EASY);
		assertTrue(g.getUserIsBreaker());
		assertEquals(Diff.EASY, g.getDifficulty());
		assertNotNull(g.getBoard());
		//fail("Not yet implemented");
	}
}
