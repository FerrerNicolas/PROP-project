package domini.Ranking;

import java.util.ArrayList;


import domini.Tuple.*;

public class Ranking { //Author:Luis
	
	ArrayList<Tuple> ranking = new ArrayList<Tuple>(10);
	
	public void insert(Tuple t) {
		
		ranking.add(t);
		sort(ranking);
	}
		
		/*Function to sort arrayList using insertion sort*/
	    void sort(ArrayList<Tuple> ranking)
	    {
	        int n = ranking.size();
	        for (int i=0; i<n; ++i)
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
	            ranking.set(j+1, key);
	        }
	    }
	  
	    public ArrayList<Tuple> getRanking(){
		return ranking;
				}
	}
