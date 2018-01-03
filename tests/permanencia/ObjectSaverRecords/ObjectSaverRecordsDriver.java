package permanencia.ObjectSaverRecords;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ObjectSaverRecordsDriver {
	
	
	public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
		
		ObjectSaverRecords o = new ObjectSaverRecords("c:/persistenceRecords/DATA/"); 
		
		Player p1 = new Player("Luis");
       		Player p2 = new Player("Victor");
                Player p3 = new Player("Javi");

		p1.updateRecords(false, 3f, 12);
		p1.updateRecords(false, 3f, 12);
		p1.updateRecords(false, 3f, 12);
		p1.updateRecords(true, 14f, 6);
                
                p2.updateRecords(true, 20f, 5);
                
                p3.updateRecords(true, 5f, 2);
              
                
		GlobalRecords gr = new GlobalRecords();
                gr.update(p1);
                
                Ranking ranking = new Ranking();
                Tuple t1 = new Tuple(p1.getPlayerName(),p1.getMaxScore());
                Tuple t2 = new Tuple(p2.getPlayerName(),p2.getMaxScore());
                Tuple t3 = new Tuple(p3.getPlayerName(),p3.getMaxScore());
                ranking.insert(t1);
                ranking.insert(t2);
                ranking.insert(t3);
                ranking.insert(t1);
                ranking.insert(t2);
                ranking.insert(t3);
                ranking.insert(t1);
                ranking.insert(t2);
                ranking.insert(t3);
                ranking.insert(t1);
                ranking.insert(t2);
                ranking.insert(t3);//12 insertions . Should save 10 only
                
                
                o.saveRankings(ranking);
                o.saveGlobalRecords(gr);
            GlobalRecords grSaved = o.getGlobalRecords();
            Ranking savedRanking = o.getRankings();
	    System.out.println("Original player stats:");
	    System.out.println(p1.getPlayerName());
	    System.out.println(p1.getFinishedGames());
	    System.out.println(p1.getGamesLost());
	    System.out.println(p1.getGamesWon());
	    System.out.println(p1.getMaxScore());
	    System.out.println(p1.getMinGuesses());
	    System.out.println(p1.getTotalScore());
	    System.out.println("Loaded GlobalRecords:");
            String test = grSaved.getGlobalRecords().get(1).getPlayerName();
	    System.out.println(grSaved.getGlobalRecords());
            System.out.println("Loaded Rankings(Tuples and Strings):");
   	    System.out.println(savedRanking.getRankings());
            System.out.println(savedRanking.getRankingstoString());

                    
    }

}
