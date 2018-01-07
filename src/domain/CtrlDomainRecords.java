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

public class CtrlDomainRecords { //Author:Luis

    private CtrlDomain domain;

    private CtrlPersistenceRecords persistenceRecords;

    public CtrlDomainRecords(CtrlPersistenceRecords cpr) throws FileNotFoundException, IOException, ClassNotFoundException {

        this.persistenceRecords = cpr;

    }

    /**
     * Insert into globalrecords and rankings
     *
     * @param p
     * @return Player
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void insertInRR(Player p) throws FileNotFoundException, IOException {
        Tuple t = new Tuple(p.getPlayerName(), p.getTotalScore());

        // Rankings handling
        Ranking r = this.persistenceRecords.getRankings();
        r.insert(t);
        this.persistenceRecords.saveRankings(r);

        //Global records Handling
        GlobalRecords records = this.persistenceRecords.getGlobalRecords();
        records.update(p);
        this.persistenceRecords.saveGlobalRecords(records);
    }

    public Ranking getRankings() {
        return this.persistenceRecords.getRankings();
    }

    public GlobalRecords getGlobalRecords() {
        return this.persistenceRecords.getGlobalRecords();
    }
}