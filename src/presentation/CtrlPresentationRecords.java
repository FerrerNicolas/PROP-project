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

    private CtrlDomain domain;

    public CtrlPresentationRecords() throws IOException, FileNotFoundException, ClassNotFoundException {
        /*try {
            this.domain = new CtrlDomain();

        } catch (Exception e) {

            e.printStackTrace();

        }
        */
            }


    public void loadRecordsGUI() throws IOException, FileNotFoundException, ClassNotFoundException {
        this.recordsGui = new RecordsGUI(new CtrlDomainRecords(new CtrlPersistenceRecords()));
    }

    public void loadRankingsGUI() throws IOException, FileNotFoundException, ClassNotFoundException {
        this.rankingsGui = new RankingsGUI(new CtrlDomainRecords(new CtrlPersistenceRecords()));
    }
}