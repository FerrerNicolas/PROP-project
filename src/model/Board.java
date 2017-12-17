package model;
import java.util.ArrayList;
import java.util.Vector;

import exceptions.*;
// Guillem
public class Board {
	private ArrayList<Code> guesses;
	private ArrayList<Correction> corrections;
	private Code secretCode;
	private Game game;
	
	public Board(Game g) {
		guesses = new ArrayList<Code>();
		corrections = new ArrayList<Correction>();
		game = g;
	}
	
	//
	//getters
	//
	
	public ArrayList<Object> parse(){
		ArrayList<Object> parsed = new ArrayList<Object>();
		parsed.add(secretCode.getCodeArray());
		for(int i = 0; i < guesses.size(); i++) {
			parsed.add(guesses.get(i).getCodeArray());
			if (corrections.size() > i)
				parsed.add(corrections.get(i).parse());
		}
		
		return parsed;
	}
	
	public Code getSecretCode() {
		return secretCode.clone();
	}
	
	public ArrayList<Code> getGuesses(){
		ArrayList<Code> nguesses = new ArrayList<Code>();
		for (int i = 0; i < guesses.size();i++)
			nguesses.add(guesses.get(i).clone());
		return nguesses;
	}
	
	public ArrayList<Correction> getCorrections(){
		ArrayList<Correction> ncorrections = new ArrayList<Correction>();
		for (int i = 0; i < corrections.size(); i++)
			ncorrections.add(corrections.get(i));
		return ncorrections;
	}
	
	//
	//setters
	//
	
	public void setSecretCode(Code secretC) throws CodeIsInvalid, SecretCodeAlreadySet{
		if(secretCode == null) {
			if(game.codeIsValid(secretC))
				secretCode = secretC.clone();
			else
				throw (new CodeIsInvalid());
		}else {
			throw new SecretCodeAlreadySet();
		}
		
	}
	
	//
	//others
	//
	
	//assumes the code is valid
	public void addGuess(Code guess) throws CodeIsInvalid, UncorrectedGuessExists{
		if (guesses.size() - corrections.size() > 0)
			throw (new UncorrectedGuessExists());
		else {
			if(game.codeIsValid(guess))
				guesses.add(guess.clone()); //clone guesses
			else
				throw (new CodeIsInvalid());
		}
	}
	
	//returns true if 12 turns have passed or game has been won, false otherwise
	//assumes the correction is valid
	public Boolean addCorrection(Correction corr) throws NoGuessToBeCorrected, IncorrectCorrection{
		if (guesses.size() - corrections.size() <= 0)
			throw (new NoGuessToBeCorrected());
		else {
			if (!guesses.get(guesses.size()-1).correct(secretCode).equals(corr)) {
				throw new IncorrectCorrection();
			}else {
				corrections.add(corr.clone());
				return hasEnded();
			}
		}
	}
	
	//checks, using the last correction, if the game has been won
	public Boolean hasWon() {
		if (corrections.size() != 0) {
			Correction lastCorrection = corrections.get( corrections.size() - 1 );
			if (lastCorrection.getBlackPins() == game.getCodeMaxLength())
				return true;
			return false;
		}else return false;
		
	}
	public Boolean hasEnded() {
		if (corrections.size() == 12 || hasWon()) {
			return true;
		}else {
			return false;
		}
	}
	
	//returns the number of corrections
	//assumes the number of turns is equal to the number of turns done
	public int turnsDone() {
		return corrections.size();
	}
	
	
}
