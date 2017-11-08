package model;

public abstract class Player  {
	private String playerName;
	private Float gamesWon;
	private Float gamesLost;
	private Float totalScore;
	private Float maxScore;
	private Float minGuesses;
	
	public Player(String name) { //UPDATE in VPP
		playerName = name;
		gamesWon = 0.0f;
		gamesLost = 0.0f;
		totalScore = 0.0f;
		maxScore = 0.0f;
		minGuesses = 13.0f; //this is maximum
	}
	public Float getFinishedGames() { // UPDATE in VPP
		return gamesWon+gamesLost;
	}
	public void updateRecords(Boolean hasWonGame, int score, Integer nGuesses) { //Update in VPP
		if(hasWonGame) {
			gamesWon++;
			totalScore+=score;
			maxScore = Math.max(maxScore, score);
			minGuesses = Math.min(minGuesses, nGuesses);
		} else gamesLost++;
	}
	public String getPlayerName() {
		return playerName;
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
	
}
