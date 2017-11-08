package model;
import java.util.*;

//Victor & Guillem
public class FiveGuess extends Ai {
	private ArrayList<Code> unusedGuesses;
	private ArrayList<Code> S;

	
	public FiveGuess() {
		unusedGuesses = new ArrayList<Code>();
		S = new ArrayList<Code>();
		Integer c = 0000;
		Code code = new Code();
		while (c < 7000) {
			code.setCode(c);
			if(game.codeIsValid(code)) {
				unusedGuesses.add(code.clone());
				S.add(code.clone());
			}
			c++;
			if ((c%10)       == 7) c +=   3;
			if ((c%100/10)   == 7) c +=  30;
			if ((c%1000/100) == 7) c += 300; //Overflow managing
		}
		//RN this should build all the set!
	}
	
	public Code codeBreakerTurn(Code code, Correction correction) { //In case javi does not have it, consulting board may be best (through game)
		if(code == null && correction == null) {
			return new Code(1122);
		}
		//Exception handling! 1 null or whatevs

		unusedGuesses.remove(code);
		// eliminate from S
		int i=0;
		while(i<S.size()) {
			if(!S.get(i).isCompatible(code, correction)) S.remove(i);
			else i++;
		}
		
		//...
		Code nextGuess = new Code(); //Change to code.
		int minScore = 0;
		for(i = 0; i < unusedGuesses.size(); i++) {
			ArrayList<ArrayList<Integer> > hitCount =  new ArrayList<ArrayList<Integer> >();
			for(int a=0; a < 5; a++) {
				ArrayList<Integer> r = new ArrayList<Integer>();
				for(int b = 0; b < 5; b++) r.add(0);
				hitCount.add(r);
			}
			for(int j=0; j < S.size(); j++) {
				Correction c = S.get(j).correct(unusedGuesses.get(i));
				Integer aux = hitCount.get(c.getBlackPins()).get(c.getWhitePins());
				hitCount.get(c.getBlackPins()).set(c.getWhitePins(), aux+1);
			}
			int localMaxHitCount=0;
			for(int a=0; a<5; a++) {
				for(int b=0; b<5; b++) {
					localMaxHitCount = Math.max(localMaxHitCount, hitCount.get(a).get(b));
				}
			}
			int score = S.size()-localMaxHitCount;
			if (score > minScore) {
				minScore = score;
				nextGuess = unusedGuesses.get(i).clone();
			} else if(score == minScore && S.contains( unusedGuesses.get(i) ) ) {
				nextGuess = unusedGuesses.get(i).clone();
				// We do this to ensure picking elements preferably from S
			}
		}
		return nextGuess;
	}
	
}
