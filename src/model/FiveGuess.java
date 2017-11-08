package model;
import java.util.*;

//remember to change vpp documentation
public class FiveGuess extends Ai {
	private ArrayList<Code> unusedGuesses;
	private ArrayList<Code> S;

	
	public FiveGuess() {
		super("FiveGuess");
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
		if(code == null && correction == null) return new Code(1122);
		//Exception handling! 1 null or whatevs
		
		// eliminate from S!
		for (int i=0; i<S.size(); i++) {
			
		}
		return new Code();
	}
	
}
