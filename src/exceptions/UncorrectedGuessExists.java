package exceptions;

public class UncorrectedGuessExists extends Exception{
	public UncorrectedGuessExists() {
		super();
	}
	public UncorrectedGuessExists(String msg) {
		super(msg);
	}
}
