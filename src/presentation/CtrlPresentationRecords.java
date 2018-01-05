package presentation;

import domini.Tuple.Tuple;
import model.Player;
import domain.CtrlDomain;
import domain.CtrlDomainRecords;
import java.io.FileNotFoundException;
import java.io.IOException;
import persistence.CtrlPersistenceRecords;


public class CtrlPresentationRecords { //Author:Luis

    protected CtrlDomainRecords cdr;

    protected RecordsGUI recordsGui;
    protected RankingsGUI rankingsGui;

    private CtrlDomain domain;
    private String playername;
    public CtrlPresentationRecords() throws IOException, FileNotFoundException, ClassNotFoundException {
        try {
            this.domain = new CtrlDomain();

        } catch (Exception e) {

            e.printStackTrace();

        }
        // Player name
        String playername = this.domain.getLoggedUsername();

        Player player =  (Player)this.domain.getInstanceOfPlayer(playername);
        // max Score for the rankings

        Tuple playerScore = new Tuple(player.getPlayerName(), player.getMaxScore());

        //loadRankingsGUI();
        //this.recordsGui = new RecordsGUI(this.domain.getInstanceOfPlayer(playername),domain);
        //this.rankingsGui = new RankingsGUI(this.domain.getInstanceOfPlayer(playername),domain);
    }


    public void loadRecordsGUI() throws IOException, FileNotFoundException, ClassNotFoundException {
        this.recordsGui = new RecordsGUI(new CtrlDomainRecords(new CtrlPersistenceRecords()));
    }

    public void loadRankingsGUI() throws IOException, FileNotFoundException, ClassNotFoundException {
        this.rankingsGui = new RankingsGUI(new CtrlDomainRecords(new CtrlPersistenceRecords()));
    }
}