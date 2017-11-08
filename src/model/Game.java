package model;

//import java.util.*;
//Victor
public class Game {
	private Boolean userIsBreaker;
	private Diff difficulty;
	private Ai ai; // not needed?
	private User user; // not needed?
	private Board board;
	
	
	public Game(Boolean uIB, Diff d, Ai a, User u) {
		userIsBreaker = uIB;
		difficulty = d;
		ai = a;
		user = u;
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
