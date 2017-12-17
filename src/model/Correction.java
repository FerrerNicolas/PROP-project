package model;
//Guillem

import java.util.Vector;

import exceptions.*;

public class Correction implements Cloneable{
	private int whitePins;
	private int blackPins;
	private int codeSize;
	
	public Correction() {
		whitePins = 0;
		blackPins = 0;
		codeSize = 4;
	}
	
	public Correction(int wPins, int bPins) throws InvalidNumberOfPins{
		if (wPins+bPins > 4 || wPins > 4 || bPins > 4)
			throw (new InvalidNumberOfPins());
		else {
			whitePins = wPins;
			blackPins = bPins;
			codeSize = 4;
		}
	}
	
	public Correction(int wPins, int bPins, int cSize) throws InvalidNumberOfPins{
		if (wPins+bPins > cSize || wPins > cSize || bPins > cSize)
			throw (new InvalidNumberOfPins());
		else {
			whitePins = wPins;
			blackPins = bPins;
			codeSize = cSize;
		}
	}
	
	public int getWhitePins() {
		return whitePins;
	}
	
	public int getBlackPins() {
		return blackPins;
	}
	
	public int getCodeSize() {
		return codeSize;
	}
	
	public Vector<Integer> parse(){
		Vector<Integer> parsed = new Vector<Integer>();
		parsed.add(whitePins);
		parsed.add(blackPins);
		return parsed;
	}
	
	public void setWhitePins(int wPins) throws InvalidNumberOfPins{
		if (wPins+this.blackPins > codeSize || wPins > codeSize)
			throw (new InvalidNumberOfPins());
		else {
			whitePins = wPins;
		}
	}
	
	public void setBlackPins(int bPins) throws InvalidNumberOfPins{
		if (this.whitePins+bPins > codeSize || bPins > codeSize)
			throw (new InvalidNumberOfPins());
		else {
			blackPins = bPins;
		}
	}
	
	public void setCodeSize(int cSize) throws InvalidNumberOfPins{
		if (this.whitePins+this.blackPins > cSize)
			throw (new InvalidNumberOfPins());
		else {
			codeSize = cSize;
		}
	}
	
	public String toString() {
		return new String(""+whitePins+"W "+blackPins+"B");
	}

	public boolean equals(Object object) {
        boolean same = false;
        if (object != null && object instanceof Correction) {
            same = this.whitePins == ((Correction)object).getWhitePins() && this.blackPins == ((Correction)object).getBlackPins() && this.codeSize == ((Correction)object).getCodeSize();
        }
        return same;
    }
	
	public Correction clone()
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
