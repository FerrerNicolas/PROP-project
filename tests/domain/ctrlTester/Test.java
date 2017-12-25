package domain.ctrlTester;
public class Test {
	public static void main(String[] args) {
		Driver d = new Driver();
		try {
			d.start();
		} catch(Exception e) {
			System.out.println("FATAL UNHANDLED ERROR IN PERSISTANCE!");
		}
	}
}
