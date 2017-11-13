package domini.Tuple;

public class Tuple {//Author:Luis
	
	private String playerName;
	private Float value;
	
	public Tuple(String playerName,Float value){
		this.playerName = playerName;
		this.value = value;
	}
	
	public String getPlayerName() {
		return playerName;
	}

	
	public Float getValue() {
		return value;
	}
	
}
