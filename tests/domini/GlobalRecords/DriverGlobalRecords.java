package domini.GlobalRecords;

import java.util.Scanner;


import domini.Player.*;

public class DriverGlobalRecords {//Author:Luis

	
	public static void main (String[] args) {
		Boolean exit = false;
		Scanner sc= new Scanner(System.in);
		Player p = null;
		GlobalRecords gr = new GlobalRecords();
		while (!exit) {
			
			System.out.println("Input number for test");
			System.out.println("1. Update global record");
			System.out.println("2. Get global records");
			System.out.println("3. Exit Driver");
			int option = sc.nextInt();
			sc.nextLine();
			switch(option) {
			case 1:
				System.out.println("Insert a name:" );
				String A = sc.nextLine();
				System.out.println("Insert record");
				p = new Player(A);	
				System.out.println(" Player selected: " + A );
				gr.update(p);			
				
				
				break;
				
			case 2:
				if(!gr.globalRecords.isEmpty()) {
				for(int i=0;i<gr.getGlobalRecords().size() && i<6; i++) {
							System.out.println("Data:" + gr.getGlobalRecords().get(i).getPlayerName());
					}
				System.out.println("Record Size:" + gr.getGlobalRecords().size());
				}
				else {
					System.out.println("Empty array");
				}
				
				break;
			case 3:
				exit = true;
				sc.close();
				break;
			default:
				System.out.println("Option not valid, please input a number from 1 to 3");
				break;
				
			}
		}
	}

}
