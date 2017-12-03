package model;

import java.io.*;
import java.util.HashMap;

public class ObjectSaver {
	
	public static void savePlayer(Player p, String savedPlayersFolderDirectory) {
		File file;
		File UUIDs_file;
		Integer playerUUID= -1;
		//get or add UUID of player
		try {
			UUIDs_file = new File(savedPlayersFolderDirectory + "UUIDs.list");
			
			if (!UUIDs_file.exists()) {
				UUIDs_file.createNewFile();
				HashMap<String,Integer> tmp = new HashMap<String,Integer>();
				//In the future the first two should be the AIs
				tmp.put("FakePlayer", 0);
				FileOutputStream fos = new FileOutputStream(UUIDs_file, false);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(tmp);
				oos.close();
			}
			
			//reading UUIDs file
			FileInputStream fis;
			fis = new FileInputStream(UUIDs_file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			HashMap<String,Integer> UUIDs = (HashMap<String,Integer>) ois.readObject();
			ois.close();
			
			//Searching for the player
			if (UUIDs.containsKey(p.getPlayerName())) {
				//Player already has a UUID
				playerUUID = UUIDs.get(p.getPlayerName());
			}else {
				//Player didn't have a UUID
				playerUUID = UUIDs.size();
				UUIDs.put(p.getPlayerName(), playerUUID);
				//Saving UUIDs file with the new UUID
				FileOutputStream fos = new FileOutputStream(UUIDs_file, false);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(UUIDs);
				oos.close();
			}
			
			
		}catch(IOException e) {
			System.out.println("this shouldn't happen: IOException when loading UUIDs file");
		} catch (ClassNotFoundException e) {
			System.out.println("this shouldn't happen: ClassNotFoundException when loading UUIDs file");
		}
		
		// write player to file
		if (playerUUID !=-1) {
			try {			
				file = new File(savedPlayersFolderDirectory + playerUUID + ".ser");
				try {
					if (!file.exists()) {
						file.createNewFile();
					}else {
						file.delete();
						file.createNewFile();
					}
				}catch(IOException e2) {
					System.out.println("this shouldn't happen: IOException when creating file");
					System.out.println(e2.getMessage());
				}
				FileOutputStream fos = new FileOutputStream(file, false);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(p);
				oos.close();
				
			} catch (FileNotFoundException e) {
				System.out.println("this shouldn't happen: FileNotFoundException when saving player");
			} catch (IOException e) {
				System.out.println("this shouldn't happen: IOException when saving player");
			}
		}else {
			System.out.println("Error playerUUID was -1");
		}
		
	}
	
	public static Player loadPlayer(String playername,  String savedPlayersFolderDirectory) {
		Player result = new Player("tmp");
		File file;
		
		File UUIDs_file;
		Integer playerUUID= -1;
		//get or add UUID of player
		try {
			UUIDs_file = new File(savedPlayersFolderDirectory + "UUIDs.list");
			
			if (!UUIDs_file.exists()) {
				System.out.println("Error UUIDs file doesn't exist");
			}
			
			//reading UUIDs file
			FileInputStream fis;
			fis = new FileInputStream(UUIDs_file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			HashMap<String,Integer> UUIDs = (HashMap<String,Integer>) ois.readObject();
			ois.close();
			
			//Searching for the player
			if (UUIDs.containsKey(playername)) {
				//Player already has a UUID
				playerUUID = UUIDs.get(playername);
			}else {
				System.out.println("Player was never given a UUID and saved");
			}
			
			
		}catch(IOException e) {
			System.out.println("this shouldn't happen: IOException when loading UUIDs file");
		} catch (ClassNotFoundException e) {
			System.out.println("this shouldn't happen: ClassNotFoundException when loading UUIDs file");
		}
		
		if (playerUUID != -1) {
			try {
				file = new File(savedPlayersFolderDirectory + playerUUID + ".ser");
				FileInputStream fis;
				fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				result = (Player) ois.readObject();
				ois.close();
				
			} catch (FileNotFoundException e) {
				System.out.println("this shouldn't happen: FileNotFoundException when loading player");
			} catch (ClassNotFoundException e) {
				System.out.println("this shouldn't happen: ClassNotFoundException when loading player");
			} catch (IOException e) {
				System.out.println("this shouldn't happen: IOException when loading player");
			}
		}else {
			System.out.println("Error playerUUID was -1");
		}
		return result;
	}
	
}
