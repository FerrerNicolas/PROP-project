package domini.EmuladorGenetic;

import exceptions.*;
import model.*;

import java.util.Date;
import java.util.Random;
import java.util.Vector;

public class EmuladorGeneticStats {

    public static void main(String[] args) {

        int[] AvailableColors = {1, 2, 3, 4, 5, 6};
        int[] AvailableColorsHard = {0, 1, 2, 3, 4, 5, 6};

        System.out.println("Initiating the genetic tester. Every test does a million code tries");
        Long easyTime = 0L;
        Long mediumTime = 0L;
        Long hardTime = 0L;


        Long easyMinTime = Long.MAX_VALUE;
        Long easyMaxTime = Long.MIN_VALUE;

        Long mediumMinTime = Long.MAX_VALUE;
        Long mediumMaxTime = Long.MIN_VALUE;

        Long hardMinTime = Long.MAX_VALUE;
        Long hardMaxTime = Long.MIN_VALUE;

        int easyTurns = 0;
        int mediumTurns = 0;
        int hardTurns = 0;

        System.out.println("Starting easy tests");
        Random rng = new Random(12324);
        int percentatge = 0;
        /*
        for (int i = 0; i < 100000; i++) {
            if (i %10000 == 0) {
                ++percentatge;
                System.out.println("Completed: " + percentatge + " %");
            }
            Game g = new Game(false, Diff.EASY, false);
            Board b = g.getBoard();
            Vector<Integer> codeNum = new Vector<>();
            for (int j = 0; j < 4; ++j) {
                int nextNum = rng.nextInt(AvailableColors.length);
                while (codeNum.contains(AvailableColors[nextNum])) nextNum = rng.nextInt(AvailableColors.length);
                codeNum.add(AvailableColors[nextNum]);
            }
            Code scCode = new Code();
            try {
                scCode.setCode(codeNum);
            } catch (BadlyFormedCode badlyFormedCode) { }
            try {
                b.setSecretCode(scCode);
            } catch (CodeIsInvalid codeIsInvalid) {
                System.out.println("Failed to set secret code");
            } catch (SecretCodeAlreadySet secretCodeAlreadySet) {
                secretCodeAlreadySet.printStackTrace();
            }
            Genetic gen = new Genetic(g);
            Code answer;
            Long startTime = System.currentTimeMillis();
            answer = gen.codeBreakerTurn(null, null);
            try {
                b.addGuess(answer);
            } catch (CodeIsInvalid codeIsInvalid) {
                codeIsInvalid.printStackTrace();
            } catch (UncorrectedGuessExists uncorrectedGuessExists) {
                uncorrectedGuessExists.printStackTrace();
            }
            Correction correction = scCode.correct(answer);
            try {
                b.addCorrection(correction);
            } catch (NoGuessToBeCorrected noGuessToBeCorrected) {
            } catch (IncorrectCorrection incorrectCorrection) {
            }
            while (!b.hasWon()) {
                answer = gen.codeBreakerTurn(answer, correction);
                try {
                    b.addGuess(answer);
                } catch (CodeIsInvalid codeIsInvalid) {
                    codeIsInvalid.printStackTrace();
                } catch (UncorrectedGuessExists uncorrectedGuessExists) {
                    System.out.println("Tried to add a guess with a previous one without correction");
                }
                correction = answer.correct(scCode);
                try {
                    b.addCorrection(correction);
                } catch (NoGuessToBeCorrected noGuessToBeCorrected) {
                } catch (IncorrectCorrection incorrectCorrection) {

                }
                ++easyTurns;
            }
            Long finalTime = System.currentTimeMillis();
            Long gameTime = finalTime - startTime;
            if (gameTime < easyMinTime) easyMinTime = gameTime;
            if (gameTime > easyMaxTime) easyMaxTime = gameTime;
            easyTime += gameTime;
        }

        System.out.println("Easy test finished");
        System.out.println("In playing 1milion games in easy the algorithm took " + easyTime + "ms and " + easyTurns
            + "turns. That makes a median of " + easyTime / 1000000 + "ms and " + easyTurns/1000000 + "turns. " +
                "The minimum time for a game was " + easyMinTime + "ms and the maximum " + easyMaxTime + "ms");
           */
        System.out.println("Starting normal tests");
        percentatge = 0;
        for (int i = 0; i < 1000000; ++i) {
            if (i %10000 == 0) {
                ++percentatge;
                System.out.println("Completed: " + percentatge + " %");
            }
            Game g = new Game(false, Diff.NORMAL, false);
            Board b = g.getBoard();
            Vector<Integer> codeNum = new Vector<>();
            for (int j = 0; j < 4; ++j) {
                int nextNum = rng.nextInt(AvailableColors.length);
                codeNum.add(AvailableColors[nextNum]);
            }
            Code scCode = new Code();
            try {
                scCode.setCode(codeNum);
            } catch (BadlyFormedCode badlyFormedCode) { }
            try {
                b.setSecretCode(scCode);
            } catch (CodeIsInvalid codeIsInvalid) {
                System.out.println("Failed to set secret code");
            } catch (SecretCodeAlreadySet secretCodeAlreadySet) {
                secretCodeAlreadySet.printStackTrace();
            }
            Genetic gen = new Genetic(g);
            Code answer;
            Long startTime = System.currentTimeMillis();
            answer = gen.codeBreakerTurn(null, null);
            try {
                b.addGuess(answer);
            } catch (CodeIsInvalid codeIsInvalid) {
                codeIsInvalid.printStackTrace();
            } catch (UncorrectedGuessExists uncorrectedGuessExists) {
                uncorrectedGuessExists.printStackTrace();
            }
            Correction correction = scCode.correct(answer);
            try {
                b.addCorrection(correction);
            } catch (NoGuessToBeCorrected noGuessToBeCorrected) {
            } catch (IncorrectCorrection incorrectCorrection) {
            }
            while (!b.hasWon()) {
                answer = gen.codeBreakerTurn(answer, correction);
                try {
                    b.addGuess(answer);
                } catch (CodeIsInvalid codeIsInvalid) {
                    codeIsInvalid.printStackTrace();
                } catch (UncorrectedGuessExists uncorrectedGuessExists) {
                    System.out.println("Tried to add a guess with a previous one without correction");
                }
                correction = answer.correct(scCode);
                try {
                    b.addCorrection(correction);
                } catch (NoGuessToBeCorrected noGuessToBeCorrected) {
                } catch (IncorrectCorrection incorrectCorrection) {

                }
                ++mediumTurns;
            }
            Long finalTime = System.currentTimeMillis();
            Long gameTime = finalTime - startTime;
            if (gameTime < mediumMinTime) mediumMinTime = gameTime;
            if (gameTime > mediumMaxTime) mediumMaxTime = gameTime;
            mediumTime += gameTime;
        }

        System.out.println("Medium test finished");
        System.out.println();
        System.out.println("In playing 1milion games in normal the algorithm took " + mediumTime + "ms and " + mediumTurns
                + "turns. That makes a median of " + mediumTime / 1000000 + "ms and " + mediumTurns/1000000 + "turns per game. "
                + "The minimum time for a game was " + mediumMinTime + "ms and the maximum " + mediumMaxTime + "ms");

        System.out.println("Starting hard tests");

        /*percentatge = 0;
        for (int i = 0; i < 10000; ++i) {
            if (i %100 == 0) {
                ++percentatge;
                System.out.println("Completed: " + percentatge + " %");
            }
            Game g = new Game(false, Diff.HARD, false);
            Board b = g.getBoard();
            Vector<Integer> codeNum = new Vector<>();
            for (int j = 0; j < 5; ++j) {
                int nextNum = rng.nextInt(AvailableColors.length);
                codeNum.add(AvailableColorsHard[nextNum]);
            }
            Code scCode = new Code();
            try {
                scCode.setCode(codeNum);
            } catch (BadlyFormedCode badlyFormedCode) { }
            try {
                b.setSecretCode(scCode);
            } catch (CodeIsInvalid codeIsInvalid) {
                System.out.println("Failed to set secret code");
            } catch (SecretCodeAlreadySet secretCodeAlreadySet) {
                secretCodeAlreadySet.printStackTrace();
            }
            Genetic gen = new Genetic(g);
            Code answer;
            Long startTime = System.currentTimeMillis();
            answer = gen.codeBreakerTurn(null, null);
            try {
                b.addGuess(answer);
            } catch (CodeIsInvalid codeIsInvalid) {
                codeIsInvalid.printStackTrace();
            } catch (UncorrectedGuessExists uncorrectedGuessExists) {
                uncorrectedGuessExists.printStackTrace();
            }
            Correction correction = scCode.correct(answer);
            try {
                b.addCorrection(correction);
            } catch (NoGuessToBeCorrected noGuessToBeCorrected) {
            } catch (IncorrectCorrection incorrectCorrection) {
            }
            while (!b.hasWon()) {
                answer = gen.codeBreakerTurn(answer, correction);
                try {
                    b.addGuess(answer);
                } catch (CodeIsInvalid codeIsInvalid) {
                    codeIsInvalid.printStackTrace();
                } catch (UncorrectedGuessExists uncorrectedGuessExists) {
                    System.out.println("Tried to add a guess with a previous one without correction");
                }
                correction = answer.correct(scCode);
                try {
                    b.addCorrection(correction);
                } catch (NoGuessToBeCorrected noGuessToBeCorrected) {
                } catch (IncorrectCorrection incorrectCorrection) {

                }
                ++hardTurns;
            }
            Long finalTime = System.currentTimeMillis();
            Long gameTime = finalTime - startTime;
            if (gameTime < hardMinTime) hardMinTime = gameTime;
            if (gameTime > hardMaxTime) hardMaxTime = gameTime;
            hardTime += gameTime;
        }

        System.out.println("Hard test finished");
        System.out.println();
        System.out.println("In playing 1milion games in hard the algorithm took " + hardTime + "ms and " + hardTurns
                + "turns. That makes a median of " + hardTime / 1000000 + "ms and " + hardTurns/1000000 + "turns per game. "
                + "The minimum time for a game was " + hardMinTime + "ms and the maximum " + hardMaxTime + "ms");
    */
    }
}
