package presentation;

import domain.CtrlDomain;
import domain.CtrlDomainRecords;
import persistence.CtrlPersistenceRecords;
import java.io.FileNotFoundException;
import java.io.IOException;




public class CtrlPresentationRecords { //Author:Luis

    protected CtrlDomainRecords cdr;

    protected RecordsGUI recordsGui;
    protected RankingsGUI rankingsGui;
    private CtrlPresentation cp;

    public CtrlPresentationRecords(CtrlPresentation cp) throws IOException, FileNotFoundException, ClassNotFoundException {
        try{
            this.cp = cp;
            
        } catch (Exception e) {

            e.printStackTrace();

        }
        
            }


    public void loadRecordsGUI() throws IOException, FileNotFoundException, ClassNotFoundException {
        this.cdr = new CtrlDomainRecords();
        this.recordsGui = new RecordsGUI(this.cdr, this.cp);
    }

    public void loadRankingsGUI() throws IOException, FileNotFoundException, ClassNotFoundException {
        this.cdr = new CtrlDomainRecords();
        this.rankingsGui = new RankingsGUI(this.cdr, this.cp);
    }
}