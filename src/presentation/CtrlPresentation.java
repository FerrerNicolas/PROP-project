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
	//Add here the views
	private LoginView loginView;
	private NewUserView newUserView;
	private MenuView menuView;
	private NewGameView newGameView;
	private BreakerView breakerView;
	private CorrectionView correctionView;
	private SetCodeView setCodeView;

	public CtrlPresentation() {
		try {
			this.ctrlDomain = new CtrlDomain();
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}
		//might need more stuff, specifically: instance the views with a reference to this class

	}

	public void initialize() {
		loadLoginView();
	}

	public void loginUser(String username) {
		try {
			ctrlDomain.logIn(username);
		} catch (UnexistingUser unexistingUser) {
			loginView.notExistingUser();
		} catch (AlreadyLoggedIn alreadyLoggedIn) {
		} catch (FileNotFoundException e) {}
		catch (IOException e) {}
		catch (ClassNotFoundException e) {

		}
		loginView.setVisible(false);
		loadMenuView();
	}


	public void createUser(String username) {
		try {
			ctrlDomain.register(username);
		} catch (AlreadyLoggedIn alreadyLoggedIn) {
			//Should never happen
		} catch (ExistingUser existingUser) {
			//Dialog showing user already exists
		} catch (IOException e) {}
		newUserView.setVisible(false);
		loadMenuView();
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

	public void createNewGame(Diff diff, boolean userIsBreaker, boolean fiveGuessAI) {
		ArrayList<String> gameParameters = new ArrayList<String>();
		if (userIsBreaker) gameParameters.add("Breaker");
		else gameParameters.add("Maker");
		if (diff.equals(Diff.EASY)) gameParameters.add("Easy");
		else if (diff.equals(Diff.NORMAL)) gameParameters.add("Normal");
		else gameParameters.add("Hard");
		if (userIsBreaker && fiveGuessAI) gameParameters.add("Knuth");
		else if (userIsBreaker && !fiveGuessAI) gameParameters.add("Darwin");
		try {
			ctrlDomain.NewGame(gameParameters);
		} catch (BadParameters badParameters) {}
		catch (AlreadyGameLoaded alreadyGameLoaded) {}
		catch (NoUserLoggedIn noUserLoggedIn) {}

		if (userIsBreaker) loadBreakerView(diff.equals(Diff.HARD), false);
		else loadMakerView(diff.equals(Diff.HARD));
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
		} catch (GameUnexistentForUser gameUnexistentForUser) {}
		catch (NoUserLoggedIn noUserLoggedIn) {}
		catch (ClassNotFoundException e) {}
		catch (IOException e) {}
		ArrayList<String> gameParameters = new ArrayList<>();
		try {
			gameParameters = ctrlDomain.getGameInfo();
		} catch (NoActiveGame noActiveGame) {
		}
		if(gameParameters.get(0).equals("Breaker")) {
			loadBreakerView(gameParameters.get(1).equals("Hard"), true);
		}
		else loadCorrectionView(gameParameters.get(1).equals("Hard"));
	}

	private void loadCorrectionView(boolean isHard) {
	}

	public void eraseGame(String s) {
        try {
            ctrlDomain.deleteSavedGame(s);
        } catch (GameUnexistentForUser gameUnexistentForUser) {}
        catch (NoUserLoggedIn noUserLoggedIn) {}
        catch (ClassNotFoundException e) {}
        catch (IOException e) {}
    }

    public ArrayList<Object> getBoard() {
		return ctrlDomain.getBoard();
	}

	public Vector<Integer> correctCode(Vector<Integer> code) throws GameIsFinished, IOException, CodeIsInvalid, MismatchedRole, ClassNotFoundException, BadlyFormedCode, NoUserLoggedIn, NoActiveGame {
		System.out.println(code.toString());
		return ctrlDomain.playCode(code);
	}
	/*
	public float showScore() {

	}
	*/

	public void playCorrection(int blackPins, int white) throws IncorrectCorrection, NoGuessToBeCorrected, InvalidNumberOfPins, IOException, MismatchedRole, ClassNotFoundException, NoUserLoggedIn, NoActiveGame {
		ctrlDomain.playCorrection(white, blackPins);
	}

	public void setSecretCode(Vector<Integer> code) {
		try {
			ctrlDomain.setSecretCode(code);
		} catch (NoActiveGame noActiveGame) {}
		catch (SecretCodeAlreadySet secretCodeAlreadySet) {}
		catch (MismatchedRole mismatchedRole) {}
		catch (BadlyFormedCode badlyFormedCode) {}
		catch (CodeIsInvalid codeIsInvalid) {}
		loadCorrectionView();
	}
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

	public void loadLoadGameView() {
	    new LoadGameView(this).setVisible(true);
    }

    private void loadBreakerView(boolean isHard, boolean startedGame) {
			newGameView.setVisible(false);
			breakerView = new BreakerView(this, isHard, startedGame);
	}

	private void loadMakerView(boolean isHard) {
		new SetCodeView(this, isHard);
	}

	private void loadCorrectionView() {
		correctionView = new CorrectionView(this);
	}

	public void endGame() {
		correctionView.setVisible(false);
		breakerView.setVisible(false);
		loadMenuView();
	}

	public void saveGame(String name) {
		try {
			ctrlDomain.saveAndExitCurrentGame(name);
		} catch (NoActiveGame noActiveGame) {}
		catch (UserSavesExistingID userSavesExistingID) {}
		catch (ClassNotFoundException e) {}
		catch (IOException e) {}
	}

	public void exitGame() {
		try {
			ctrlDomain.exitCurrentGame();
		} catch (NoActiveGame noActiveGame) {
			noActiveGame.printStackTrace();
		}
		breakerView.setVisible(false);
		setCodeView.setVisible(false);
		loadMenuView();

	}
}
