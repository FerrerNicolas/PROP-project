package domini.Correction;
//Guillem
import java.util.*;

import exceptions.InvalidNumberOfPins;

public class DriverCorrection {
	private static Correction correction;
	
	public static void test1(Scanner sc) {
    	System.out.println("Input number of white pins for the correction to create: ");
    	int w = sc.nextInt();
    	System.out.println("Input number of black pins for the correction to create: ");
    	int b = sc.nextInt();
    	try {
			correction = new Correction(w,b);
			System.out.println("Created correction with " + w + " white pins and " + b + " black pins");
		} catch (InvalidNumberOfPins e) {
			System.out.println("Please enter a valid amount of pins.");
    		test1(sc);
		}
	}
	public static void test2() {
		if (correction == null) {
    		System.out.println("Invalid Operation, you need to create a correction first using Option 1");
    	}else {
    		int p = correction.getWhitePins();
    		System.out.print("Number of white pins: ");
    		System.out.println(p);
    	}
	}
	public static void test3() {
		if (correction == null) {
    		System.out.println("Invalid Operation, you need to create a correction first using Option 1");
    	}else {
    		int p = correction.getBlackPins();
    		System.out.print("Number of black pins: ");
    		System.out.println(p);
    	}
	}
	public static void test4(Scanner sc) {
		if (correction == null) {
    		System.out.println("Invalid Operation, you need to create a correction first using Option 1");
    	}else {
    		System.out.println("Input number of pins: ");
    		int q = sc.nextInt();
    		
    		try {
				correction.setWhitePins(q);
				int p = correction.getWhitePins();
	    		System.out.println("Set number of white pins to: "+p);
			} catch (InvalidNumberOfPins e) {
				System.out.println("Please enter a valid amount of pins.");
	    		test4(sc);
			}	
    	}
	}
	public static void test5(Scanner sc) {
		if (correction == null) {
    		System.out.println("Invalid Operation, you need to create a correction first using Option 1");
    	}else {
    		System.out.println("Input number of pins: ");
    		int q = sc.nextInt();
    		try {
				correction.setBlackPins(q);
				int p = correction.getBlackPins();
	    		System.out.println("Set number of black pins to: "+p);
			} catch (InvalidNumberOfPins e) {
				System.out.println("Please enter a valid amount of pins.");
	    		test5(sc);
			}
    		
    	}
	}
	public static void test6(Scanner sc) {
		if (correction == null) {
    		System.out.println("Invalid Operation, you need to create a correction first using Option 1");
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
	
	public static void printMenu() {
		System.out.println("Choose what to test:");
        System.out.println("1- Create a correction (needed for tests 2 through 6).");
        System.out.println("2- Get number of white pins");
        System.out.println("3- Get number of black pins");
        System.out.println("4- Set number of white pins");
        System.out.println("5- Set number of black pins");
        System.out.println("6- Check wheter the correction equals another");
        System.out.println("7- End test");
	}
	
	public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

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
                	theresMore = false;
                    break;
                default:
                    System.out.println("Invalid Option, try again.");
            }
        }
    }
}
