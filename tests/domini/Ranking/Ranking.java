package domini.Ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import domini.Tuple.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.CtrlPersistenceRecords;

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
	

    public void setRanking(ArrayList<Tuple> ranking) {
        this.ranking = ranking;
    }
    
    
    public void addRankings() throws IOException, ClassNotFoundException {
        CtrlPersistenceRecords cpr = null;
            cpr = new CtrlPersistenceRecords();
        
        model.Ranking r = new model.Ranking();
        r.insert(new model.Tuple("Luis", 2.0f ));
        cpr.saveRankings(r);
    }
    
    
    public static void main(String[] args) {
        Ranking r = new Ranking();
        try {
            r.addRankings();
        } catch (IOException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
