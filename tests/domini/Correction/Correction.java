package domini.Correction;

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

	public boolean equals(Object object) {
        boolean same = false;
        if (object != null && object instanceof Correction) {
            same = this.whitePins == ((Correction)object).getWhitePins() && this.blackPins == ((Correction)object).getBlackPins();
        }
        return same;
    }

}