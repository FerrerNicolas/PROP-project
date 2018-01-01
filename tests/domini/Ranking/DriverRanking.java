package domini.Ranking;



import java.util.Scanner;

import domini.Tuple.*;


public class DriverRanking { //Author:Luis

	
	public static void main (String[] args) {
		Boolean exit = false;
		Scanner sc= new Scanner(System.in);
		Tuple t = null;
		Ranking r =new Ranking();
			while (!exit) {
			
			System.out.println("Input number for test");
			System.out.println("1. Insert ranking");
			System.out.println("2. Get Ranking");
			System.out.println("3. Exit Driver");
			int option = sc.nextInt();
			sc.nextLine();
			switch(option) {
			case 1:
				System.out.println("Insert a name");
				String A = sc.nextLine();
				System.out.println("Insert a record");
				Float B = sc.nextFloat();
				t = new Tuple(A,B);	
				
				System.out.println(A + " you scored " + B);
				r.insert(t);				
				
				break;
			case 2:
				for(int i=0;i<r.getRanking().size() && i<10;i++) {
					
							System.out.println("Tuple nrº:" + (1+i) + " " + r.getRanking().get(i));
	/*						System.out.println("Name: "+ r.getRanking().get(i).getPlayerName() + "; Score: " + r.getRanking().get(i).getValue()); */
					}
				System.out.println("Ranking size:"+ r.getRanking().size());
				
		
				
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