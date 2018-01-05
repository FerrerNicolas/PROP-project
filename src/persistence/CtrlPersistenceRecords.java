package persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import domain.*;
import model.*;


public class CtrlPersistenceRecords { //Author:Luis

    private GlobalRecords globalRecords = null;
    private Ranking ranking = null;

    private File globalRecordsFile;
    private File rankingsFile;

    private String savingDirectory; //everything is saved  in the same place
    private CtrlDomainRecords ctrlDomainRecords;

    public CtrlPersistenceRecords() throws FileNotFoundException, IOException, ClassNotFoundException {

        this.savingDirectory = "src/persistence/DATARECORDS/";

        // Global Records
        this.globalRecordsFile = new File(this.savingDirectory + CtrlPersistenceRecords.GLOBAL_RECORDS_FILE_NAME);

        if (this.globalRecordsFile.exists()) {
            // If the file exists, there are records in the database
            this.globalRecords = (GlobalRecords) loadObject(this.globalRecordsFile);
        } else {
            this.globalRecords = new GlobalRecords();
        }

        // Rankings 
        this.rankingsFile = new File(this.savingDirectory + CtrlPersistenceRecords.RANKINGS_FILE_NAME);

        if (this.rankingsFile.exists()) {
            this.ranking = (Ranking) loadObject(this.rankingsFile);
        } else {
            this.ranking = new Ranking();
        }

    }

    //Same functions as CtrlPersistence
    private Object loadObject(File dir) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(dir);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object tmp = ois.readObject();
        ois.close();
        return tmp;
    }

    private Object loadObject(String filename) throws FileNotFoundException, ClassNotFoundException, IOException {
        File dir = new File(savingDirectory + filename);
        return loadObject(dir);
    }

    private void saveObject(File dir, Object obj) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(dir, false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        oos.close();

    }

    private void saveObject(String filename, Object obj) throws FileNotFoundException, IOException {
        File dir = new File(savingDirectory + filename);
        saveObject(dir, obj);
    }

    // save the list and give the modified/updated list
    public GlobalRecords saveGlobalRecords(GlobalRecords globalRecords) throws FileNotFoundException, IOException {
        File f = new File(this.savingDirectory + CtrlPersistenceRecords.GLOBAL_RECORDS_FILE_NAME);
        saveObject(f, globalRecords);
        this.globalRecords = globalRecords;
        return this.globalRecords;
    }

    public Ranking saveRankings(Ranking ranking) throws FileNotFoundException, IOException {
        File f = new File(this.savingDirectory + CtrlPersistenceRecords.RANKINGS_FILE_NAME);
        saveObject(f, ranking);
        this.ranking = ranking;
        return this.ranking;
    }

    public Ranking getRankings() {
        return this.ranking;
    }

    public GlobalRecords getGlobalRecords() {
        return this.globalRecords;
    }

    public static final String RANKINGS_FILE_NAME = "rankings.list";
    public static final String GLOBAL_RECORDS_FILE_NAME = "global_records.list";

}

// Update Records, save
// And retreive records