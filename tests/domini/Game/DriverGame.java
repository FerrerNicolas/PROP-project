package domini.Game;

import java.util.Scanner;

import model.Diff;
import model.Code;


public class DriverGame {
	
	public static void main (String[] args) {
		Boolean exit = false;
		Game g = new Game(true, Diff.EASY);
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
			switch(option) {
			case 1:
				System.out.println("Input (b,m) for player breaker/maker, enter and e,n,h for difficulty.");
				String A = sc.nextLine();
				Boolean uIB = true, correctinput = true;
				if(A.equalsIgnoreCase("b")) uIB = true;
				else if (A.equalsIgnoreCase("m")) uIB = false;
				else correctinput = false;
				while(!correctinput) {
					System.out.println("Input not valid, expected b | m, please repeat");
					A = sc.nextLine();
					correctinput = true;
					if(A.equalsIgnoreCase("b")) uIB = true;
					else if (A.equalsIgnoreCase("m")) uIB = false;
					else correctinput = false;
				}
				correctinput = true;
				A = sc.nextLine();
				if(A.equalsIgnoreCase("e")) g = new Game(uIB, Diff.EASY);
				else if (A.equalsIgnoreCase("n")) g = new Game(uIB, Diff.NORMAL);
				else if (A.equalsIgnoreCase("d")) g = new Game(uIB, Diff.HARD);
				else correctinput = false;
				while(!correctinput) {
					System.out.println("Input not valid, expected e|n|h, please repeat");
					A = sc.nextLine();
					correctinput = true;
					if(A.equalsIgnoreCase("e")) g = new Game(uIB, Diff.EASY);
					else if (A.equalsIgnoreCase("n")) g = new Game(uIB, Diff.NORMAL);
					else if (A.equalsIgnoreCase("h")) g = new Game(uIB, Diff.HARD);
					else correctinput = false;
				}
				System.out.println("Game initialized");
				break;
			case 2:
				System.out.println(g.getBoard().toString());
				break;
			case 3:
				System.out.println(g.getDifficulty().toString());
				break;
			case 4:
				System.out.println(g.getUserIsBreaker().toString());
				break;
			case 5:
				Integer code = sc.nextInt();
				Code c = new Code(code); //Probably will need an exception handler.
				System.out.println(g.codeIsValid(c).toString());
				break;
			case 6:
				exit = true;
				sc.close();
				break;
			default:
				System.out.println("Option not valid, please input a number from 1 to 6");
				break;
				
			}
		}
	}
}