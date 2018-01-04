/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import domain.CtrlDomain;
import domain.CtrlDomainRecords;
import domini.Tuple.Tuple;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import persistence.CtrlPersistenceRecords;

/**
 *
 * @author Luis Mendes
 */
public class RankingsGUI {



    //View Objects
    private JFrame frame;
    private JButton newButton;
    private JTable grTable;
    private JTable rTable;

    private CtrlDomainRecords records;
    private CtrlDomain ctrlDomain;
    CtrlPresentationRecords cpr;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            RankingsGUI g = new RankingsGUI(new CtrlDomainRecords(new CtrlPersistenceRecords()));
            g.initialize();
        } catch (IOException ex) {
            Logger.getLogger(RecordsGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecordsGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RankingsGUI(CtrlDomainRecords recordsControl) {
        this.records = recordsControl;
    }

    public RankingsGUI(Object player, CtrlDomain domain) {

        try {
            this.ctrlDomain = domain;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();

        //Frame dimensions
        Dimension dimension = new Dimension(500,400);
        frame.setPreferredSize(dimension);

        frame.setResizable(true);
        frame.setLocation(dimension.width/2-frame.getSize().width/2, dimension.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);


        ArrayList<model.Tuple> rankings = this.records.getRankings().getRanking();

        Object[] columnNames = new Object[2];
        columnNames[0] = "Player";
        columnNames[1] = "Value";

        rTable = new JTable();


        DefaultTableModel dtm = (DefaultTableModel) rTable.getModel();
        dtm.setColumnIdentifiers(columnNames);
        for (int i = 0; i < rankings.size(); i++) {
            Object[] row = new Object[3];
            row[0] = rankings.get(i).getPlayerName();
            String value = "";
            if (rankings.get(i).getValue() != null) {
                row[1] = rankings.get(i).getValue().toString();
            } else {
                row[1] = "N/A";
            }

            dtm.addRow(row);
        }


        JScrollPane pane = new JScrollPane(rTable);
        panel.add(rTable.getTableHeader(), BorderLayout.CENTER);

        panel.add(pane);

    }

}