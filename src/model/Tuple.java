package model;

public class Tuple {
	private String playerName;
	private Float value;
	
	public Tuple(String playerName,Float value){
		playerName = this.playerName;
		value = this.value;
	}
	
	public String getPlayerName() {
		return playerName;
	}

	
	public Float getValue() {
		return value;
	}
	
}
