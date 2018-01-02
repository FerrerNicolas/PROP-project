package presentation;

import domini.Tuple.Tuple;
import model.Player;
import domain.CtrlDomain;
import domain.CtrlDomainRecords;


public class CtrlPresentationRecords {

    protected CtrlDomainRecords cdr;

    protected RecordsGUI recordsGui;

    private CtrlDomain domain;

    public CtrlPresentationRecords() {
        try {
            this.domain = new CtrlDomain();

        } catch (Exception e) {
        
            e.printStackTrace();
        
        }
        // Plyer name
        String playername = this.domain.getLoggedUsername();
        
        Player player =  (Player)this.domain.getInstanceOfPlayer(playername);
        // max Score for the rankings
        
        Tuple playerScore = new Tuple(player.getPlayerName(), player.getMaxScore());
        
        
        this.recordsGui = new RecordsGUI(this.domain.getInstanceOfPlayer(playername), domain);

    }
        
    
}
