package domini.EmuladorFiveGuess;

import java.util.Random;

import exceptions.CodeAlreadyUsed;
import exceptions.CodeOrCorrectionNull;
import model.*;

//VictorGuillem
public class EmuladorFGRandom {
	public static void main (String[] args) {
		try {
		float max=0, median=0;
		long timeNow = System.currentTimeMillis();
		for(int i=0; i<10; i++) {
			Game g = new Game(false, Diff.HARD, true);
			Board b = g.getBoard();
			FiveGuess fg = new FiveGuess(g);
			Code secretCode = fg.generateSecretCode();
			b.setSecretCode(secretCode);
			Code nextGuess = new Code();
			try {
				nextGuess = fg.codeBreakerTurn(null, null);
			} catch(Exception e) {
				System.out.println("This should never happen");
			}
			b.addGuess(nextGuess);
			Correction correction = nextGuess.correct(secretCode);
			b.addCorrection(correction);
			try {
				while (!b.hasWon() && ! (b.turnsDone()== 12)) {
					//System.out.println("Tried " + nextGuess.getCode().toString() + ", got correction " + correction.toString() );
						nextGuess=fg.codeBreakerTurn(nextGuess, correction); // CodeOrCorrectionNull, CodeAlreadyUsed
						correction = nextGuess.correct(secretCode);
						b.addGuess(nextGuess); b.addCorrection(correction);
				}

				//System.out.println("AI won by guessing " + nextGuess.getCode());
				max = Math.max(b.turnsDone(), max);
				median += b.turnsDone();
				System.out.println("GAME "+ i + " ENDED");
			} catch (CodeOrCorrectionNull e) {
				System.out.println("This should never happen, but one was null");
			} catch (CodeAlreadyUsed e) {
				System.out.println("This should never happen, code was already used");
			}
		}
		long ElapsedTime = System.currentTimeMillis() - timeNow;
		System.out.println(max + " " +  median/100.0);
		System.out.println(ElapsedTime/1000.0);
		} catch (Exception e) {
			System.out.println("FATAL EXCEPTION");
		}
	}
	
}