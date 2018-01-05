package model;

import java.io.Serializable;

//Victor
public class Player implements Serializable {
	private String playerName;
	private Float gamesWon;
	private Float gamesLost;
	private Float totalScore;
	private Float maxScore;
	private Float minGuesses;
	private static final long serialVersionUID = 2L;
	public Player(String name) {
		playerName = name;
		gamesWon = 0.0f;
		gamesLost = 0.0f;
		totalScore = 0.0f;
		maxScore = 0.0f;
		minGuesses = 13.0f; //this is maximum
	}
	public String getPlayerName() {
		return this.playerName;
	}
	public Float getFinishedGames() {
		return gamesWon+gamesLost;
	}
	public Float getGamesWon() {
		return gamesWon;
	}
	public Float getGamesLost() {
		return gamesLost;
	}
	public Float getTotalScore() {
		return totalScore;
	}
	public Float getMaxScore() {
		return maxScore;
	}
	public Float getMinGuesses() {
		return minGuesses;
	}
	
	public void updateRecords(Boolean hasWonGame, Float score, Integer nGuesses) {
		if(hasWonGame) {
			gamesWon++;
			totalScore+=score;
			maxScore = Math.max(maxScore, score);
			minGuesses = Math.min(minGuesses, nGuesses);
		} else gamesLost++;
	}
}
