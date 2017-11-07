package model;
import java.util.*;

public class User extends Player {
	private ArrayList<String> SavedGames;
	public User(String name) {
		super(name);
		SavedGames = new ArrayList<String>();
	}
	public ArrayList<String> getSavedGames(){
		return SavedGames;
	}
	public void saveGame(String gameID) {
		SavedGames.add(gameID); //SHOULD CHECK REPEATED!! procrastinated until exception implementing
	}
	public void deleteSavedGame(String gameID) {
		SavedGames.remove(gameID); 
	}
}