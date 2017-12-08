package domini.Ai;

import exceptions.BadlyFormedCode;
import model.Ai;
import model.*;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.pow;

//Javi
public class Genetic extends Ai {
    private int[] AvailableColors;
    private ArrayList<Code> population;
    private ArrayList<Code> chosenOnes;
    private ArrayList<Code> previousGuesses;
    private int codeSize;
    private int pop_size = 100;
    private int max_generations = 60;

    public Genetic(Game g) {
        super(g);
        //We initialize the array of available colors.

        if (!game.getDifficulty().equals(Diff.HARD)) {
            AvailableColors = new int[]{1, 2, 3, 4, 5, 6};
            codeSize = 4;
        } else {
            AvailableColors = new int[]{0, 1, 2, 3, 4, 5, 6};
            codeSize = 5;
        }
    }

    /*
    Takes a random interval from the code and inverts the positions.
     */

    /*
        Assigns a fitnessScore to a guess, the fitness score is used to find a suitable
        code to be the next guess.
        For the code to assign a suitable fitnessScore it gets as parameters a previous guess
        a possible next guess and the correction of the first guess.
     */
    private int fitnessScore(Code guess) {
        ArrayList<Correction> previousCorrection = game.getBoard().getCorrections();
        int wPinsDifference = 0;
        int bPinsDifference = 0;
        for (int i = 0; i < previousGuesses.size(); ++i) {
            Correction comparison = previousGuesses.get(i).correct(guess);
            wPinsDifference += abs(previousCorrection.get(i).getWhitePins() - comparison.getWhitePins());

            bPinsDifference += abs(previousCorrection.get(i).getBlackPins() - comparison.getBlackPins());
        }
        return (wPinsDifference + bPinsDifference);
    }

    private  Code reverse(Code code) {
        Random rng = new Random();
        int i1 = rng.nextInt(codeSize) + 1;
        int i2 = rng.nextInt(codeSize) + 1;
        int min = StrictMath.min(i1, i2);
        int max = StrictMath.max(i1, i2);
        int interval = code.getCode();
        int maxInterval = (int) pow(10, max);
        interval = interval % maxInterval;
        int minInterval = (int) pow(10, min - 1);
        interval = interval / minInterval;
        for (int i = 0; i <= max - min; ++i) {
            int color = interval % 10;
            try {
                code.setColorAt(max - i, color);
            } catch (BadlyFormedCode badlyFormedCode) {

            }
            interval /= 10;
        }
        return code;
    }


    private Code crossover(Code c1, Code c2) {
        Random rnJesus = new Random();
        Code codeRes = new Code();
        int code1 = c1.getCode();
        int code2 = c2.getCode();
        for (int i = 1; i <= codeSize; ++i) {
            if (rnJesus.nextFloat() < 0.5) {
                try {
                    codeRes.setColorAt(i, code1 % 10);
                } catch (BadlyFormedCode badlyFormedCode) {

                }
            } else {
                try {
                    codeRes.setColorAt(i, code2 % 10);
                } catch (BadlyFormedCode e) {
                }
            }
            code1 /= 10;
            code2 /= 10;
        }
        return codeRes;
    }

    private Code mutate(Code code) {
        Random rnJesus = new Random();
        int index = (rnJesus.nextInt(codeSize) + 1);
        int color = rnJesus.nextInt(AvailableColors.length);
        try {
            code.setColorAt(index, AvailableColors[color]);
        } catch (BadlyFormedCode badlyFormedCode) {
            badlyFormedCode.printStackTrace();
        }
        return code;
    }

    private Code permute(Code code) {
        Random rnJesus = new Random();
        int cd = code.getCode();
        for (int i = 0; i < codeSize; ++i) {
            int index1 = (rnJesus.nextInt(codeSize) + 1);
            int index2 = (rnJesus.nextInt(codeSize) + 1);
            if (index1 == index2) return code;
            int i1 = (int) Math.pow(10, index1);
            int i2 = (int) Math.pow(10, index2);
            int color1 = cd % i1;
            i1 /= 10;
            color1 /= i1;
            int color2 = cd % i2;
            i2 /= 10;
            color2 /= i2;
            try {
                code.setColorAt(index1, color2);
                code.setColorAt(index2, color1);
            } catch (BadlyFormedCode badlyFormedCode) {

            }
        }
        return code;
    }

    public Code codeBreakerTurn(Code code, Correction correction) {
        if (code == null && correction == null) {
            if (game.getDifficulty() == Diff.EASY) {
                try {
                    return new Code(1234);
                } catch (BadlyFormedCode badlyFormedCode) {

                }
            } else if (game.getDifficulty() == Diff.NORMAL){
                try {
                    return new Code(1123);
                } catch (BadlyFormedCode badlyFormedCode) {
                }
            }
            else {
                try {
                    return new Code(11223);
                } catch (BadlyFormedCode e) {

                }
            }
        }
        population = new ArrayList<>();
        chosenOnes = new ArrayList<>();
        //first we fill the population with random codes
        Random rng = new Random();
        if (population.isEmpty() || population.size() < pop_size) {
            for (int i = population.size(); i < pop_size; ++i) {
                Code c = new Code();
                try {
                    c = new Code(0, codeSize);
                } catch (BadlyFormedCode e) {

                }
                for (int j = 1; j <= codeSize; ++j) {
                    try {
                        c.setColorAt(j, AvailableColors[rng.nextInt(AvailableColors.length)]);
                    } catch (BadlyFormedCode badlyFormedCode) {
                        System.out.println("AvailableColors array wrongly initialized");
                    }
                }
                if (codeSize == c.getCodeSize()) population.add(c);
            }
        }
        int gen_num = 1;
        previousGuesses = game.getBoard().getGuesses();
        ArrayList<Code> sons = new ArrayList<Code>();
        while (chosenOnes.size() <= pop_size && gen_num <= max_generations) {
            for (int i = 0; i < population.size(); ++i) {
                if (i == population.size() - 1) {
                    sons.add(population.get(i));
                    break;
                }
                Code son;
                son = crossover(population.get(i), population.get(i + 1));
                if (rng.nextFloat() < 0.03) {
                    son = mutate(son);

                }
                if (rng.nextFloat() < 0.03) {
                    son = permute(son);
                }
                if (rng.nextFloat() < 0.02) {
                    son = reverse(son);
                }
                sons.add(son);
            }
            for (int i = 0; i < sons.size(); ++i) {
                Code possibleGuess = sons.get(i);
                int fitness = fitnessScore(possibleGuess);
                if (fitness == 0 && !chosenOnes.contains(possibleGuess) && game.codeIsValid(possibleGuess)) {
                    chosenOnes.add(possibleGuess);
                    if (chosenOnes.size() >= pop_size) break;
                }
            }
            population.clear();
            population.addAll(chosenOnes);
            if (population.isEmpty() || population.size() < pop_size) {
                for (int i = population.size(); i < pop_size; ++i) {
                    Code c = new Code();
                    for (int j = 1; j <= codeSize; ++j) {
                        try {
                            c.setColorAt(j, AvailableColors[rng.nextInt(AvailableColors.length)]);
                        } catch (BadlyFormedCode badlyFormedCode) {
                            badlyFormedCode.printStackTrace();
                        }
                    }
                    population.add(c);
                }
            }
            ++gen_num;
            sons.clear();
        }
        //Right now code searches for a random guess, best solution would be to add the most similar to the previous
        //guesses
        Code nextGuess;
        nextGuess = chosenOnes.get(rng.nextInt(chosenOnes.size()));
        while (previousGuesses.contains(nextGuess)) {
            nextGuess = chosenOnes.get(rng.nextInt(chosenOnes.size()));
        }
        return nextGuess;
    }


}
