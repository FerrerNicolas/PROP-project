package domini.Tuple;


import java.util.Scanner;


public class DriverTuple {//Author:Luis

	
	public static void main (String[] args) {
		Boolean exit = false;
		
		Scanner sc= new Scanner(System.in);
		Tuple t = null;
		
		while (!exit) {
			
			System.out.println("Input number for test");
			System.out.println("1. Instance tuple");
			System.out.println("2. Get Name");
			System.out.println("3. Get value");
			System.out.println("4. Exit Driver");
			int option = sc.nextInt();
			sc.nextLine();
			switch(option) {
			case 1:
				System.out.println("Input data");
				System.out.println("Name:");
				String A = sc.nextLine();
				System.out.println("Score:");
				Float B = sc.nextFloat();
				t = new Tuple(A,B);
				if(!A.isEmpty() && !B.isNaN()) {
						
						System.out.println("Tuple initialized: " + t.getPlayerName() + " " + t.getValue());
				}
				
								break;
			case 2:
				System.out.println(t.getPlayerName());
				break;
			case 3:
				System.out.println(t.getValue().toString());
				break;
			case 4:
				exit = true;
				sc.close();
				break;
			default:
				System.out.println("Option not valid, please input a number from 1 to 4");
				break;
				
			}
		}
	}
}