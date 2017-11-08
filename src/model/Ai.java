package model;

import java.util.*;


public abstract class Ai extends Player {
	protected Game game;
	
	public Ai(String name) {
		super(name);
	}
	
	public abstract Code codeBreakerTurn(Code code, Correction correction); //CHANGE IN VPP!
	
	public Code generateSecretCode() {
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
	//NEED TO CHANGE IN VPP
	public Correction correctGuess(Code guess) {
		Code c = game.getBoard().getSecretCode();
		return guess.correct(c);
	}
}