package model;
import java.util.ArrayList;

public class Board {
	private ArrayList<Code> guesses;
	private ArrayList<Correction> corrections;
	private Code secretCode;
	
	
	public Board() {
	}
	
	public Code getSecretCode() {
		return secretCode;
	}
	
	public void setSecretCode(Code secretC) {
		secretCode = secretC;
	}
	
	public void addGuess(Code guess){
		guesses.add(guess);
	}
	
	public Boolean addCorrection(Correction corr) {
		corrections.add(corr);
	}
	//= new ArrayList<Code>();
}
