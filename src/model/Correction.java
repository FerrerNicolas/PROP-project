package model;
// Guillem
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

	public boolean equals(Correction c2) {
	    int wPins = getWhitePins();
	    int bPins = getBlackPins();
	    return (this.blackPins == bPins && this.whitePins == wPins);
    }
}
