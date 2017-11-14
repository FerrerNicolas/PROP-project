package domini.Ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import domini.Tuple.*;

public class Ranking { //Author:Luis
	
	ArrayList<Tuple> ranking = new ArrayList<Tuple>(10);
	
	public void insert(Tuple t) {
		
		ranking.add(t);
		
	

	Collections.sort(ranking, new Comparator<Tuple>() {
	    @Override
	    public int compare(Tuple t1, Tuple t2) {
	        return t2.getValue().compareTo(t1.getValue());
	    }
	});
	
	if(ranking.size()>10) {
		ranking.remove(10);
	}
	}
	public ArrayList<Tuple> getRanking(){
		return ranking;
				}
	}
