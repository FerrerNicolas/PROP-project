package domain.ctrlTester;

import java.util.ArrayList;
import java.util.Scanner;

import domain.CtrlDomain;
import exceptions.*;

public class Driver {
	CtrlDomain cd;
	static Scanner sc = new Scanner(System.in);
	//This is a tester class, but also an example of use of the class CtrlDomain.
	public void start() {
		cd = new CtrlDomain();
		Status currentstatus;
		currentstatus = Status.LOGINSCREEN;
		while (currentstatus != Status.EXIT) {
			switch (currentstatus){
			case LOGINSCREEN:
				currentstatus = login();
				break;
			case MAINMENU:
				currentstatus = mainmenu();
			default:
			}
		}
	}
	public Status login() {
		while (true) {
			System.out.println("LOGIN SCREEN");
			System.out.println("Input 1 for register user");
			System.out.println("Input 2 for login user");
			System.out.println("Input 3 for exit game");
			Integer opt = sc.nextInt();
			if(opt == 1) {
				System.out.println("Input username");
				String username = sc.nextLine();
				try {
					cd.register(username); 
					System.out.println("Success, going to MainMenu");
					return Status.MAINMENU;
				} catch (AlreadyLoggedIn e) {
					System.out.println("There was a player already logged in, so we send you to MainMenu");
					return Status.MAINMENU;
				} catch (ExistingUser e) {
					System.out.println("There was a User with that name already, retry");
				}
			} else if(opt == 2) {
				System.out.println("Input username");
				String username = sc.nextLine();
				
				try {
					cd.logIn(username);
				} catch (UnexistingUser e) {
					System.out.println("There was no User with that name, retry");
				} catch (AlreadyLoggedIn e) {
					System.out.println("There was a player already logged in, so we send you to MainMenu");
					return Status.MAINMENU;
				}
				
			} else if(opt == 3) return Status.EXIT;
			else System.out.println("Retry Opt Code");
		}
	}
	public Status mainmenu() {
		while (true) {
			System.out.println("MAIN MENU SCREEN");
			System.out.println("User logged in: " + cd.getLoggedUsername());
			System.out.println("Input 1 for New Game");
			System.out.println("Input 2 for Load Game");
			System.out.println("Input 3 for Records (not supported)");
			System.out.println("Input 4 for Log out");
			System.out.println("Input 5 for Exit");
			Integer opt = sc.nextInt();
			switch(opt) {
			case 1:
				System.out.println("Input the new Game's parameters:");
				ArrayList<String> parameters = new ArrayList<String>();
				/* Format on Parameters REMINDER:
				 * 		*[0]: "Breaker"|"Maker" (referring to user)
				 * 		*[1]: "Easy"|"Normal"|"Hard" (referring to game difficulty)
				 * 		*[2]: "Knuth"|"Darwin" (referring to Ai Type, will be ignored if [0]="Breaker", so practice is: leave it blank (arraysize =2))
				 */
				System.out.println("Breaker| Maker ?");
				parameters.add(sc.nextLine());
				System.out.println("Easy | Normal | Hard ?");
				parameters.add(sc.nextLine());
				if(parameters.get(0).equals("Maker")) {
					System.out.println("Knuth|Darwin ?");
					parameters.add(sc.nextLine());
				}
				try {
					cd.NewGame(parameters);
				} catch (BadParameters e1) {
					System.out.println("Bad Parameters:");
					System.out.println(e1.getMessage());
				} catch (AlreadyGameLoaded e1) {
					System.out.println("Already a Game active! Should not happen in any case, but it will now be exited");
					try {
						cd.exitCurrentGame();
					} catch (Exception x) {} //totally impossible
				} catch (NoUserLoggedIn e1) {
					System.out.println("No user logged in! Redirecting to log in screen");
					return Status.LOGINSCREEN;
				}
				break;
			case 2:
				// Will do some other day, can't be bothered rn
				break;
			case 3:
				System.out.println("Sorry, Luis's work");
				break;
			case 4:
				try {
					cd.logOut();
					return Status.LOGINSCREEN;
				} catch (NoUserLoggedIn e) {
					System.out.println("There was no user logged in, returning to log in screen");
					return Status.LOGINSCREEN;
				}
			case 5:
				return Status.EXIT;
			default:
				break;
			}
		}
	}
	public Status game() {
		//will leave for other moment
		return Status.EXIT;
	}
	public enum Status {
		LOGINSCREEN,
		MAINMENU,
		GAME,
		EXIT
	}
}
