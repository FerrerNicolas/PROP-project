package model;

import java.util.ArrayList;

public class Ranking {
	
	ArrayList<Tuple> ranking;
	
	public void insert(Tuple t) {
		
		for(int i=0;i<10;i++) {
		if(t.getValue() > ranking.get(i).getValue()) {
			if(ranking.get(i).getPlayerName().equals(t.getPlayerName())) {
				ranking.add(t);
				}
			}
		}
	}
	
	public ArrayList<Tuple> getRanking(){
		return ranking;
				}
	}
