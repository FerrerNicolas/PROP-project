package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



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
	}
	
	public ArrayList<Tuple> getRanking(){
		return ranking;
				}
	}
