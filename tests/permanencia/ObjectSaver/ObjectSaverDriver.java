package permanencia.ObjectSaver;

public class ObjectSaverDriver {
	
	
	public static void main(String[] args) {
		
		Player p = new Player("Zenny");
		p.updateRecords(false, 3f, 12);
		p.updateRecords(false, 3f, 12);
		p.updateRecords(false, 3f, 12);
		p.updateRecords(false, 3f, 12);
		p.updateRecords(false, 3f, 12);
		p.updateRecords(true, 14f, 6);
		ObjectSaver.savePlayer(p, "tests/permanencia/ObjectSaver/savedPlayers/");
	    Player p2 = ObjectSaver.loadPlayer("Zenny", "tests/permanencia/ObjectSaver/savedPlayers/");
	    System.out.println("Original player stats:");
	    System.out.println(p.getPlayerName());
	    System.out.println(p.getFinishedGames());
	    System.out.println(p.getGamesLost());
	    System.out.println(p.getGamesWon());
	    System.out.println(p.getMaxScore());
	    System.out.println(p.getMinGuesses());
	    System.out.println(p.getTotalScore());
	    System.out.println("Loaded player stats:");
	    System.out.println(p2.getPlayerName());
	    System.out.println(p2.getFinishedGames());
	    System.out.println(p2.getGamesLost());
	    System.out.println(p2.getGamesWon());
	    System.out.println(p2.getMaxScore());
	    System.out.println(p2.getMinGuesses());
	    System.out.println(p2.getTotalScore());
		
    }

}
