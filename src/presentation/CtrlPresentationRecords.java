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
            this.cdr = new CtrlDomainRecords(new CtrlPersistenceRecords());
            this.cp =cp;
        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    public void loadRecordsGUI() throws IOException, FileNotFoundException, ClassNotFoundException {
        this.recordsGui = new RecordsGUI(new CtrlDomainRecords(new CtrlPersistenceRecords()),cp);
    }

    public void loadRankingsGUI() throws IOException, FileNotFoundException, ClassNotFoundException {
        this.rankingsGui = new RankingsGUI(new CtrlDomainRecords(new CtrlPersistenceRecords()),cp );
    }
}