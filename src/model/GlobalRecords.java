package model;

import java.io.Serializable;
import java.util.ArrayList;

public class GlobalRecords implements Serializable {//Author:Luis

    ArrayList<Tuple> globalRecords = new ArrayList<Tuple>();

    boolean updated = false;

    Tuple newFinishedGamesScore;
    Tuple newGamesLostScore;
    Tuple newGamesWonScore;
    Tuple newMaxScore;
    Tuple newMinGuessesScore;
    Tuple newTotalScore;

    public boolean update(Player p) {
        //public boolean update (... can be usefull because we want to save data every time that a player is updated); same for rankings

        if (globalRecords.isEmpty()) {

            newFinishedGamesScore = new Tuple(p.getPlayerName(), p.getFinishedGames());
            newGamesLostScore = new Tuple(p.getPlayerName(), p.getGamesLost());
            newGamesWonScore = new Tuple(p.getPlayerName(), p.getGamesWon());
            newMaxScore = new Tuple(p.getPlayerName(), p.getMaxScore());
            newMinGuessesScore = new Tuple(p.getPlayerName(), p.getMinGuesses());
            newTotalScore = new Tuple(p.getPlayerName(), p.getTotalScore());

            globalRecords.add(0, newFinishedGamesScore);
            globalRecords.add(1, newGamesLostScore);
            globalRecords.add(2, newGamesWonScore);
            globalRecords.add(3, newMaxScore);
            globalRecords.add(4, newMinGuessesScore);
            globalRecords.add(5, newTotalScore);

            updated = true;
            return updated;

        } else if (globalRecords.get(0).getPlayerName().isEmpty()) {
            Tuple newFinishedGamesScore = new Tuple(p.getPlayerName(), p.getFinishedGames());
            globalRecords.add(0, newFinishedGamesScore);
        } else if (globalRecords.get(0).getValue() < p.getFinishedGames()) {
            Tuple newFinishedGamesScore = new Tuple(p.getPlayerName(), p.getFinishedGames());
            globalRecords.set(0, newFinishedGamesScore);
        }

        if (globalRecords.get(1).getPlayerName().isEmpty()) {
            Tuple newGamesLostScore = new Tuple(p.getPlayerName(), p.getFinishedGames());
            globalRecords.add(1, newGamesLostScore);
        } else if (globalRecords.get(1).getValue() < p.getGamesLost()) {
            Tuple newGamesLostScore = new Tuple(p.getPlayerName(), p.getGamesLost());
            globalRecords.set(1, newGamesLostScore);
        }

        if (globalRecords.get(2).getPlayerName().isEmpty()) {
            Tuple newGamesWonScore = new Tuple(p.getPlayerName(), p.getFinishedGames());
            globalRecords.add(2, newGamesWonScore);
        } else if (globalRecords.get(2).getValue() < p.getGamesWon()) {
            Tuple newGamesWonScore = new Tuple(p.getPlayerName(), p.getGamesWon());
            globalRecords.set(2, newGamesWonScore);
        }

        if (globalRecords.get(3).getPlayerName().isEmpty()) {
            Tuple newMaxScore = new Tuple(p.getPlayerName(), p.getFinishedGames());
            globalRecords.add(3, newMaxScore);
        } else if (globalRecords.get(3).getValue() < p.getMaxScore()) {
            Tuple newMaxScore = new Tuple(p.getPlayerName(), p.getMaxScore());
            globalRecords.set(3, newMaxScore);
        }

        if (globalRecords.get(4).getPlayerName().isEmpty()) {
            Tuple newMinGuessesScore = new Tuple(p.getPlayerName(), p.getMinGuesses());
            globalRecords.add(4, newMinGuessesScore);
        } else if (globalRecords.get(4).getValue() > p.getMinGuesses()) {
            Tuple newMinGuessesScore = new Tuple(p.getPlayerName(), p.getMinGuesses());
            globalRecords.set(4, newMinGuessesScore);
        }

        if (globalRecords.get(5).getPlayerName().isEmpty()) {
            Tuple newTotalScore = new Tuple(p.getPlayerName(), p.getTotalScore());
            globalRecords.add(5, newTotalScore);
        } else if (globalRecords.get(5).getValue() < p.getTotalScore()) {
            Tuple newTotalScore = new Tuple(p.getPlayerName(), p.getTotalScore());
            globalRecords.set(5, newTotalScore);
        }
        updated = true;

        return updated;

    }

    public void setGlobalRecords(ArrayList<Tuple> globalRecords) {
        this.globalRecords = globalRecords;
    }

    /**
     *
     * @return
     */
    public ArrayList<Tuple> getGlobalRecords() {
        return globalRecords;
    }
}
