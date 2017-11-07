package model;

public class Correction {
	private int whitePins;
	private int blackPins;
	
	public Correction(int wPins, int bPins) {
		whitePins = wPins;
		blackPins = bPins;
	}
	
	public int getWhitePins() {
		return whitePins;
	}
	
	public int getBlackPins() {
		return blackPins;
	}
	
	public void setWhitePins(int wPins){
		whitePins = wPins;
	}
	
	public void setBlackPins(int bPins){
		blackPins = bPins;
	}
}
