package domini.Board;

import java.util.ArrayList;
import java.util.Scanner;

import exceptions.CodeIsInvalid;
import exceptions.InvalidNumberOfPins;

public class DriverBoard {
	private static Board board;
	private static Game g;
	
	public static void test1(Scanner sc) {
    	System.out.println("Input secret code in numerical form: ");
    	int inp = sc.nextInt();
    	Code code = new Code(inp);
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
    			System.out.println("Guess "+i+": "+guesses.get(i));
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
    			System.out.println("Guess "+i+": "+corrections.get(i));	
    	}
	}
	
	public static void test5(Scanner sc) {
		if (board == null) {
    		System.out.println("Invalid Operation, you need to create a board first using Option 1");
    	}else {
    		System.out.println("Input the guess in numerical form: ");
    		int inp = sc.nextInt();
    		Code guess = new Code(inp);
    		try {
				board.addGuess(guess);
			} catch (CodeIsInvalid e) {
				System.out.println("This should never happen.");
			}
    	}
	}
	
	public static void test6(Scanner sc) {
		if (board == null) {
    		System.out.println("Invalid Operation, you need to create a board first using Option 1");
    	}else {
    		System.out.println("Input number of white pins for the correction to compare to: ");
    		int w = sc.nextInt();
    		System.out.println("Input number of black pins for the correction to compare to: ");
    		int b = sc.nextInt();
    		
    		Correction tmp;
			try {
				tmp = new Correction(w,b);
				int p = correction.getBlackPins();
	    		int q = correction.getWhitePins();
	    		boolean isEqual = correction.equals(tmp);
	    		System.out.println("Compared to " + q + " white pins and " + p + " black pins, got " +isEqual);
			} catch (InvalidNumberOfPins e) {
				System.out.println("Please enter a valid amount of pins.");
	    		test6(sc);
			}
    	}
	}
	
	public static void test7() {
		if (board == null) {
    		System.out.println("Invalid Operation, you need to create a board first using Option 1");
    	}else {
    		System.out.println(correction.toString());
    	}
	}
	
	public static void test8() {
		if (board == null) {
    		System.out.println("Invalid Operation, you need to create a board first using Option 1");
    	}else {
    		System.out.println(correction.toString());
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
