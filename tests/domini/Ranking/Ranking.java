package domini.Ranking;

import java.util.ArrayList;
import java.util.Collections;
//import java.util.Collections;
import java.util.Comparator;

import domini.Tuple.*;

public class Ranking { //Author:Luis
	
	ArrayList<Tuple> ranking = new ArrayList<Tuple>(10);
	
	public void insert(Tuple t) {
		
		ranking.add(t);
		sort(ranking);
	}
		
		/*Function to sort array using insertion sort*/
	    void sort(ArrayList<Tuple> ranking)
	    {
	        int n = ranking.size();
	        for (int i=1; i<n; ++i)
	        {
	            Tuple key = ranking.get(i);
	            int j = i-1;
	 
	            /* Move elements of ranking[0..i-1], that are
	               greater than key, to one position ahead
	               of their current position */
	            while (j>=0 && ranking.get(j).getValue() > key.getValue())
	            {
	                ranking.set(i, ranking.get(j));
	                j = j-1;
	            }
	            ranking.set(j+1,key);
	        }
	    }
	    
/*
				if(ranking.size()<10) { //Fill ArrayList with first 10 tuples
				ranking.add(t);
				
			}
			else { 

			for(int i=0;i < ranking.size();i++) { 
					if(ranking.get(i).getValue() < t.getValue() ) { //Check if values inside the array are lower than the new tuple value
						if(ranking.get(i).getPlayerName().equals(t.getPlayerName())) { //One player must have only one register in the ranking list
							ranking.set(i, t); //Replace tuple 
							

							}
					else{
							ranking.add(t); //Add tuple with score greater than the old tuple

						}
			}
					else {
						
					}
		}
			}*/
				
	public ArrayList<Tuple> getRanking(){
		return ranking;
				}
	}
