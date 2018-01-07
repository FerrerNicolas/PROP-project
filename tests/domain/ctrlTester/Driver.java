package domain.ctrlTester;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import domain.CtrlDomain;
import domain.CtrlDomainRecords;
import exceptions.*;
import model.Tuple;
import persistence.CtrlPersistenceRecords;

public class Driver {
	CtrlDomain cd;
	CtrlDomainRecords cdr;
	static Scanner sc = new Scanner(System.in);
	//This is a tester class, but also an example of use of the class CtrlDomain.
	public void start() throws FileNotFoundException, ClassNotFoundException, IOException {
		cd = new CtrlDomain();
		cdr = new CtrlDomainRecords(new CtrlPersistenceRecords());
		Status currentstatus;
		currentstatus = Status.LOGINSCREEN;
		while (currentstatus != Status.EXIT) {
			switch (currentstatus){
				case LOGINSCREEN:
					currentstatus = login();
					break;
				case MAINMENU:
					currentstatus = mainmenu();
				case GAME:
					currentstatus = game();
				case SAVEDGAMES:
					currentstatus = savedGames();
				default:
			}
		}
	}
	public Status login() throws FileNotFoundException, IOException, ClassNotFoundException {
		while (true) {
			System.out.println("LOGIN SCREEN");
			System.out.println("Input 1 for register user");
			System.out.println("Input 2 for login user");
			System.out.println("Input 3 for exit game");
			Integer opt = sc.nextInt();
			sc.nextLine();
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
					return Status.MAINMENU;
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
			System.out.println("Input 3 for Records");
			System.out.println("Input 4 for Log out");
			System.out.println("Input 5 for Exit");
			Integer opt = sc.nextInt();
			sc.nextLine();
			switch(opt) {
				case 1:
					System.out.println("Input the new Game's parameters:");
					ArrayList<String> parameters = new ArrayList<String>();
					/* Format on Parameters REMINDER:
					 * 		*[0]: "Breaker"|"Maker" (referring to user)
					 * 		*[1]: "Easy"|"Normal"|"Hard" (referring to game difficulty)
					 * 		*[2]: "Knuth"|"Darwin" (referring to Ai Type, will be ignored if [0]="Breaker", so practice is: leave it blank (arraysize =2))
					 */
					System.out.println("Breaker | Maker ?");
					parameters.add(sc.nextLine());
					System.out.println("Easy | Normal | Hard ?");
					parameters.add(sc.nextLine());
					if(parameters.get(0).equals("Maker")) {
						System.out.println("Knuth | Darwin ?");
						parameters.add(sc.nextLine());
					}
					try {
						cd.NewGame(parameters);
						return Status.GAME;
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
					return Status.SAVEDGAMES;
				case 3:
					System.out.println("Global Records\n" + getRecordstoString()+"\nRankings \n"+getRankingstoString());
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
		try {
			ArrayList<String> GI = cd.getGameInfo();
			System.out.println("GAME ACTIVE: Info");
			System.out.println(GI.get(0));
			System.out.println(GI.get(1));
			System.out.println(GI.get(2));
			if(GI.get(0).equals("Maker"))
				System.out.println(GI.get(3));
			System.out.println();
			System.out.println("BOARD:-------------------------------------");
			ArrayList<Object> Board = cd.getBoard();
			for (int i = 1; i < Board.size(); i++) {
				System.out.println(Board.get(i).toString()+ "  -----  " +  Board.get(i+1).toString()); //need to check if this works
				//get i +1 will explode, need to recheck
			}
			while(true) {
			}
		} catch (NoActiveGame e) {
			System.out.println("No Game in progress, redirecting to Main Menu");
			return Status.MAINMENU;
		}

	}
	public Status savedGames() throws FileNotFoundException, ClassNotFoundException, IOException {
		while (true) {
			try {
				ArrayList<String> sg = cd.loadUsersSavedGames();
				System.out.println("User's saved games: ");
				for(int i=0; i<sg.size(); i++) System.out.println(sg.get(i));
				System.out.println("Input 1 for Load Game");
				System.out.println("Input 2 for Deleting a Saved Game");
				System.out.println("Input 3 for Exiting to Main Menu");
				Integer opt = sc.nextInt();
				sc.nextLine();
				switch(opt) {
					case 1:
						System.out.println("Input Game Id");
						String id = sc.nextLine();
						try {
							cd.loadSavedGame(id);
							return Status.GAME;
						} catch (AlreadyGameLoaded e) {
							System.out.println("Already a Game active! Should not happen in any case, but it will now be exited");
							try {
								cd.exitCurrentGame();
							} catch (Exception x) {}
						} catch (GameUnexistentForUser e) {
							System.out.println("This game does not belong to the user!");
						}
						break;
					case 2:
						System.out.println("Input Game Id");
						String id2 = sc.nextLine();
						try {
							cd.deleteSavedGame(id2);
						} catch (GameUnexistentForUser e) {
							System.out.println("This game does not belong to the user!");
						}
						break;
					case 3:
						return Status.MAINMENU;
				}
			} catch (NoUserLoggedIn e1) {
				System.out.println("No user logged in! Redirecting to log in screen");
				return Status.LOGINSCREEN;
			}
		}
	}

	public String getRankingstoString(){//Author:Luis
		String result = "";
		ArrayList<Tuple> rankings = cdr.getRankings().getRanking();
		if(!rankings.isEmpty()){
			for(int i=0;i<rankings.size();i++){
				result += rankings.get(i).getPlayerName() + " " + rankings.get(i).getValue() + "\n";
			}
			return result;
		}else
			return result = "Rankings are empty";
	}

	public String getRecordstoString(){ //Author:Luis
		String result = "";
		ArrayList<Tuple> globalrecords = cdr.getGlobalRecords().getGlobalRecords();
		if(!globalrecords.isEmpty()){
			for(int i=0;i<globalrecords.size();i++){
				result += globalrecords.get(i).getPlayerName() + " " + globalrecords.get(i).getValue() + "\n";
			}
			return result;
		}else
			return "Records are empty";
	}
	public enum Status {
		LOGINSCREEN,
		MAINMENU,
		GAME,
		SAVEDGAMES,
		EXIT
	}
}