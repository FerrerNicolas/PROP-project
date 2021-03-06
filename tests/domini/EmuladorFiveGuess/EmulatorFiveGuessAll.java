package domini.EmuladorFiveGuess;
//GuillemVictor
import exceptions.*;
import model.Board;
import model.Code;
import model.Correction;
import model.Diff;
import model.FiveGuess;
import model.Game;

public class EmulatorFiveGuessAll {
	
	public static void main (String[] args) {
		double easygames = 0;
		double normalgames = 0;
		double hardgames = 0;
		double maxeasyturns = 0;
		double maxnormalturns = 0;
		double maxhardturns = 0;
		double totaleasyturns = 0;
		double totalnormalturns = 0;
		double totalhardturns = 0;
		Integer code = 0;
		while (code < 7000) {
			for (int i = 0; i<3; i++) {
				Game g;
				switch(i) {
				case 0:
					g = new Game(false, Diff.EASY);
					break;
				case 1:
					g = new Game(false, Diff.NORMAL);
					break;
				case 2:
					g = new Game(false, Diff.HARD);
					break;
				default:
					g = new Game(false, Diff.NORMAL);
					break;
				}
				Board b = g.getBoard();
				Code secretCode = new Code();
				try {
					secretCode = new Code(code);
				} catch (BadlyFormedCode e4) {
					System.out.println("This should never happen");
				}
				Boolean valid = true;
				try {
					b.setSecretCode(secretCode);
				} catch (CodeIsInvalid e3) { valid = false;}
				
				if (valid) {
					//System.out.println("Playing game on difficulty " + g.getDifficulty() + " with secret code " + secretCode.getCode().toString());
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
						System.out.println("This should not happen, but code " + nextGuess.getCode() + " is invalid!");
					} catch (UncorrectedGuessExists e) {
						System.out.println("This should not happen, but a guess was gonna be added before a correction");
					}
					Correction correction = nextGuess.correct(secretCode);
					try {
						b.addCorrection(correction);
					} catch (NoGuessToBeCorrected e2) {
						System.out.println("this shouldn't happen but a correction was added before a guess to correct");
					}
					//System.out.println("Turn: "+ b.turnsDone() +"  Played "+nextGuess.getCode()+" got " + correction.getWhitePins() + "W " + correction.getBlackPins() + "B");
					Integer tmp = 0;
					Boolean cont = !b.hasWon();
					try {
						while (cont) {
							tmp = nextGuess.getCode();
							//System.out.println("Tried " + nextGuess.getCode().toString() + ", got correction " + correction.getBlackPins() + "B " + correction.getWhitePins()+"W" );
							nextGuess=fg.codeBreakerTurn(nextGuess, correction); // CodeOrCorrectionNull, CodeAlreadyUsed
							correction = nextGuess.correct(secretCode);
							try {
								b.addGuess(nextGuess);
							} catch (CodeIsInvalid e1) {
								System.out.println("This should not happen, but code " + nextGuess.getCode() + " is invalid!");
							} catch (UncorrectedGuessExists e2) {
								System.out.println("This should not happen, but a guess was gonna be added before a correction");
							}
							try {
								cont = !b.addCorrection(correction);
							} catch (NoGuessToBeCorrected e) {
								cont = false;
								System.out.println("this shouldn't happen but a correction was added before a guess to correct");
							}
							//System.out.println("Turn: "+ b.turnsDone() +"  Played "+nextGuess.getCode()+" got " + correction.getWhitePins() + "W " + correction.getBlackPins() + "B");
						}
						if (!b.hasWon())// System.out.println("AI won by guessing " + nextGuess.getCode());
							System.out.println("AI Lost by guessing  " + nextGuess.getCode());
					} catch (CodeOrCorrectionNull e) {
						System.out.println("This should never happen, but one was null");
					} catch (CodeAlreadyUsed e) {
						System.out.print("This should never happen, code"+ tmp +"was already used while playing " + code + " in ");
						switch(i) {
						case 0:
							System.out.println("EASY");
							break;
						case 1:
							System.out.println("NORMAL");
							break;
						case 2:
							System.out.println("HARD");
							break;
						}
					}
					switch(i) {
					case 0:
						easygames++;
						if (b.turnsDone() > maxeasyturns)
							maxeasyturns = b.turnsDone();
						totaleasyturns+=b.turnsDone();
						break;
					case 1:
						normalgames++;
						if (b.turnsDone() > maxnormalturns)
							maxnormalturns = b.turnsDone();
						totalnormalturns+=b.turnsDone();
						if (b.turnsDone() == 6)
							System.out.println("Code "+code+ " took 6 turns in NORMAL");
						break;
					case 2:
						hardgames++;
						if (b.turnsDone() > maxhardturns)
							maxhardturns = b.turnsDone();
						totalhardturns+=b.turnsDone();
						break;
					}
				}
			}
			code++;
			if ((code%10)       == 7) code +=   3;
			if ((code%100/10)   == 7) code +=  30;
			if ((code%1000/100) == 7) code += 300; //Overflow managing
		}
		System.out.println("Played " + easygames + " EASY games: Won in at most " + maxeasyturns + " turns. Took " + totaleasyturns/easygames + " turns in average to win." );
		System.out.println("Played " + normalgames + " NORMAL games: Won in at most " + maxnormalturns + " turns. Took " + totalnormalturns/normalgames + " turns in average to win." );
		System.out.println("Played " + hardgames + " HARD games: Won in at most " + maxhardturns + " turns. Took " + totalhardturns/hardgames + " turns in average to win." );
	}	
}
