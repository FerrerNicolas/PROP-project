package domini.Board;

import java.util.ArrayList;
import java.util.Scanner;

import exceptions.*;

public class DriverBoard {
	private static Board board;
	private static Game g;
	
	public static void test1(Scanner sc) {
    	System.out.println("Input secret code in numerical form: ");
    	int inp = sc.nextInt();
    	//Added try and catch to this function
		Code code = new Code();
		try {
			code = new Code(inp);
		} catch (BadlyFormedCode e) {

		}
		board = new Board(g);
    	try {
			board.setSecretCode(code);
		} catch (CodeIsInvalid e) {
			System.out.println("This will never happen.");
		}
	}
	
	public static void test2() {
		if (board == null) {
    		System.out.println("Invalid Operation, you need to create a board first using Option 1");
    	}else {
    		Code code = board.getSecretCode();
    		int p = code.getCode();
    		System.out.println("Secret code: "+p);
    	}
	}
	
	public static void test3() {
		if (board == null) {
    		System.out.println("Invalid Operation, you need to create a board first using Option 1");
    	}else {
    		ArrayList<Code> guesses = board.getGuesses();
    		if (guesses.size() == 0)
    			System.out.println("There are no guesses to print.");
    		for (int i = 0; i < guesses.size(); i++)
    			System.out.println("Guess "+i+": "+guesses.get(i).getCode());
    	}
	}
	
	public static void test4(Scanner sc) {
		if (board == null) {
    		System.out.println("Invalid Operation, you need to create a board first using Option 1");
    	}else {
    		ArrayList<Correction> corrections = board.getCorrections();
    		if (corrections.size() == 0)
    			System.out.println("There are no corrections to print.");
    		for (int i = 0; i < corrections.size(); i++)
    			System.out.println("Correction "+i+": "+corrections.get(i).toString());	
    	}
	}
	
	public static void test5(Scanner sc) {
		if (board == null) {
    		System.out.println("Invalid Operation, you need to create a board first using Option 1");
    	}else {
    		System.out.println("Input the guess in numerical form: ");
    		int inp = sc.nextInt();
            Code guess = null;
            //Added try and catch here too, so I can test Code
            try {
                guess = new Code(inp);
            } catch (BadlyFormedCode e) {

            }
            try {
				board.addGuess(guess);
			} catch (CodeIsInvalid e) {
				System.out.println("This should never happen.");
			} catch (UncorrectedGuessExists e) {
				System.out.println("You can't add another guess when one without a correction exists. Guess was not added.");
			}
    	}
	}
	
	public static void test6(Scanner sc) {
		if (board == null) {
    		System.out.println("Invalid Operation, you need to create a board first using Option 1");
    	}else {
    		System.out.println("Input the white pins of the correction: ");
    		int w = sc.nextInt();
    		System.out.println("Input the black pins of the correction: ");
    		int b = sc.nextInt();
    		try {
				Correction corr = new Correction(w,b);
				board.addCorrection(corr);
			} catch (InvalidNumberOfPins e) {
				System.out.println("Please input a valid number of pins.");
				test6(sc);
			} catch (NoGuessToBeCorrected e) {
				System.out.println("There is no guess pending to be corrected. Correction not added.");
			}
    	}
	}
	
	public static void test7() {
		if (board == null) {
    		System.out.println("Invalid Operation, you need to create a board first using Option 1");
    	}else {
    		System.out.println("The fact that the game has been won with the last correction is "+board.hasWon());
    	}
	}
	
	public static void test8() {
		if (board == null) {
    		System.out.println("Invalid Operation, you need to create a board first using Option 1");
    	}else {
    		System.out.println("A total of "+board.turnsDone()+" turns have been done.");
    	}
	}
	
	public static void printMenu() {
		System.out.println("Choose what to test:");
        System.out.println("1- Create a (or substitute current) Board (needed for tests 2 through 8).");
        System.out.println("2- Get secret code");
        System.out.println("3- Get all guesses");
        System.out.println("4- Get all corrections");
        System.out.println("5- Add a guess");
        System.out.println("6- Add a guess' correction");
        System.out.println("7- Check if the game was won with the last correction");
        System.out.println("8- Get the number of turns done so far (based on corrections)");
        System.out.println("9- End test");
	}
	
	public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        g = new Game();
        
        boolean theresMore = true;
        while (theresMore) {
        	
        	printMenu();
        	
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    test1(sc);
                    break;
                case 2:
                	test2();
                    break;
                case 3:
                    test3();
                    break;
                case 4:
                    test4(sc);
                    break;
                case 5:
                    test5(sc);
                    break;
                case 6:
                    test6(sc);
                    break;
                case 7:
                	test7();
                	break;
                case 8:
                	test8();
                    break;
                case 9:
                	theresMore = false;
                    break;
                default:
                    System.out.println("Invalid Option, try again.");
            }
        }
    }
}
