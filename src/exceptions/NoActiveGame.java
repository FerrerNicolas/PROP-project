package exceptions;

public class NoActiveGame extends Exception {
	public NoActiveGame() {
		super();
	}
	public NoActiveGame(String msg) {
		super(msg);
	}
}