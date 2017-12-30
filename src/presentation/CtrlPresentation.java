package presentation;
import domain.CtrlDomain;
import exceptions.*;
import model.Diff;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CtrlPresentation {
	private CtrlDomain ctrlDomain;
	//Add here the views
	
	public CtrlPresentation() {
		try {
			ctrlDomain = new CtrlDomain();
		} catch (ClassNotFoundException e) {
			//Should never happen, but added for it to compile
		} catch (IOException e) {
			//Should never happen but added for it to compile
		}
		//might need more stuff, specifically: instance the views with a reference to this class
	}

	public void loginUser(String username) {
		try {
			ctrlDomain.logIn(username);
		} catch (UnexistingUser unexistingUser) {
			LoginView.notExistingUser();
		} catch (AlreadyLoggedIn alreadyLoggedIn) {
			System.out.println("This should never happen");
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		} catch (ClassNotFoundException e) {

		}
		//Load main menu view
	}


	public void createUser(String username) {
		try {
			ctrlDomain.register(username);
		} catch (AlreadyLoggedIn alreadyLoggedIn) {
			//Should never happen
		} catch (ExistingUser existingUser) {
			//Dialog showing user already exists
		} catch (IOException e) {

		}
	}

	public String getUser() {
		return ctrlDomain.getLoggedUsername();
	}

	public void logOutUser() {
		try {
			ctrlDomain.logOut();
		} catch (NoUserLoggedIn noUserLoggedIn) {
			System.out.println("This should never happen");
		}
		loadLoginView();
	}

	public void createNewGame(Diff diff, boolean userIsBreaker, boolean fiveGuessAI) {
		ArrayList<String> gameParameters = new ArrayList<>();
		if (userIsBreaker) gameParameters.add("Breaker");
		else gameParameters.add("Maker");
		if (diff.equals(Diff.EASY)) gameParameters.add("Easy");
		else if (diff.equals(Diff.NORMAL)) gameParameters.add("Normal");
		else gameParameters.add("Hard");
		if (userIsBreaker && fiveGuessAI) gameParameters.add("Knuth");
		else if (userIsBreaker && !fiveGuessAI) gameParameters.add("Darwin");
		try {
			ctrlDomain.NewGame(gameParameters);
		} catch (BadParameters badParameters) {
			System.out.println("Bad parameters");
		} catch (AlreadyGameLoaded alreadyGameLoaded) {
			System.out.println("This should never happen");
		} catch (NoUserLoggedIn noUserLoggedIn) {
			System.out.println("This should never happen");
		}
		//Load gameView
	}


	public Object[] getSavedGamesList() {
		try {
			return ctrlDomain.loadUsersSavedGames().toArray();
		} catch (NoUserLoggedIn noUserLoggedIn) {
		}
		//Should never come here!
		return null;
	}

	public void loadGame(String s) {

		try {
			ctrlDomain.loadSavedGame(s);
		} catch (AlreadyGameLoaded alreadyGameLoaded) {
			//Should never hapen
		} catch (GameUnexistentForUser gameUnexistentForUser) {

		} catch (NoUserLoggedIn noUserLoggedIn) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//GameView

	}

	public void eraseGame(String s) {
        try {
            ctrlDomain.deleteSavedGame(s);
        } catch (GameUnexistentForUser gameUnexistentForUser) {
            gameUnexistentForUser.printStackTrace();
        } catch (NoUserLoggedIn noUserLoggedIn) {
            noUserLoggedIn.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void loadLoginView() {
		new LoginView(this).setVisible(true);
	}

	public void loadUserView() {
		new NewUserView(this).setVisible(true);
	}

	public void loadMenuView() {
		new MenuView(this).setVisible(true);
	}

	public void loadLoadGameView() {
	    new LoadGameView(this).setVisible(true);
    }



}
