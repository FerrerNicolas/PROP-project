package domini.EmuladorFiveGuess;

import java.util.Random;
import java.util.Scanner;

import domini.*;
import exceptions.CodeAlreadyUsed;
import exceptions.CodeOrCorrectionNull;
import model.*;

//VictorGuillem
public class EmuladorFGRandom {
	public static void main (String[] args) {
		//Scanner sc= new Scanner(System.in);
		try {
		while(true) {
			Random rand = new Random();
			Integer difficulty = rand.nextInt(3);
			Game g;
			if(difficulty.equals(0)) 
				g = new Game(false, Diff.EASY);
			else if (difficulty.equals(1)) 
				g = new Game(false, Diff.NORMAL);
			else 
				g = new Game(false, Diff.HARD);
			Board b = g.getBoard();
			FiveGuess fg = new FiveGuess(g);
			Code secretCode = fg.generateSecretCode();
			b.setSecretCode(secretCode);
			
			System.out.println("Playing game on difficulty " + g.getDifficulty() + " with secret code " + secretCode.getCode().toString());
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
					System.out.println("Tried " + nextGuess.getCode().toString() + ", got correction " + correction.getBlackPins() + "B " + correction.getWhitePins()+"W" );
						nextGuess=fg.codeBreakerTurn(nextGuess, correction); // CodeOrCorrectionNull, CodeAlreadyUsed
						correction = nextGuess.correct(secretCode);
						b.addGuess(nextGuess); b.addCorrection(correction);
				}
				if (b.hasWon()) System.out.println("AI won by guessing " + nextGuess.getCode());
				else System.out.println("AI Lost by guessing  " + nextGuess.getCode());
				System.out.println();
			} catch (CodeOrCorrectionNull e) {
				System.out.println("This should never happen, but one was null");
			} catch (CodeAlreadyUsed e) {
				System.out.println("This should never happen, code was already used");
			}
		//sc.close();
		}
		} catch (Exception e) {
			System.out.println("FATAL EXCEPTION");
		}
	}
	
}