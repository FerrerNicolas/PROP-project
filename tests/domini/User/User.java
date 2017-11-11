package domini.User;
import java.util.*;
import exceptions.*;
//Victor
public class User extends Player {
	private ArrayList<String> savedGames;
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
	public void deleteSavedGame(String gameID) throws UserTriedDeletingUnexistent {
		if (!savedGames.contains(gameID)) throw (new UserTriedDeletingUnexistent());
		savedGames.remove(gameID); 
	}
}

