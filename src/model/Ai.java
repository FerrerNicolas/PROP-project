package model;

import java.util.*;

//Victor
public abstract class Ai {
	protected Game game;
	// AI to player!
	
	public abstract Code codeBreakerTurn(Code code, Correction correction);
	
	public Code generateSecretCode() { //Need to reimplement to follow code setters
		Random rand = new Random();
		Boolean isValid = false;
		Vector<Integer> v = new Vector<Integer>(4);
		Diff d = game.getDifficulty();
		Code c = new Code();
		while(!isValid) {
			Integer  n;
			for(int i=0; i < 4; i++) {
				if(d != Diff.HARD) 
					n = rand.nextInt(7);
				else 
					n = rand.nextInt(6)+1;
				v.set(i, n);
			}
			c.setCode(v);
			isValid = game.codeIsValid(c);
		}
		return c;
	}
	public Correction correctGuess(Code guess) {
		Code c = game.getBoard().getSecretCode();
		return guess.correct(c);
	}
}