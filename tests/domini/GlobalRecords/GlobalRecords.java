package domini.GlobalRecords;

import domini.Tuple.*;
import domini.Player.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class GlobalRecords {
	
	ArrayList<Tuple> globalRecords = new ArrayList<Tuple>(6);
	Tuple newRecord = null;
	
	public void update(Player p) {
		
			newRecord = new Tuple(p.getPlayerName(),p.getMaxScore());
			globalRecords.add(newRecord);
			
			Collections.sort(globalRecords, new Comparator<Tuple>() {
			    @Override
			    public int compare(Tuple t1, Tuple t2) {
			        return t2.getValue().compareTo(t1.getValue());
			    }
			});
		}
			
		
	
	
	

	
	
	
	public ArrayList<Tuple> getGlobalRecords(){
		return globalRecords;
				}
}