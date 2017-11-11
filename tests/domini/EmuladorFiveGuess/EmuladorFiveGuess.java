package domini.EmuladorFiveGuess;

import java.util.Scanner;

//import domini.*;
import model.*;
import exceptions.*;
//Victor
public class EmuladorFiveGuess {
	public static void main (String[] args) {
		System.out.println("Input Game Difficulty e|n|h");
		Scanner sc= new Scanner(System.in);
		Boolean correctinput = false;
		Game g = new Game(false, Diff.EASY);
		while(!correctinput) {
			String A = sc.nextLine();
			correctinput = true;
			if(A.equalsIgnoreCase("e")) g = new Game(false, Diff.EASY);
			else if (A.equalsIgnoreCase("n")) g = new Game(false, Diff.NORMAL);
			else if (A.equalsIgnoreCase("h")) g = new Game(false, Diff.HARD);
			else correctinput = false;
			if (!correctinput) System.out.println("Input Game Difficulty e|n|h");
		}
		System.out.println("Input Secret Code");
		correctinput = false;
		Integer secret;
		Code secretCode = new Code();
		Board b = g.getBoard();
		while(!correctinput) {
			secret = sc.nextInt();
			try{
				secretCode = new Code(secret);
				correctinput = true;
				if(g.codeIsValid(secretCode)) 
					b.setSecretCode(secretCode);
				else correctinput = false;
			} catch (CodeIsInvalid c) {
				correctinput = false;
			} catch (BadlyFormedCode c) {
				correctinput = false;
			}
			if (!correctinput) System.out.println("Input Valid Secret Code for the chosen difficulty");
		}
		System.out.println("Playing game on difficulty " + g.getDifficulty() + " with secret code " + secretCode.getCode().toString());
		System.out.println("Do you want to correct Guesses manually? (y/n)");
		Boolean manual = true; 
		correctinput = false;
		sc.nextLine();
		while (!correctinput) {
			correctinput = true;
			String A = sc.nextLine();
			if(A.equalsIgnoreCase("y")) manual = true;
			else if (A.equalsIgnoreCase("n")) manual = false;
			else correctinput = false;
			if(!correctinput) System.out.println("Expected (y/n), got " + A + ". Please repeat input");
		}
		FiveGuess fg = new FiveGuess(g);
		Code nextGuess = new Code();
		try {
			nextGuess = fg.codeBreakerTurn(null, null);
		} catch(Exception e) {
			System.out.println("This should never happen");
		}
		try {
			b.addGuess(nextGuess);
		} catch (CodeIsInvalid e1) {
			System.out.println("This should not happen, but code " + nextGuess.toString() + " is invalid!");
		}catch (UncorrectedGuessExists e1) {
			System.out.println("This should not happen, but system tried to push a code when the previous was not corrected.");
		}
		
		
		Correction correction = nextGuess.correct(secretCode);
		if(manual) {
			Boolean goodCorrection = false;
			while(!goodCorrection) {
				System.out.println("Please correct " + nextGuess.toString() + " (input #whites pins then black)");
				Integer whites = sc.nextInt();
				Integer blacks = sc.nextInt();
				if(whites.equals(correction.getWhitePins()) && blacks.equals(correction.getBlackPins()) ) goodCorrection = true;
				else System.out.println("Correction is wrong!");
			}
		}
		Boolean end = false;
		try {
			end = b.addCorrection(correction);
		} catch (NoGuessToBeCorrected e2) {
			System.out.println("This should not happen, but a correction was pushed to board when no guess needed to be corrected ");
		}
		try {
			while (!end) {
				System.out.println("Tried " + nextGuess.toString() + ", got correction " + correction.toString() );
					nextGuess=fg.codeBreakerTurn(nextGuess, correction); // CodeOrCorrectionNull, CodeAlreadyUsed
					correction = nextGuess.correct(secretCode);
					if(manual) {
						Boolean goodCorrection = false;
						while(!goodCorrection) {
							System.out.println("Please correct " + nextGuess.toString() + " (input #whites pins then black)");
							Integer whites = sc.nextInt();
							Integer blacks = sc.nextInt();
							if(whites.equals(correction.getWhitePins()) && blacks.equals(correction.getBlackPins()) ) goodCorrection = true;
							else System.out.println("Correction is wrong!");
						}
					}
					try {
						b.addGuess(nextGuess);
					} catch (CodeIsInvalid e1) {
						System.out.println("This should not happen, but code " + nextGuess.toString() + " is invalid!");
					} catch (UncorrectedGuessExists e1) {
						System.out.println("This should not happen, but system tried to push a code when the previous was not corrected.");
					}
					try {
						end = b.addCorrection(correction);
					} catch (NoGuessToBeCorrected e2) {
						System.out.println("This should not happen, but a correction was pushed to board when no guess needed to be corrected ");
					}
			
			}
			if (b.hasWon()) System.out.println("AI won by guessing " + nextGuess.toString());
			else System.out.println("AI Lost by guessing  " + nextGuess.toString());
		} catch (CodeOrCorrectionNull e) {
			System.out.println("This should never happen, but one was null");
		} catch (CodeAlreadyUsed e) {
			System.out.println("This should never happen, code was already used");
		}
		sc.close();
	}
}