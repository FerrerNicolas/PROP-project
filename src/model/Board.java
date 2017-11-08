package model;
import java.util.ArrayList;
// Guillem
public class Board {
	private ArrayList<Code> guesses;
	private ArrayList<Correction> corrections;
	private Code secretCode;
	
	
	public Board() {
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
	
	public void setSecretCode(Code secretC) {
		secretCode = secretC;
	}
	
	//
	//others
	//
	
	//assumes the code is valid
	public void addGuess(Code guess){
		guesses.add(guess);
	}
	
	//returns true if 12 turns have passed, false otherwise
	//assumes the correction is valid
	public Boolean addCorrection(Correction corr) {
		corrections.add(corr);
		if (corrections.size() == 12)
			return true;
		return false;
	}
	
	//checks, using the last correction, if the game has been won
	public Boolean hasWon() {
		Correction lastCorrection = corrections.get( corrections.size() - 1 );
		if (lastCorrection.getBlackPins() == 4)
			return true;
		return false;
	}
	
	//returns the number of corrections
	//assumes the number of turns is equal to the number of turns done
	public int turnsDone() {
		return corrections.size();
	}
	
	
}
