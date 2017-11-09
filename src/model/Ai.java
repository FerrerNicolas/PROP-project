package model;

import java.util.*;

import exceptions.*;

//Victor
public abstract class Ai {
	protected Game game;
	// AI to player!
	public Ai (Game g) {
		game = g;
	}
	public abstract Code codeBreakerTurn(Code code, Correction correction) throws CodeOrCorrectionNull, CodeAlreadyUsed;
	
	public Code generateSecretCode() {
		Random rand = new Random();
		Boolean isValid = false;
		Integer code = new Integer(0);
		Diff d = game.getDifficulty();
		Code c = new Code();
		while(!isValid) {
			code = 0;
			Integer  n;
			for(int i=0; i < 4; i++) {
				if(d != Diff.HARD) 
					n = rand.nextInt(7);
				else 
					n = rand.nextInt(6)+1;
				code=code*10+n;
			}
			c.setCode(code);
			isValid = game.codeIsValid(c);
		}
		return c;
	}
	public Correction correctGuess(Code guess) {
		Code c = game.getBoard().getSecretCode();
		return guess.correct(c);
	}
}