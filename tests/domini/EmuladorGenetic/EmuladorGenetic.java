package domini.EmuladorGenetic;

import exceptions.*;
import model.*;

import java.util.Scanner;

public class EmuladorGenetic {
    public static void main(String[] args) {
        System.out.println("Input a difficulty e|n|h");
        Scanner sc = new Scanner(System.in);
        boolean gameCreated = false;
        Game g = new Game(false, Diff.NORMAL);
        while (!gameCreated) {
            String diff = sc.nextLine();
            if (diff.equalsIgnoreCase("e")) {
                g = new Game(false, Diff.EASY);
                gameCreated = true;
            } else if (diff.equalsIgnoreCase("n")) {
                g = new Game(false, Diff.NORMAL);
                gameCreated = true;
            } else if (diff.equalsIgnoreCase("h")) {
                g = new Game(false, Diff.HARD);
                gameCreated = true;
            }
        }
        System.out.println("Input secret code");
        boolean codeCreated = false;
        Code secretCode = new Code();
        Board b = g.getBoard();
        while (!codeCreated) {
            int scCode = sc.nextInt();
            try {
                secretCode.setCode(scCode);
                if (g.codeIsValid(secretCode)) {
                    codeCreated = true;
                    b.setSecretCode(secretCode);
                }
            } catch (CodeIsInvalid codeIsInvalid) {
                codeIsInvalid.printStackTrace();
            } catch (BadlyFormedCode badlyFormedCode) {
                System.out.println("Wrong code inputted, valid codes are represented by numbers from 0 to 6");
            }
            System.out.println("Input a valid code");
        }
        Genetic gen = new Genetic(g);
        Code guess;
        guess = gen.codeBreakerTurn(null, null);
        try {
            b.addGuess(guess);
        } catch (CodeIsInvalid codeIsInvalid) {
            codeIsInvalid.printStackTrace();
        } catch (UncorrectedGuessExists uncorrectedGuessExists) {
            System.out.println("There is a guess not submitted to correct");
        }
        Correction correction = guess.correct(secretCode);
        try {
            b.addCorrection(correction);
        } catch (NoGuessToBeCorrected correctionIsInvalid) {
            correctionIsInvalid.printStackTrace();
        }

        while (!b.hasWon() && b.turnsDone() < 12) {
            System.out.println(
                    "Tried " + guess.getCode().toString() + ", got " + correction.getBlackPins() + " black pins and " +
                            correction.getWhitePins() + " white pins"
            );
            guess = gen.codeBreakerTurn(guess, correction);

            try {
                b.addGuess(guess);
            } catch (CodeIsInvalid e) {
                System.out.println("Code invalid returned by genetic function");
            } catch (UncorrectedGuessExists uncorrectedGuessExists) {
                System.out.println("Tried to guess the same guess two times");
            }
            correction = guess.correct(secretCode);
            try {
                b.addCorrection(correction);
            } catch (NoGuessToBeCorrected e) {
                System.out.println("Correction returned by correct wrong");
            }
            if (b.hasWon()) System.out.println("The ai guessed the code! " + guess.getCode().toString());

        }
        sc.close();
    }
}
