package model;
import java.util.*;
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
	public void saveGame(String gameID) {
		savedGames.add(gameID); //SHOULD CHECK REPEATED!! procrastinated until exception implementing
	}
	public void deleteSavedGame(String gameID) {
		savedGames.remove(gameID); 
	}
}