package domini.GlobalRecords;

import java.util.Scanner;

import domini.Ranking.Ranking;
import domini.Player.*;

public class DriverGlobalRecords {

	
	public static void main (String[] args) {
		Boolean exit = false;
		Scanner sc= new Scanner(System.in);
		Player p = null;
		GlobalRecords gr =null;
		while (!exit) {
			
			System.out.println("Input number for test");
			System.out.println("1. Update global record");
			System.out.println("2. Get global records");
			System.out.println("3. Exit Driver");
			int option = sc.nextInt();
			sc.nextLine();
			switch(option) {
			case 1:
				System.out.println("Insert a name");
				String A = sc.nextLine();
				p = new Player(A);	
				gr = new GlobalRecords();
				System.out.println(A + " you scored ");
				gr.update(p);
				if(gr.getGlobalRecords().isEmpty()) {

					System.out.println("Error inserting tuple");
						
				}else{

					System.out.println("Tuple inserted correctly");
					
					
				}
				
				
				
				break;
			case 2:
				gr.getGlobalRecords().ensureCapacity(10);
				for(int i=0;i<gr.getGlobalRecords().size();i++) {
					if(!gr.getGlobalRecords().get(i).getPlayerName().isEmpty()) {
System.out.println(gr.getGlobalRecords().get(i));
							System.out.println(gr.getGlobalRecords().size());
					}
					
					else{
						System.out.println("Error");}
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
