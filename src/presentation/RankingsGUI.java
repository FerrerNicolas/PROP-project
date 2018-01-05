/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import static com.sun.glass.ui.Cursor.setVisible;
import domain.CtrlDomainRecords;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis Mendes
 */
public class RankingsGUI {



    //View Objects
    private JFrame frame;
    private JTable rTable;
    private JButton backButton;
    private CtrlDomainRecords records;
    private CtrlPresentation ctrlPresentation;

    public RankingsGUI(CtrlDomainRecords recordsControl) {
        try{
            this.records = recordsControl;
        } catch (Exception e){
            e.printStackTrace();
        }
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        ctrlPresentation = new CtrlPresentation();

        //Frame dimensions
        Dimension dimension = new Dimension(500,400);
        frame.setPreferredSize(dimension);
        frame.setSize(dimension);
        frame.setResizable(true);
        frame.setLocation(dimension.width/2-frame.getSize().width/2, dimension.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //Back button handling
        backButton = new JButton();
        backButton.setText("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                backButtonPressed();
            }
        });
        
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
        panel.add(backButton);
        
        
        
    }
    private void backButtonPressed() {
        frame.setVisible(false);
        ctrlPresentation.loadMenuView();
    }

}