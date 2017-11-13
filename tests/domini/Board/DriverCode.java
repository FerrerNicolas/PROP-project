package domini.Board;

import exceptions.BadlyFormedCode;
import domini.Board.Correction;
import java.util.Scanner;

public class DriverCode {

    private static Code code = new Code();

    public static void showMenu() {
        System.out.println("Tester for code, choose the option with the numeric identifier");
        System.out.println("1. Create a new code with the numbers you want, or change the actual code (Code 0000 by default)");
        System.out.println("2. Change a color of the code at the position you want");
        System.out.println("3. Check if the code has repititions and blanks");
        System.out.println("4. Check the score a code would get compared with another one");
        System.out.println("5. Exit the test");
    }

    public static void main(String[] args) {
        showMenu();
        Scanner sc = new Scanner(System.in);

        while (true) {
            int opt = sc.nextInt();
            sc.nextLine();
            switch (opt) {
                case 1:
                    System.out.println("Choose a 4 digit combination to add as a code");
                    System.out.println("Remember that a code is composed of colors and are represented between 1 and 4");
                    boolean codeCreated = false;
                    while (!codeCreated) {
                        try {
                            code = new Code(sc.nextInt());
                            codeCreated = true;
                        } catch (BadlyFormedCode badlyFormedCode) {
                            System.out.println("Input a valid code");
                            codeCreated = false;
                        }
                    }
                    System.out.println("You created the code: " + code.toString());
                    break;
                case 2:
                    System.out.println("Input a position between 1 and 4");
                    int index = sc.nextInt();
                    while (index < 1 || index > 4) {
                        System.out.println("Input a valid number between 1 and 4");
                        index = sc.nextInt();
                    }
                    System.out.println("Now input a color represented with a number in the interval 0 to 6");
                    int color = sc.nextInt();
                    while (color < 0 || color > 6) {
                        System.out.println("Input a valid number between 0 and 6");
                        color = sc.nextInt();
                    }
                    int oldCode = code.getCode();
                    try {
                        code.setColorAt(index, color);
                    } catch (BadlyFormedCode badlyFormedCode) {
                        badlyFormedCode.printStackTrace();
                    }
                    System.out.println("You previously had the code: "
                            + oldCode + ", you know have the code: " + code.toString());
                    break;
                case 3:
                    boolean repetitions = code.hasRepetitions();
                    boolean blanks = code.hasBlanks();
                    if (!repetitions && !blanks)
                        System.out.println("The code: " + code.toString() + " has no repetitions nor blanks");
                    else if(!repetitions && blanks)
                        System.out.println("The code: " + code.toString() + " has no repetitions but has one blank or more");
                    else if (repetitions && !blanks)
                        System.out.println("The code: " + code.toString() + " has repetitions but has no blanks");
                    else System.out.println("The code: " +code.toString() + " has both repetitions and blanks");
                    break;
                case 4:
                    System.out.println("Input a new code to compare to the code you actually have");
                    codeCreated = false;
                    Code code2 = new Code();
                    while (!codeCreated) {
                        try {
                            code2 = new Code(sc.nextInt());
                            codeCreated = true;
                        } catch (BadlyFormedCode badlyFormedCode) {
                            System.out.println("Input a valid code");
                            codeCreated = false;
                        }
                    }
                    Correction correction = code.correct(code2);
                    System.out.println("The code " + code.toString() + " compared to the code " + code2.toString()
                    + " got a correction value of " + correction.getBlackPins() + "black pins and "
                            + correction.getWhitePins() + "white pins");
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choose one of the options between 1 and 5");
                    break;
            }
            System.out.println();
            showMenu();
        }

    }

}
