package model;
import java.util.*;
import exceptions.*;
//Victor
public class User extends Player {
	private ArrayList<String> savedGames;
	private static final long serialVersionUID = 1L;
	public User(String name) {
		super(name);
		savedGames = new ArrayList<String>();
	}
	public ArrayList<String> getSavedGames(){
		return savedGames;
	}
	public void saveGame(String gameID) throws UserSavesExistingID {
		if (savedGames.contains(gameID)) throw (new UserSavesExistingID());
		savedGames.add(gameID);
	}
	public void deleteSavedGame(String gameID) throws GameUnexistentForUser {
		if (!savedGames.contains(gameID)) throw (new GameUnexistentForUser());
		savedGames.remove(gameID); 
	}
}

