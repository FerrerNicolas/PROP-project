package model;

import java.util.ArrayList;
import java.util.Random;

public class Genetic  extends Ai{

    private int[] AvailableColors;
    private ArrayList<Code> population;
    private ArrayList<Code> chosenOnes;

    private int pop_size = 60;
    private int max_generations = 100;

    /*
        Assigns a fitnessScore to a guess, the fitness score is used to find a suitable
        code to be the next guess.
        For the code to assign a suitable fitnessScore it gets as parameters a previous guess
        a possible next guess and the correction the first guess got.
     */
    private int fitnessScore(Code guess, Code guessed, Correction correction) {
        Correction comparison = guessed.correct(guess);

        int wPinsComparison = comparison.getWhitePins();
        int wPinsCorrection = correction.getWhitePins();
        int wPinsDifference = wPinsCorrection - wPinsComparison;

        int bPinsComparison = comparison.getBlackPins();
        int bPinsCorrrection = correction.getWhitePins();
        int bPinsDifference = bPinsCorrrection - bPinsComparison;

        return wPinsDifference + bPinsDifference;
    }

    public Genetic() {
        //We initialize the array of available colors.

        if (game.getDifficulty() != Diff.HARD)
            AvailableColors = new int[]{1, 2, 3, 4, 5, 6};
        else
            AvailableColors = new int[]{0, 1, 2, 3, 4, 5, 6};
    }

    public Code codeBreakerTurn(Code code, Correction correction) {
        Code guess = new Code();
        if (code == null && correction == null) {

            if (game.getDifficulty() == Diff.EASY) {
                return new Code(1234);
            }
            else {
                return new Code(1123);
            }
        }
        chosenOnes.clear();
        //first we fill the population with random codes
        Random rng = new Random();
        if (population.isEmpty() || population.size() < pop_size) {
            int i = population.size();
            while( i < pop_size) {
                Code c = new Code(rng.nextInt() % 7000);
                if (game.codeIsValid(c)) {
                    population.add(c);
                    ++i;
                }
            }
        }
        int gen_num = 1;
        ArrayList<Code> previousGuesses= game.getBoard().getGuesses();
        ArrayList<Correction> previousCorrections = game.getBoard().getCorrections();
        while (chosenOnes.size() < pop_size || gen_num < max_generations) {
            //To-do: add mutation functions
            for (int i = 0; i < population.size(); ++i) {
                int fitness = 0;
                for (int j = 0; j < previousGuesses.size(); ++j) {
                    fitness += fitnessScore(population.get(i), previousGuesses.get(j), previousCorrections.get(j));
                }
                if (fitness == 0 && !chosenOnes.contains(population.get(i)) && !previousGuesses.contains(population.get(i))) {
                    chosenOnes.add(population.get(i));
                    if (chosenOnes.size() > pop_size) break;
                }
            }
            population.clear();
            population.addAll(chosenOnes);
            ++gen_num;
        }
        //Right now code searches for a random guess, best solution would be to add the most similar to the previous
        //guesses

        return chosenOnes.get(rng.nextInt() % chosenOnes.size());
    }
}
