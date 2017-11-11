package model;

import java.util.ArrayList;

public class GlobalRecords {
	
	ArrayList<Tuple> globalRecords;
	
	public void update(Player p) {
		
		for(int i=0;i<10;i++) {
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
