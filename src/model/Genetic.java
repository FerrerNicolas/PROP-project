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
        a possible next guess and the correction of the first guess.
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


    private Code crossover(Code c1, Code c2) {
         Random rnJesus = new Random();
         Code codeRes = new Code();
         int code1 = c1.getCode();
         int code2 = c2.getCode();
         for (int i = 1; i <= 4; ++i) {
             if (rnJesus.nextFloat() < 0.5) {
                 codeRes.setColorAt(i, code1%10);
             }
             else {
                 codeRes.setColorAt(i, code2%10);
             }
             code1 /= 10;
             code2 /= 10;
         }
         return codeRes;
    }

    private Code mutate(Code code) {
        Random rnJesus = new Random();
        int index = (rnJesus.nextInt() % 4) +1;
        int color = rnJesus.nextInt() % AvailableColors.length;
        code.setColorAt(index, AvailableColors[color]);
        return code;
    }

    private Code permute(Code code) {
        Random rnJesus = new Random();
        int cd = code.getCode();
        int index1 = (rnJesus.nextInt(4) + 1);
        int index2 = (rnJesus.nextInt(4) + 1);
        if (index1 == index2) return code;
        int i1 = (int)Math.pow(10, index1);
        int i2 = (int)Math.pow(10, index2);
        int color1 = cd%i1;
        i1 /= 10;
        color1 /= i1;
        int color2 = cd%i2;
        i2 /= 10;
        color2 /= i2;
        code.setColorAt(i1, color2);
        code.setColorAt(i2, color1);
        return code;
    }

    public Genetic(Game g) {
    	super(g);
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
            ArrayList<Code> sons = new ArrayList<Code>();
            for (int i = 0; i < population.size(); ++i) {
                if (i == population.size()-1) {
                    sons.add(population.get(i));
                    break;
                }
                Code son = new Code();
                son = crossover(population.get(i), population.get(i+1));
                if (rng.nextFloat() < 0.03) son = mutate(son);
                if (rng.nextFloat() < 0.03) son = permute(son);
            }
            for (int i = 0; i < sons.size(); ++i) {
                int fitness = 0;
                for (int j = 0; j < previousGuesses.size(); ++j) {
                    fitness += fitnessScore(sons.get(i), previousGuesses.get(j), previousCorrections.get(j));
                }
                if (fitness == 0 && !chosenOnes.contains(sons.get(i)) && !previousGuesses.contains(sons.get(i))) {
                    chosenOnes.add(sons.get(i));
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
