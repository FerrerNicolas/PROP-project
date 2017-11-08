package model;
import java.util.*;

//remember to change vpp documentation
public class FiveGuess extends Ai {
	private ArrayList<Code> unusedGuesses;
	
	public FiveGuess() {
		super("FiveGuess");
		unusedGuesses = new ArrayList<Code>();
		//ArrayList<Integer> v = new ArrayList<Integer>(4,0);
		/*while(v.elementAt(0) != 7) {
			(v.elementAt(0)) = v.elementAt(0) + 1;
		}*/
	}
	
	public Code codeBreakerTurn() {
		return new Code();
	}
	
}
