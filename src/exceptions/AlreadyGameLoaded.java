package exceptions;

public class AlreadyGameLoaded extends Exception {
	public AlreadyGameLoaded() {
		super();
	}
	public AlreadyGameLoaded(String msg) {
		super(msg);
	}
}