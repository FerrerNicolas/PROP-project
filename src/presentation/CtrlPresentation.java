package presentation;
import domain.CtrlDomain;
import exceptions.*;
import model.Diff;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class CtrlPresentation {
	private CtrlDomain ctrlDomain;
	private CtrlPresentationRecords ctrlPresentationRecords;
	//Add here the views
	private LoginView loginView;
	private NewUserView newUserView;
	private MenuView menuView;
	private NewGameView newGameView;


	//Functions to initialize the controller

	public CtrlPresentation() {
		try {
			this.ctrlPresentationRecords = new CtrlPresentationRecords(this);
			this.ctrlDomain = new CtrlDomain();
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
		//might need more stuff, specifically: instance the views with a reference to this class

	}

	//Function to load the initial view
	public void initialize() {
		loadLoginView();
	}


	//User control related functions

	//Function used by the LoginView
	public void loginUser(String username) {
		boolean succesfulLogin = true;
		try {
			ctrlDomain.logIn(username);
		} catch (UnexistingUser unexistingUser) {
			succesfulLogin = false;
			loginView.notExistingUser();
		} catch (AlreadyLoggedIn alreadyLoggedIn) {
		} catch (FileNotFoundException e) {}
		catch (IOException e) {}
		catch (ClassNotFoundException e) {
		}
		if (succesfulLogin) {
            try {
                this.ctrlPresentationRecords 	 = new CtrlPresentationRecords(this);
            } catch (IOException e) {}
            catch (ClassNotFoundException e) {}
            loginView.setVisible(false);
			loadMenuView();
		}
	}


	public void createUser(String username) {
		boolean createdUser = true;
		try {
			ctrlDomain.register(username);
		} catch (AlreadyLoggedIn alreadyLoggedIn) {
			//Should never happen
		} catch (ExistingUser existingUser) {
			createdUser = false;
			newUserView.userAlreadyExists(username);
		} catch (IOException e) {}
		if (createdUser) {
			newUserView.setVisible(false);
			loadMenuView();
		}

	}

	public String getUser() {
		return ctrlDomain.getLoggedUsername();
	}

	public void logOutUser() {
		try {
			ctrlDomain.logOut();
		} catch (NoUserLoggedIn noUserLoggedIn) {
		}
		loadLoginView();
	}

	//Users related functions

	public void createNewGame(Diff diff, boolean userIsBreaker, boolean fiveGuessAI) {
		ArrayList<String> gameParameters = new ArrayList<String>();
		if (userIsBreaker) gameParameters.add("Breaker");
		else gameParameters.add("Maker");
		if (diff.equals(Diff.EASY)) gameParameters.add("Easy");
		else if (diff.equals(Diff.NORMAL)) gameParameters.add("Normal");
		else gameParameters.add("Hard");
        if (!userIsBreaker && fiveGuessAI) gameParameters.add("Knuth");
        else if (!userIsBreaker) gameParameters.add("Darwin");
		try {
			ctrlDomain.NewGame(gameParameters);
		} catch (BadParameters badParameters) {
		}
		catch (AlreadyGameLoaded alreadyGameLoaded) {
		}
		catch (NoUserLoggedIn noUserLoggedIn) {}

		if (userIsBreaker) loadBreakerView(diff.equals(Diff.HARD), false);
        else loadSetCodeView(diff.equals(Diff.HARD));

    }

    private void loadSetCodeView(boolean isHard) {
        new SetCodeView(this, isHard);
	}


	public Object[] getSavedGamesList() {
		try {
			return ctrlDomain.loadUsersSavedGames().toArray();
		} catch (NoUserLoggedIn noUserLoggedIn) {
		}

		//Should never come here!
		return null;
	}

	public void loadGame(String gameID) throws ClassNotFoundException, NoUserLoggedIn, AlreadyGameLoaded, GameUnexistentForUser, IOException {
		ctrlDomain.loadSavedGame(gameID);


    }

	public void loadSavedGame() {
		ArrayList<String> gameParameters = new ArrayList<String>();
		try {
			gameParameters = ctrlDomain.getGameInfo();
		} catch (NoActiveGame noActiveGame) {
		}
		if(gameParameters.get(0).equals("Breaker")) {
			loadBreakerView(gameParameters.get(1).equals("Hard"), true);
		} else loadMakerView(gameParameters.get(1).equals("Hard"));
	}


	public void eraseGame(String gameID) {
        try {
            ctrlDomain.deleteSavedGame(gameID);
        } catch (GameUnexistentForUser gameUnexistentForUser) {}
        catch (NoUserLoggedIn noUserLoggedIn) {}
        catch (ClassNotFoundException e) {}
        catch (IOException e) {}
    }

    public ArrayList<Object> getBoard() {
		return ctrlDomain.getBoard();
	}

	public Vector<Integer> correctCode(Vector<Integer> code) throws GameIsFinished, IOException, CodeIsInvalid, MismatchedRole, ClassNotFoundException, BadlyFormedCode, NoUserLoggedIn, NoActiveGame {
		return ctrlDomain.playCode(code);
	}



    public Vector<Integer> playCorrection(int blackPins, int white) throws IncorrectCorrection, NoGuessToBeCorrected, InvalidNumberOfPins, IOException, MismatchedRole, ClassNotFoundException, NoUserLoggedIn, NoActiveGame {
        return ctrlDomain.playCorrection(white, blackPins);
    }

    public void saveGame(String name) throws ClassNotFoundException, UserSavesExistingID, NoActiveGame, IOException {
        ctrlDomain.saveAndExitCurrentGame(name);
        loadMenuView();
    }

	public void endGame() {
		loadMenuView();
	}

	public void exitGame() {
		try {
			ctrlDomain.exitCurrentGame();
		} catch (NoActiveGame noActiveGame) {
			noActiveGame.printStackTrace();
		}
		loadMenuView();

	}

	public Vector<Integer> takeHint(Vector<Boolean> mask) throws BadParameters, NoUserLoggedIn, EmptyMask, MismatchedRole, NoActiveGame {
		return ctrlDomain.getHint(mask);
	}

	public Vector<Integer> getSecretCode() {
		return (Vector<Integer>) ctrlDomain.getBoard().get(0);
	}

	public void setSecretCode(Vector<Integer> code) throws BadlyFormedCode, MismatchedRole, CodeIsInvalid, NoActiveGame, SecretCodeAlreadySet {
		ctrlDomain.setSecretCode(code);

	}

	//Views related functions, used to load views

	public void loadLoginView() {
		loginView = new LoginView(this);
	}

	public void loadUserView() {
		newUserView = new NewUserView(this);
	}

	public void loadMenuView() {
		menuView = new MenuView(this);
	}

	public void loadNewGameView() {
		menuView.setVisible(false);
		newGameView = new NewGameView(this);
	}

	public void showRecords() {
		menuView.setVisible(false);
		try {
			ctrlPresentationRecords.loadRecordsGUI();
		} catch (IOException e) {}
		catch (ClassNotFoundException e) {}
	}

	public void showRankings() {
		menuView.setVisible(false);
		try {
			ctrlPresentationRecords.loadRankingsGUI();
		} catch (FileNotFoundException e) {}
		catch (IOException e) {}
		catch (ClassNotFoundException e) {}
	}

	public void loadMakerView(boolean isHard) {
		new MakerView(this, isHard);
	}


	public void loadLoadGameView() {
	    new LoadGameView(this).setVisible(true);
    }

    private void loadBreakerView(boolean isHard, boolean startedGame) {
			newGameView.setVisible(false);
			new BreakerView(this, isHard, startedGame);
	}




}
