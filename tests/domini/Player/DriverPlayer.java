package domini.Player;

import java.util.Scanner;


public class DriverPlayer {
	public static void main (String[] args) {
		Boolean exit = false;
		Player g = new Player("PREDEFINED");
		Scanner sc= new Scanner(System.in);
		while (!exit) {
			System.out.println("Input number for test");
			System.out.println("1. Instance game");
			System.out.println("2. Get Board");
			System.out.println("3. Get Difficulty");
			System.out.println("4. Is user Breaker?");
			System.out.println("5. Check Code Validity");
			System.out.println("6. Exit Driver");
			int option = sc.nextInt();
			sc.nextLine();
		}
	}
}