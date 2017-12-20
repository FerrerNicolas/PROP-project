package domain;
import persistence.*;
import model.*;

import java.util.ArrayList;
import java.util.Vector;

import exceptions.*;
//Victor
public class CtrlDomain {
	protected CtrlPersistence ctrlPersistence;
	protected User activeUser = null;
	private Game activeGame = null;
	private Ai activeAi = null;
	//private CtrlDomainRecord cdr;
	//Creation related functions + getter of domain Record Ctrl
	public CtrlDomain() {
		ctrlPersistence = new CtrlPersistence(this); 
		//CtrlPersistenceRecords cpr = ctrlPersistence.getControllerRecords();
		//cdr = new CtrlDomainRecord(cpr);
	}
	
	/*public CtrlDomainRecord getCdr(){
	 * 	return cdr;
	 * }*/
	public Object getInstanceOfPlayer(String name) { //This is for permanency!
		return new Player(name);
	}
	
	//USER RELATED FUNCTIONS
	public void logIn(String username) throws UnexistingUser, AlreadyLoggedIn { 
		if (activeUser != null) throw new AlreadyLoggedIn();
		activeUser = (User) ctrlPersistence.loadUser(username); //should throw UnexistingUser;
	}
	public void logOut() throws NoUserLoggedIn {
		if (activeUser == null) throw new NoUserLoggedIn();
		activeUser = null;
	}
	public void register(String username) throws AlreadyLoggedIn, ExistingUser {
		if (activeUser != null) throw new AlreadyLoggedIn();
		activeUser = new User(username);
		ctrlPersistence.saveNewUser(username, activeUser);
	}
	//GAME RELATED FUNCTIONS:
	//GAME CREATION:
	public void NewGame(ArrayList<String> parameters) throws BadParameters, AlreadyGameLoaded, NoUserLoggedIn {
		/* Format on Parameters:
		 * 		*[0]: "Breaker"|"Maker" (referring to user)
		 * 		*[1]: "Easy"|"Normal"|"Hard" (referring to game difficulty)
		 * 		*[2]: "Knuth"|"Darwin" (referring to Ai Type, will be ignored if [0]="Breaker", so practice is: leave it blank (arraysize =2))
		 */
		if (activeUser == null) throw new NoUserLoggedIn();
		if (activeGame != null) throw new AlreadyGameLoaded();
		if(parameters.size() < 2) throw new BadParameters("IncorrectSize");
		
		Boolean b; Diff d; Boolean aiIsFG;
		if(parameters.get(0) == "Breaker") b = true;
		else if(parameters.get(0) == "Maker") b = false;
		else throw new BadParameters("Expected \"Breaker\"|\"Maker\", got " + parameters.get(0));
		
		if(parameters.get(1) == "Easy") d = Diff.EASY;
		else if(parameters.get(1) == "Normal") d = Diff.NORMAL;
		else if(parameters.get(1) == "Hard") d = Diff.HARD;
		else throw new BadParameters("Expected \"Easy\"|\"Normal\"|\"Hard\", got " + parameters.get(1));
		if(!b) {
			if(parameters.size() < 3) throw new BadParameters("No third parameter found and Player is Maker");
			if(parameters.get(2) == "Knuth") aiIsFG = true;
			else if(parameters.get(2) == "Darwin") aiIsFG = false;
			else throw new BadParameters("Expected \"Knuth\"|\"Darwin\", got " + parameters.get(2));
		} else aiIsFG = false; //irrelevant
		activeGame = new Game(b,d,aiIsFG);
		if(b && aiIsFG) activeAi = new FiveGuess(activeGame);
		else activeAi = new Genetic(activeGame);
		try {
			if(activeGame.getUserIsBreaker()) activeGame.getBoard().setSecretCode(activeAi.generateSecretCode());
		} catch (CodeIsInvalid e) { //virtually impossible exceptions
			System.out.println("FatalErrorInDomain!");
		} catch (SecretCodeAlreadySet e) {
			System.out.println("FatalErrorInDomain!");
		}
	}
	//GAME LOADING/SAVING:
	public ArrayList<String> loadUsersSavedGames() throws NoUserLoggedIn {
		if (activeUser == null) throw new NoUserLoggedIn();
		return activeUser.getSavedGames();
	}
	public void deleteSavedGame(String gameId) throws UserTriedDeletingUnexistent, NoUserLoggedIn {
		if (activeUser == null) throw new NoUserLoggedIn();
		activeUser.deleteSavedGame(gameId); 
		ctrlPersistence.deleteGame(activeUser.getPlayerName(), gameId); //will throw exception, nogame?
	}
	public void loadSavedGame(String gameId) throws AlreadyGameLoaded, UserTriedDeletingUnexistent, NoUserLoggedIn { //probs more excp, nogame
		if (activeUser == null) throw new NoUserLoggedIn();
		if (activeGame != null) throw new AlreadyGameLoaded();
		activeUser.deleteSavedGame(gameId); 
		activeGame = (Game) ctrlPersistence.loadGame(activeUser.getPlayerName(), gameId);
		if (!activeGame.getUserIsBreaker() && activeGame.isAiFG() ==true) {
			//Need to reset Ai from beginning;
			activeAi = new FiveGuess(activeGame);
			//could move this to FiveGuess
			Board b = activeGame.getBoard();
			for(int i=0; i<b.getCorrections().size(); i++) {
				try {
					activeAi.codeBreakerTurn(b.getGuesses().get(i), b.getCorrections().get(i));
				} catch (Exception e){
					//Impossible or load bug, so will log it (remove from final version)
					System.out.println("FATAL ERROR ON AI LOAD!");
				}
			}
		} else activeAi = new Genetic(activeGame);
	}
	public void exitCurrentGame() throws NoActiveGame {
		if (activeGame == null) throw new NoActiveGame();
		activeGame = null;
	}
	public void saveAndExitCurrentGame(String gameId) throws NoActiveGame, UserSavesExistingID {
		if (activeGame == null) throw new NoActiveGame();
		activeUser.saveGame(gameId); //throws UserSavesExistingID
		ctrlPersistence.saveGame(activeUser.getPlayerName(), gameId, activeGame);
		ctrlPersistence.saveUser(activeUser.getPlayerName(), activeUser);
		activeGame = null;
	}
	//GAME PLAYING: MOST THINGS ARE NOT YET IMPLEMENTED
	public Vector<Integer> setSecretCode(Vector<Integer> sc) throws NoActiveGame, SecretCodeAlreadySet, MismatchedRole, BadlyFormedCode, CodeIsInvalid {
		//Returns the Vector<Integer> first guess of Ai
		if (activeGame == null) throw new NoActiveGame();
		if (activeGame.getUserIsBreaker()) throw new MismatchedRole();
		Code secret = new Code();
		secret.setCode(sc);
		activeGame.getBoard().setSecretCode(secret);
		Board b = activeGame.getBoard();

		Code c = new Code();
		try {
			c = activeAi.codeBreakerTurn(null, null);
			b.addGuess(c);
		} catch (CodeOrCorrectionNull e) {//virtually impossible exceptions
			System.out.println("FatalErrorInDomain!");
		} catch (CodeAlreadyUsed e) {
			System.out.println("FatalErrorInDomain!");
		} catch (UncorrectedGuessExists e) {
			System.out.println("Somebody somehow added the secret code AFTER a play");
		}
		return c.getCodeArray();
	}
	public ArrayList<String> getGameInfo() throws NoActiveGame {
		/* Format on Info:
		 * 		*[0]: "Breaker"|"Maker" (referring to user)
		 * 		*[1]: "Easy"|"Normal"|"Hard" (referring to game difficulty)
		 * 		*[2]: "Ranked"|"Unranked" (referring to score acquisition)
		 * 		*[3]: "Knuth"|"Darwin" (referring to Ai Type, won't exist if [0]="Breaker")
		 */
		if (activeGame == null) throw new NoActiveGame();
		ArrayList<String> Info = new ArrayList<String>();
		if(activeGame.getUserIsBreaker() == true) Info.add("Breaker");
		else Info.add("Maker");
		
		if(activeGame.getDifficulty() == Diff.EASY) Info.add("Easy");
		else if(activeGame.getDifficulty() == Diff.NORMAL) Info.add("Normal");
		else if(activeGame.getDifficulty() == Diff.HARD) Info.add("Hard");
		
		if(activeGame.isRanked()) Info.add("Ranked");
		else Info.add("Unranked");
		
		if(activeGame.getUserIsBreaker() == false) {
			if(activeGame.isAiFG()) Info.add("Knuth");
			else Info.add("Darwin");
		}
		return Info;
	}
	public ArrayList<Object> getBoard() {
		/* Format:
		 * 		*[0]: Vector<Integer> with the secretCode
		 * 		( i odd;
		 * 		*[i]: Vector<Integer> with the code played on round (i+1)/2 (number of positions depending on difficulty)
		 * 		*[i+1]: Vector<Integer> with the correction: [0]: whites, [1]: blacks (2 positions)
		 * 		) *
		 */
		return activeGame.getBoard().parse();
	}
	public Vector<Integer> playCode(Vector<Integer> code) throws NoUserLoggedIn, NoActiveGame, MismatchedRole, GameIsFinished, BadlyFormedCode, CodeIsInvalid { 
		//MismatchedRole means he tried to put a Code when it's a maker! + add exception for ended games!
		//Returns the Correction of the code played, in format [0]: whites, [1]: blacks
		if (activeUser == null) throw new NoUserLoggedIn();
		if (activeGame == null) throw new NoActiveGame();
		if (! activeGame.getUserIsBreaker()) throw new MismatchedRole();
		if (activeGame.hasEnded()) throw new GameIsFinished();
		Code guess = new Code();
		guess.setCode(code);
		try {
			Board b = activeGame.getBoard();
			b.addGuess(guess);
			Correction c = guess.correct(b.getSecretCode());
			if(b.addCorrection(c)) endGame();
			return c.parse(); //warn guillem!
		} catch(UncorrectedGuessExists e) {
			System.out.println("Fatal error in Domain!");
		} catch(NoGuessToBeCorrected e) {
			System.out.println("Fatal error in Domain!");
		} catch(IncorrectCorrection e) {
			System.out.println("Fatal error in Domain!");
		}
		return null; //fake, never gets here
	}
	public Vector<Integer> playCorrection(int w, int b) throws NoUserLoggedIn, NoActiveGame, MismatchedRole, NoGuessToBeCorrected, InvalidNumberOfPins, IncorrectCorrection {
		if (activeUser == null) throw new NoUserLoggedIn();
		if (activeGame == null) throw new NoActiveGame();
		if (! activeGame.getUserIsBreaker()) throw new MismatchedRole();
		//might call endGame
		Correction c = new Correction(w,b);
		Board board = activeGame.getBoard();
		try {
			if(board.addCorrection(c)) { //probably exception!
				endGame();
				return null;
			} else {
				Code lastCode = board.getGuesses().get(board.getGuesses().size()-1);
				Code newCode = activeAi.codeBreakerTurn(lastCode, c);
				board.addGuess(newCode);
				return newCode.getCodeArray();
				
			}
		} catch (CodeAlreadyUsed e) {
			System.out.println("Error on Ai!");
		} catch (CodeOrCorrectionNull e) {
			System.out.println("FatalError");
		}catch (UncorrectedGuessExists e) {
			System.out.println("FatalError");
		} catch (CodeIsInvalid e) {
			System.out.println("Error on Ai!");
		}
		return null; //fake never gets here
	}
	private void endGame() throws NoUserLoggedIn, NoActiveGame { //PRIVATE FUNCTION FOR MANAGING THE END OF THE GAME!
		if (activeGame == null) throw new NoActiveGame();
		if (activeUser == null) throw new NoUserLoggedIn();
		Board b = activeGame.getBoard();
		float F; //score Multiplier by difficulty;
		if(activeGame.getDifficulty() == Diff.EASY) F = 0.5f;
		else if(activeGame.getDifficulty() == Diff.NORMAL) F = 1.f;
		else F = 1.5f;
		int guesses = b.getCorrections().size();
		float score = (13-guesses)*F;
		if (!b.hasWon()) { score = 0; guesses = 13; }
		if(activeGame.getUserIsBreaker()) {
			activeUser.updateRecords(b.hasWon(), score, guesses);
			//Record persistence here;
			ctrlPersistence.saveUser(activeUser.getPlayerName(), activeUser);
			//activeGame=null; better leave it not touched, let Presentation get info until game is closed. Should add an exception in rest of classes?
		} else { //Ai was breaker, not user
			String aiName = "Darwin";
			if(activeGame.isAiFG()) aiName = "Knuth";
			Player aiPlayer = (Player) ctrlPersistence.loadAi(aiName);
			aiPlayer.updateRecords(b.hasWon(), score, guesses);
			ctrlPersistence.saveAi(aiName, aiPlayer); //this surrounded on try catch!
		}
	}
}
