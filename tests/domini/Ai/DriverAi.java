package domini.Ai;

import java.util.Scanner;

import model.Game;
import model.Diff;
import model.Code;
import model.Correction;

public class DriverAi {
	
	public static void main (String[] args) {
		Boolean exit = false;
		Game g = new Game(true, Diff.EASY);
		Ai a = new FiveGuess(g);
		Scanner sc= new Scanner(System.in);
		while (!exit) {
			System.out.println("Input number for test");
			System.out.println("1. Instance AI");
			System.out.println("2. Stub CB turn");
			System.out.println("3. Generate random secret code");
			System.out.println("4. Correct a Guess");
			System.out.println("5. Exit Driver");
			int option = sc.nextInt();
			sc.nextLine();
			switch(option) {
			case 1:
				System.out.println("Input e,n,h for Game difficulty.");
				Boolean correctinput = true;
				String A = sc.nextLine();
				if(A.equalsIgnoreCase("e")) g = new Game(true, Diff.EASY);
				else if (A.equalsIgnoreCase("n")) g = new Game(true, Diff.NORMAL);
				else if (A.equalsIgnoreCase("h")) g = new Game(true, Diff.HARD);
				else correctinput = false;
				while(!correctinput) {
					System.out.println("Input not valid, expected e|n|h, please repeat");
					A = sc.nextLine();
					correctinput = true;
					if(A.equalsIgnoreCase("e")) g = new Game(true, Diff.EASY);
					else if (A.equalsIgnoreCase("n")) g = new Game(true, Diff.NORMAL);
					else if (A.equalsIgnoreCase("h")) g = new Game(true, Diff.HARD);
					else correctinput = false;
				}
				a = new FiveGuess(g);
				System.out.println("Ai initialized with a "+ g.getDifficulty() +" game (with user breaker)");
				break;
			case 2:
				try {
					System.out.println("AI did a fake code breaker turn generating code: " + a.codeBreakerTurn(null, null).getCode());
				} catch(Exception e) {
					System.out.println("UNREACHABLE CODE");
				}
				break;
			case 3:
				System.out.println("AI generated random code: " + a.generateSecretCode().getCode());
				break;
			case 4:
				try {
					System.out.println("Input Game's secret code");
					Integer code = sc.nextInt();
					Code c = new Code(code);
					g.getBoard().setSecretCode(c);
					System.out.println("Input Guess to Correct");
					code = sc.nextInt();
					c = new Code(code);
					Correction x = a.correctGuess(c);
					System.out.println(x.getWhitePins()+"W "+x.getWhitePins() + "B");
				} catch (Exception e) {
					System.out.println("UNREACHABLE CODE");
				}
				break;
			case 5:
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