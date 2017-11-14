package domini.EmuladorGenetic;

import exceptions.BadlyFormedCode;
import exceptions.CodeIsInvalid;
import exceptions.NoGuessToBeCorrected;
import exceptions.UncorrectedGuessExists;
import model.*;

import java.util.Random;

public class EmuladorGeneticStats {

    public static void main(String[] args) {
        double playedGames = 0.0;
        double easyGames = 0.0;
        double mediumGames = 0.0;
        double hardGames = 0.0;
        double playedTurns = 0.0;
        double easyTurns = 0.0;
        double mediumTurns = 0.0;
        double hardTurns = 0.0;
        int[] AvailableColors = {1, 2, 3, 4, 5, 6};
        int[] AvailableColorsHard = {0, 1, 2, 3, 4, 5, 6};

        System.out.println("Initiating the genetic tester");
        Game g = new Game(false, Diff.HARD);
        Random rng = new Random(1234);
        for (int i = 0; i < 300; i++) {
            if ((i % 3) == 0) {
                g = new Game(false, Diff.EASY);
            } else if ((i % 3) == 1) {
                g = new Game(false, Diff.NORMAL);
                System.out.println("Created a medium game");
            } else {
                g = new Game(false, Diff.HARD);
                System.out.println("Created a hard game");
            }
            Board b = g.getBoard();
            int codeNum = 0;
            int[] AvailableColorsEasy = AvailableColors.clone();
            for (int j = 0; j < 4; ++j) {
                int color;
                if (i % 3 == 2) color = AvailableColorsHard[rng.nextInt(AvailableColorsHard.length)];
                else if (i % 3 == 0) {
                    int index = rng.nextInt(AvailableColorsEasy.length);
                    color = AvailableColorsEasy[index];
                    while (color == -1) {
                        index = rng.nextInt(AvailableColorsEasy.length);
                        color = AvailableColorsEasy[index];
                    }
                    AvailableColorsEasy[index] = -1;
                } else color = AvailableColors[rng.nextInt(AvailableColors.length)];
                codeNum *= 10;
                codeNum += color;
            }

            Code scCode = new Code();
            try {
                scCode = new Code(codeNum);
            } catch (BadlyFormedCode badlyFormedCode) {
                System.out.println("Tried to create a wrong code");
            }
            try {
                b.setSecretCode(scCode);
            } catch (CodeIsInvalid codeIsInvalid) {
                System.out.println("Failed to set secret code");
            }
            System.out.println("Answering " + b.getSecretCode().toString());
            Genetic gen = new Genetic(g);
            Code answer;
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
                noGuessToBeCorrected.printStackTrace();
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
                    noGuessToBeCorrected.printStackTrace();
                }
                System.out.println("Played the answer " + answer.toString() + " in the turn " + b.turnsDone() + " and got the answer "
                        + correction.getBlackPins() + "B pins and " + correction.getWhitePins() + "W pins.");
            }
            System.out.println("Won the game answering to the code " + b.getSecretCode().toString() + " in " + b.turnsDone() + " turns.");
            if (i % 3 == 0) {
                easyTurns += b.turnsDone();
                ++easyGames;
            } else if (i % 2 == 1) {
                mediumTurns += b.turnsDone();
                ++mediumGames;
            } else {
                hardTurns += b.turnsDone();
                ++hardGames;
            }
            ++playedGames;
            playedTurns += b.turnsDone();
        }
        System.out.println("Played " + easyGames + " games at easy difficulty, it took in total " + easyTurns + " to complete all games");
        double turnsForGameEasy = easyTurns / easyGames;
        System.out.println("That is " + turnsForGameEasy + " turns in average per game in easy difficulty");

        System.out.println("Played " + mediumGames + " games at normal difficulty, it took in total " + mediumTurns + " to complete all games");
        double turnsForMediumGames = mediumTurns / mediumGames;
        System.out.println("That is " + turnsForMediumGames + " turns in average to complete a game in medium difficulty");

        System.out.println("Played " + hardGames + " game at hard difficulty, it took in total " + hardTurns + " to complete all games");
        double turnsForHardGames = hardTurns / hardGames;
        System.out.println("That is " + turnsForHardGames + " turns in average to complete a game in hard difficulty");

        System.out.println();
        double average = playedTurns / playedGames;
        System.out.println("Total average of turns per game is " + average);
    }
}
