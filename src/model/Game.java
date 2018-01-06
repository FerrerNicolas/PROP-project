package model;

import java.io.Serializable;

//import java.util.*;
//Victor
public class Game implements Serializable{
	private Boolean userIsBreaker;
	private Diff difficulty;
	private Board board;
	private Boolean ranked;//NEED TO REWORK!
	private Boolean aiIsFG;
	private static final long serialVersionUID = 3L;
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
	public void unRank() {
		ranked = false;
	}
	public Boolean isRanked() {
		return ranked;
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
	public Boolean hasEnded() {
		return board.hasEnded();
	}
}
