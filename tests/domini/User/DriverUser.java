package domini.User;

import java.util.Scanner;
import java.util.*;
/*
import model.Game;
import model.Diff;
import model.Code;
import model.Correction;*/
import exceptions.*;
public class DriverUser {
	
	public static void main (String[] args) {
		Boolean exit = false;
		User u = new User("PREDEFINED");
		Scanner sc= new Scanner(System.in);
		while (!exit) {
			System.out.println("Input number for test");
			System.out.println("1. Instance User");
			System.out.println("2. Save Game");
			System.out.println("3. Get Saved Games");
			System.out.println("4. Delete Saved Games");
			System.out.println("5. Exit Driver");
			int option = sc.nextInt();
			sc.nextLine();
			switch(option) {
			case 1:
				System.out.println("Input name");
				String A=sc.nextLine();
				u = new User(A);
				System.out.println("User instanced with name: " + u.getPlayerName());
				break;
			case 2:
				System.out.println("Input name of the Game to be saved");
				String s =sc.nextLine();
				try {
					u.saveGame(s);
					System.out.println("Game successfully saved");
				} catch (UserSavesExistingID e) {
					System.out.println("Game's ID was already present, Game not saved");
				}
				break;
			case 3:
				ArrayList<String> SG =  u.getSavedGames();
				System.out.println("User: " + u.getPlayerName() + " saved Games:");
				for(int i=0; i<SG.size(); i++) {
					System.out.println(" " + i + ": ID - " + SG.get(i));
				}
				System.out.println();
				break;
			case 4:
				System.out.println("Input name of the Game to be erased");
				String s2 =sc.nextLine();
				try {
					u.deleteSavedGame(s2);
					System.out.println("Game successfully erased");
				} catch (UserTriedDeletingUnexistent e) {
					System.out.println("Game's ID was not present, Game erased");
				}
				break;
			case 5:
				exit = true;
				sc.close();
				break;
			default:
				System.out.println("Option not valid, please input a number from 1 to 5");
				break;
				
			}
		}
	}
}