package domini.Board;

public class Code {
	private Integer code;
	public Code(Integer c) {
		code = c;
	}
	
	public Code clone() {
		Code tmp = new Code(code);
		return tmp;
	}
	
	public Integer getCode() {
		return code;
	}
}
