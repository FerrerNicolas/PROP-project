package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ranking implements Serializable { //Author:Luis

    private ArrayList<Tuple> ranking = new ArrayList<Tuple>(10);

    public Ranking insert(Tuple t) {

        if(ranking.size()<10){
            ranking.add(t);

            Collections.sort(ranking, new Comparator<Tuple>() { //function is not efficient. No need to sort the array everytime
                @Override
                public int compare(Tuple t1, Tuple t2) {
                    return t2.getValue().compareTo(t1.getValue());
                }
            });
            return new Ranking().setRanking(ranking);
        }else
            return null;
    }


    public ArrayList<Tuple> getRanking() {
        return ranking;
    }

    public Ranking setRanking(ArrayList<Tuple> ranking) {
        this.ranking = ranking;
        return this;
    }

}