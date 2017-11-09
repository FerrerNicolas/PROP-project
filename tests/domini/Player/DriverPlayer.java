package domini.Player;

import java.util.Scanner;

//Victor
public class DriverPlayer {
	public static void main (String[] args) {
		Boolean exit = false;
		Player p = new Player("PREDEFINED");
		Scanner sc= new Scanner(System.in);
		while (!exit) {
			System.out.println("Input number for test");
			System.out.println("1. Instance Player");
			System.out.println("2. getPlayerName");
			System.out.println("3. getFinishedGames");
			System.out.println("4. getGamesWon");
			System.out.println("5. getGamesLost");
			System.out.println("6. getTotalScore");
			System.out.println("7. getMaxScore");
			System.out.println("8. getMinGuesses");
			System.out.println("9. updateRecords");
			System.out.println("10. Exit Driver");
			int option = sc.nextInt();
			sc.nextLine();
			switch(option) {
			case 1:
				System.out.println("Input name");
				String n=sc.nextLine();
				p = new Player(n);
				System.out.println("Player created");
				break;
			case 2:
				System.out.println(p.getPlayerName());
				break;
			case 3:
				System.out.println(p.getFinishedGames().toString());
				break;
			case 4:
				System.out.println(p.getGamesWon().toString());
				break;
			case 5:
				System.out.println(p.getGamesLost().toString());
				break;
			case 6:
				System.out.println(p.getTotalScore().toString());
				break;
			case 7:
				System.out.println(p.getMaxScore().toString());
				break;
			case 8:
				System.out.println(p.getMinGuesses().toString());
				break;
			case 9:
				System.out.println("Input w|l for win or lose, the score and the guesses in the game");
				Boolean validInput = false;
				Boolean haswon = false;
				while(!validInput) {
					String A = sc.nextLine();
					validInput = true;
					if(A.equalsIgnoreCase("w")) haswon = true;
					else if (A.equalsIgnoreCase("l")) haswon = false;
					else validInput = false;
					if(!validInput) System.out.println("Invalid Input: input w|l for win or lose");
				}
				Float score = sc.nextFloat();
				Integer guesses = sc.nextInt();
				p.updateRecords(haswon, score, guesses);
				break;
			case 10:
				exit = true;
				sc.close();
				break;
			default:
				System.out.println("Option not valid, please input a number from 1 to 9");
				break;
			}
		}
	}
}