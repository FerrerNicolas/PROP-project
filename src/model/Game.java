package model;

//import java.util.*;
//Victor
public class Game {
	private Boolean userIsBreaker;
	private Diff difficulty;
	private Board board;
	private Boolean ranked;//NEED TO REWORK!
	private Boolean aiIsFG;
	
	public Game(Boolean uIB, Diff d, Boolean FG) {
		userIsBreaker = uIB;
		difficulty = d;
		board = new Board(this);
		ranked = true; 
		aiIsFG = FG;
	}
	public Board getBoard() {
		return board;
	}
	public Diff getDifficulty() {
		return difficulty;
	}
	public Boolean isAiFG() {
		return aiIsFG;
	}
	public Boolean getUserIsBreaker() {
		return userIsBreaker;
	}
	public Boolean codeIsValid(Code c) {
		if(difficulty == Diff.EASY)
			return (!c.hasRepetitions()) && (!c.hasBlanks()) && c.getCodeSize() ==4;
		else if (difficulty == Diff.NORMAL)
			return !c.hasBlanks() && c.getCodeSize() ==4;
		else
			return  c.getCodeSize() == 5;
	}
	public Integer getCodeMaxLength() {
		if (difficulty == Diff.HARD) return 5;
		else return 4;
	}
}
