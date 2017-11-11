package model;
// Guillem

import exceptions.*;

public class Correction {
	private int whitePins;
	private int blackPins;
	
	public Correction() {
		whitePins = 0;
		blackPins = 0;
	}
	
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
	
	public void setWhitePins(int wPins)/* throws InvalidNumberOfPins*/{
		whitePins = wPins;
	}
	
	public void setBlackPins(int bPins)/* throws InvalidNumberOfPins*/{
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
