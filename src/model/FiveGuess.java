package model;
import java.util.*;

//remember to change vpp documentation
public class FiveGuess extends Ai {
	private ArrayList<Code> unusedGuesses;

	
	public FiveGuess() {
		super("FiveGuess");
		unusedGuesses = new ArrayList<Code>();
		ArrayList<Integer> v = new ArrayList<Integer>(4);
		int x = 0000;
		Diff diff = game.getDifficulty();
		int limit;
		if (diff == Diff.HARD)
			limit = 6;
		else
			limit = 5;
		
		while(v.get(0) != 7 ) {
			while (v.get(1) != 7) {
				while (v.get(2) != 7) {
					while(v.get(3) != 7) {
						//if ( diff == Diff.NORMAL || diff == Diff.HARD || (diff == Diff.EASY &&/*bla*/ ) )
						//Code tmp = new Code();
						//tmp.setCode(v);
						//unusedGuesses.add(tmp);
						v.set(3, v.get(3) + 1);
					}
					v.set(2, v.get(2) + 1);
					v.set(3, 0);
				}
				v.set(1, v.get(1) + 1);
				v.set(2, 0);
			}
			v.set(0, v.get(0) + 1);
			v.set(1, 0);
		}
		/*while(v.elementAt(0) != 7) {
			(v.elementAt(0)) = v.elementAt(0) + 1;
		}*/
	}
	
	public Code codeBreakerTurn() {
		return new Code();
	}
	
}
