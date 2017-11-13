package domini.GlobalRecords;

import domini.Tuple.*;
import domini.Player.*;

import java.util.ArrayList;

public class GlobalRecords {
	
	ArrayList<Tuple> globalRecords = new ArrayList<Tuple>(6);
	
	public void update(Player p) {
		
		
		
		for(int i=0;i<globalRecords.size();i++) {
		if(p.getTotalScore() > globalRecords.get(i).getValue()) {
			
			
			
			Tuple newRecord = new Tuple(p.getPlayerName(),p.getTotalScore());
			
			globalRecords.add(newRecord);
		}
		}
	}
	
	public ArrayList<Tuple> getGlobalRecords(){
		return globalRecords;
				}
}