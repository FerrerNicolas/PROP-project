package model;
import java.util.ArrayList;
import exceptions.*;
// Guillem
public class Board {
	private ArrayList<Code> guesses;
	private ArrayList<Correction> corrections;
	private Code secretCode;
	private Game game; //modify vpp to reflect this
	
	public Board(Game g) {
		guesses = new ArrayList<Code>();
		corrections = new ArrayList<Correction>();
		game = g;
	}
	
	//
	//getters
	//
	
	public Code getSecretCode() {
		return secretCode;
	}
	
	public ArrayList<Code> getGuesses(){
		return guesses;
	}
	
	public ArrayList<Correction> getCorrections(){
		return corrections;
	}
	
	//
	//setters
	//
	
	public void setSecretCode(Code secretC) throws CodeIsInvalid{
		if(game.codeIsValid(secretC))
			secretCode = secretC;
		else
			throw (new CodeIsInvalid());
	}
	
	//
	//others
	//
	
	//assumes the code is valid
	public void addGuess(Code guess) throws CodeIsInvalid{
		if(game.codeIsValid(guess))
			guesses.add(guess); //clone guesses
		else
			throw (new CodeIsInvalid());
	}
	
	//returns true if 12 turns have passed, false otherwise
	//assumes the correction is valid
	public Boolean addCorrection(Correction corr) throws CorrectionIsInvalid{
		if (corr.getBlackPins() < 5 && corr.getBlackPins() > -1 && corr.getWhitePins() < 5 && corr.getWhitePins()>-1 && corr.getWhitePins() + corr.getBlackPins() < 5) {
			corrections.add(corr);
			if (corrections.size() == 12)
				return true;
			return false;
		}else {
			throw (new CorrectionIsInvalid());
		}
	}
	
	//checks, using the last correction, if the game has been won
	public Boolean hasWon() {
		if (corrections.size() != 0) {
			Correction lastCorrection = corrections.get( corrections.size() - 1 );
			if (lastCorrection.getBlackPins() == 4)
				return true;
			return false;
		}else return false;
		
	}
	
	//returns the number of corrections
	//assumes the number of turns is equal to the number of turns done
	public int turnsDone() {
		return corrections.size();
	}
	
	
}
