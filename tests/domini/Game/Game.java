package domini.Game;

import model.Board;
import model.Code;
import model.Diff;

//Victor. Board & Code already tested.
public class Game {
	private Boolean userIsBreaker;
	private Diff difficulty;
	private Board board;
	
	
	public Game(Boolean uIB, Diff d) {
		userIsBreaker = uIB;
		difficulty = d;
		board = new Board();
	}
	public Board getBoard() {
		return board;
	}
	public Diff getDifficulty() {
		return difficulty;
	}
	public Boolean getUserIsBreaker() {
		return userIsBreaker;
	}
	//FUNCTIONS RECENTLY ADDED
	public Boolean codeIsValid(Code c) {
		if(difficulty == Diff.EASY)
			return (!c.hasRepetitions()) && (!c.hasBlanks());
		else if (difficulty == Diff.NORMAL)
			return !c.hasBlanks();
		else
			return true;
	}
}