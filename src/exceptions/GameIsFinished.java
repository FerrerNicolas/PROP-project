package exceptions;

public class GameIsFinished extends Exception {
	public GameIsFinished() {
		super();
	}
	public GameIsFinished(String msg) {
		super(msg);
	}
}