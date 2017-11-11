package model;
//Guillem

import exceptions.*;

public class Correction implements Cloneable{
	private int whitePins;
	private int blackPins;
	
	public Correction() {
		whitePins = 0;
		blackPins = 0;
	}
	
	public Correction(int wPins, int bPins) throws InvalidNumberOfPins{
		if (wPins+bPins > 4 || wPins > 4 || bPins > 4)
			throw (new InvalidNumberOfPins());
		else {
			whitePins = wPins;
			blackPins = bPins;
		}
	}
	
	public int getWhitePins() {
		return whitePins;
	}
	
	public int getBlackPins() {
		return blackPins;
	}
	
	public void setWhitePins(int wPins) throws InvalidNumberOfPins{
		if (wPins+this.blackPins > 4 || wPins > 4)
			throw (new InvalidNumberOfPins());
		else {
			whitePins = wPins;
		}
	}
	
	public void setBlackPins(int bPins) throws InvalidNumberOfPins{
		if (this.whitePins+bPins > 4 || bPins > 4)
			throw (new InvalidNumberOfPins());
		else {
			blackPins = bPins;
		}
	}

	public boolean equals(Object object) {
        boolean same = false;
        if (object != null && object instanceof Correction) {
            same = this.whitePins == ((Correction)object).getWhitePins() && this.blackPins == ((Correction)object).getBlackPins();
        }
        return same;
    }
	
	public Object clone()
	{
		Correction another = null;
		try
		{
			another = (Correction) super.clone();
			// shallow copy made
		}
		catch(CloneNotSupportedException e) {} 
		//This exception will not occur
	
		return another;
	}
}
