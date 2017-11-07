package model;

import java.util.*;

public class Game {
	private Boolean userIsBreaker;
	private Diff difficulty;
	private Ai ai;
	private User u;
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
	//FUNCTION RECENTLY ADDED
	public Boolean codeIsValid(Code c) {
		return true; //NEED TO CHANGE
	}
}
