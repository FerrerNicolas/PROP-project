package permanencia.ObjectSaver;

import java.io.*;
import java.util.HashMap;

public class ObjectSaver {
	
	private HashMap<String,Integer> IDs;
	private String savingDirectory; //everything is saved  in the same place
	private File IDsFile;
	
	public ObjectSaver(String savingDirectory) {
		this.savingDirectory = savingDirectory;
		IDsFile = new File(this.savingDirectory + "IDs.list");
		if (!IDsFile.exists()) {
			IDs = new HashMap<String,Integer>();
			IDs.put("FiveGuess", 0);
			IDs.put("Genetic", 1);
			saveObject(IDsFile, IDs);
			Player p1 = new Player("FiveGuess");
			Player p2 = new Player ("Genetic");
			savePlayer(p1);
			savePlayer(p2);
		}else {
			IDs = (HashMap<String,Integer>) loadObject(IDsFile);
		}
	}
	
	private void saveObject(File dir, Object obj) {
		try {
			FileOutputStream fos = new FileOutputStream(dir, false);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.close();
		} catch (FileNotFoundException e) {
			System.out.println("this shouldn't happen: FileNotFoundException when saving object "+obj+ " to "+dir);
		} catch (IOException e) {
			System.out.println("this shouldn't happen: IOException when saving object "+obj+ " to "+dir);
		}
	}
	
	private void saveObject(String filename, Object obj) {
		File dir = new File(savingDirectory + filename);
		saveObject(dir,obj);
	}
	
	private Object loadObject(File dir) {
		try {
			FileInputStream fis = new FileInputStream(dir);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object tmp = ois.readObject();
			ois.close();
			return tmp;
		} catch (FileNotFoundException e) {
			System.out.println("this shouldn't happen: FileNotFoundException when loading file "+dir);
		} catch (IOException e) {
			System.out.println("this shouldn't happen: IOException when loading file "+dir);
		} catch (ClassNotFoundException e) {
			System.out.println("this shouldn't happen: ClassNotFoundException when loading file "+dir);
		}
		return null; //we should never get here
	}
	
	private Object loadObject(String filename) {
		File dir = new File(savingDirectory + filename);
		return loadObject(dir);
	}
	
	public void savePlayer(Player p) {
		Integer playerID= -1;
		//get or add ID of player	
		//Searching for the player
		if (IDs.containsKey(p.getPlayerName())) {
			//Player already has a ID
			playerID = IDs.get(p.getPlayerName());
		}else {
			//Player didn't have a ID
			playerID = IDs.size();
			IDs.put(p.getPlayerName(), playerID);
			//Saving IDs file with the new ID
			saveObject(IDsFile, IDs);
		}
		
		// write player to file
		if (playerID !=-1) {
			saveObject(playerID+".player", p);
		}else {
			System.out.println("Error playerID was -1");
		}
		
	}
	
	public Player loadPlayer(String playername) {
		Player result= null;
		Integer playerID= -1;
		//Searching for the player
		if (IDs.containsKey(playername)) {
			//Player has an ID
			playerID = IDs.get(playername);
		}else {
			System.out.println("Player was never given a ID and saved");
		}

		
		if (playerID != -1) {
			result = (Player) loadObject(playerID+".player");
		}else {
			System.out.println("Error playerID was -1");
		}
		return result;
	}
	
	public void saveUser(User p) {
		Integer playerID= -1;
		//Searching for the player
		if (IDs.containsKey(p.getPlayerName())) {
			//Player already has a ID
			playerID = IDs.get(p.getPlayerName());
		}else {
			//Player didn't have a ID
			playerID = IDs.size();
			IDs.put(p.getPlayerName(), playerID);
			//Saving IDs file with the new ID
			saveObject(IDsFile, IDs);
		}
		
		// write player to file
		if (playerID !=-1) {
			saveObject(playerID+".user", p);
		}else {
			System.out.println("Error playerID was -1");
		}
		
	}
	
	public User loadUser(String username) {
		User result= null;
		Integer playerID= -1;
		//Searching for the player
		if (IDs.containsKey(username)) {
			//Player has an ID
			playerID = IDs.get(username);
		}else {
			System.out.println("User was never given a ID and saved");
		}
		
		if (playerID != -1) {
			result = (User) loadObject(playerID+".user");
		}else {
			System.out.println("Error playerID was -1");
		}
		return result;
	}
	
}
