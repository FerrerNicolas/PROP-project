package model;

import java.util.ArrayList;

import domini.Tuple.Tuple;

	
	public class Ranking { //Author:Luis
		
		ArrayList<Tuple> ranking = new ArrayList<Tuple>(10);
		
		public void insert(Tuple t) {
			

			
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
			}
				}
					}
		public ArrayList<Tuple> getRanking(){
			return ranking;
					}
		}
