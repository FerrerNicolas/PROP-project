package exceptions;

public class CorrectionIsInvalid extends Exception{
	public CorrectionIsInvalid() {
		super();
	}
	public CorrectionIsInvalid(String msg) {
		super(msg);
	}
}
