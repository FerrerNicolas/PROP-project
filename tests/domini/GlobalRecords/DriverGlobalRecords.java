package domini.GlobalRecords;

import java.util.Scanner;


import domini.Player.*;
import domini.Tuple.Tuple;

public class DriverGlobalRecords { //Author:Luis

	
	public static void main (String[] args) {
		Boolean exit = false;
		Scanner sc= new Scanner(System.in);
		Player p = null;
		GlobalRecords gr = new GlobalRecords();
		Tuple t1 = new Tuple("Luis", (float) -1);
		Tuple t2 = new Tuple("Guillermo", (float) -10);
		Tuple t3 = new Tuple("Victor", (float) -20);
		Tuple t4 = new Tuple("Javi", (float) -40);
		Tuple t5 = new Tuple("John", (float) -20);
		Tuple t6 = new Tuple("Peter", (float) -40);
		while (!exit) {
			
			System.out.println("Input number for test");
			System.out.println("1. Populate array list");
			System.out.println("2. Update global record");
			System.out.println("3. Get global records");
			System.out.println("4. Exit Driver");
			int option = sc.nextInt();
			sc.nextLine();
			switch(option) {
			case 1:
				if(gr.globalRecords.isEmpty()) {
				System.out.println("Record list filled with test 1"
						+ "data: \n");
				gr.globalRecords.add(0,t1); 
				gr.globalRecords.add(1,t2); 
				gr.globalRecords.add(2,t3); 
				gr.globalRecords.add(3,t4); 
				gr.globalRecords.add(4,t5); 
				gr.globalRecords.add(5,t6); 
				System.out.println("Name: " + t1.getPlayerName() + "; New Record: "+ t1.getValue() +" Finished Games");
				System.out.println("Name: " + t2.getPlayerName() + "; New Record: "+ t2.getValue() +" Games Lost");
				System.out.println("Name: " + t3.getPlayerName() + "; New Record: "+ t3.getValue() +" Games Won");
				System.out.println("Name: " + t4.getPlayerName() + "; New Record: "+ t4.getValue() +" Max Score");
				System.out.println("Name: " + t5.getPlayerName() + "; New Record: "+ t5.getValue() +" Minimum Guesses");
				System.out.println("Name: " + t6.getPlayerName() + "; New Record: "+ t6.getValue() +" Total Score \n");
				}
				else {
					System.out.println("Record list refilled with new test data: \n");

					gr.globalRecords.set(0,t1); 
					gr.globalRecords.set(1,t2); 
					gr.globalRecords.set(2,t3); 
					gr.globalRecords.set(3,t4); 
					gr.globalRecords.set(4,t5); 
					gr.globalRecords.set(5,t6); 
					System.out.println("Name: " + t1.getPlayerName() + "; New Record: "+ t1.getValue() +" Finished Games");
					System.out.println("Name: " + t2.getPlayerName() + "; New Record: "+ t2.getValue() +" Games Lost");
					System.out.println("Name: " + t3.getPlayerName() + "; New Record: "+ t3.getValue() +" Games Won");
					System.out.println("Name: " + t4.getPlayerName() + "; New Record: "+ t4.getValue() +" Max Score");
					System.out.println("Name: " + t5.getPlayerName() + "; New Record: "+ t5.getValue() +" Minimum Guesses");
					System.out.println("Name: " + t6.getPlayerName() + "; New Record: "+ t6.getValue() +" Total Score \n");
						
				}
			break;
			
			
			case 2:
				System.out.println("Choose player to update:" );
				String A = sc.nextLine();
				p = new Player(A);	
				gr.update(p);			

				System.out.println("Records updated. Check again option 3.Get global records.");
				break;
				
			case 3:
				if(!gr.globalRecords.isEmpty()) {
				
							System.out.println("Player:" + gr.getGlobalRecords().get(0).getPlayerName());
							System.out.println("Finished Games Record: " + gr.getGlobalRecords().get(0).getValue());
							
							System.out.println("Player:" + gr.getGlobalRecords().get(1).getPlayerName());
							System.out.println("Games Lost Record: " + gr.getGlobalRecords().get(1).getValue());
							
							System.out.println("Player:" + gr.getGlobalRecords().get(2).getPlayerName());
							System.out.println("Games Won Record: " + gr.getGlobalRecords().get(2).getValue());
							
							System.out.println("Player:" + gr.getGlobalRecords().get(3).getPlayerName());
							System.out.println("Max Score Record: " + gr.getGlobalRecords().get(3).getValue());
							
							System.out.println("Player:" + gr.getGlobalRecords().get(4).getPlayerName());
							System.out.println("Minimum Guesses Record: " + gr.getGlobalRecords().get(4).getValue());
							
							System.out.println("Player:" + gr.getGlobalRecords().get(5).getPlayerName());
							System.out.println("Total Score Record: " + gr.getGlobalRecords().get(5).getValue()+"\n");
							
					
					
				System.out.println("Record Array Size:" + gr.getGlobalRecords().size() +"\n");
				}
				else {
					System.out.println("Empty array \n");
				}
				
				break;
				
			case 4:
				exit = true;
				sc.close();
				break;
				
				
			default:
				System.out.println("Option not valid, please input a number from 1 to 4 \n");
				break;
				
			}
		}
	}

}
