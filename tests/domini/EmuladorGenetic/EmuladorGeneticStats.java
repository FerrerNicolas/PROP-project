package domini.EmuladorGenetic;

import exceptions.BadlyFormedCode;
import exceptions.CodeIsInvalid;
import exceptions.NoGuessToBeCorrected;
import exceptions.UncorrectedGuessExists;
import model.*;

import java.util.Random;

public class EmuladorGeneticStats {
    public static int playedGames = 0;
    public static int easyGames = 0;
    public static int mediumGames = 0;
    public static int hardGames = 0;
    public static int playedTurns = 0;
    public static int easyTurns = 0;
    public static int mediumTurns = 0;
    public static int hardTurns = 0;
    static int[] AvailableColors = {1, 2, 3, 4, 5, 6};
    static int[] AvailableColorsHard = {0, 1, 2, 3, 4, 5, 6};

    public static void main(String[] args) {
        System.out.println("Initiating the genetic tester");
        Game g;
        Random rng = new Random();
        for (int i = 0; i < 1; i++) {
            if ((i % 3) == 0) {
                g = new Game(false, Diff.EASY);
                if (g.getDifficulty().equals(Diff.EASY)) System.out.println("Created an easy game");
            } else if ((i % 3) == 1) {
                g = new Game(false, Diff.NORMAL);
                System.out.println("Created a medium game");
            } else {
                g = new Game(false, Diff.HARD);
                System.out.println("Created a hard game");
            }
            Board b = new Board(g);
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
            ++playedGames;
            if (i % 3 == 0) {
                easyTurns += b.turnsDone();
                ++easyGames;
            } else if (i % 2 == 1) {
                mediumTurns += b.turnsDone();
                ++mediumGames;
            } else {
                hardTurns += b.turnsDone();
                ++hardTurns;
            }
        }
        System.out.println("Played " + easyGames + " games at easy difficulty, it took in total " + easyTurns + " to complete all games");
        double turnsForGameEasy = easyTurns / easyGames;

        System.out.println("That is " + turnsForGameEasy + " turns in average per game");
        /*System.out.println("Played " + mediumGames + " games at normal difficulty, it took in total " + mediumTurns + " to complete all games");
        double turnsForMediumGames = mediumTurns / mediumGames;
        System.out.println("That is " + turnsForMediumGames + " turns in average to complete a game!");
        System.out.println("Played " + hardGames + " game at hard difficulty, it took in total " + hardTurns + " to complete all games");
        double turnsForHardGames = hardTurns / hardGames;
        System.out.println("That is " + turnsForHardGames + " turns in average to complete a game!");
    */
    }
}
