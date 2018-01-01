package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.*;

import model.GlobalRecords;

import model.Player;
import model.Ranking;
import model.Tuple;

public class CtrlDomainRecords {

    private CtrlDomain domain;

    private CtrlPersistenceRecords persistenceRecords;

    public CtrlDomainRecords(CtrlDomain domain) throws FileNotFoundException, IOException, ClassNotFoundException {

        this.persistenceRecords = new CtrlPersistenceRecords();

        this.domain = domain;
    }

    /**
     * Insert into rankings
     *
     * @param p
     * @return Player
     */
    public Player insertInRR(Player p) {
        Tuple t = new Tuple(p.getPlayerName(), p.getMaxScore());

        // Rankings handling
        Ranking r = this.persistenceRecords.getRankings().insert(t);
        try {
            this.persistenceRecords.saveRankings(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Global records Hndling
        
        GlobalRecords records = this.persistenceRecords.getGlobalRecords();
        records.update(p);
        try {
            this.persistenceRecords.saveGlobalRecords(records);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return p;
    }
    
    public Ranking getRankings() {
        return this.persistenceRecords.getRankings();
    }
    
    public GlobalRecords getGlobalRecords() {
        return this.persistenceRecords.getGlobalRecords();
    }
}
