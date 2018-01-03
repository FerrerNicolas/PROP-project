package presentation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class LoadGameView extends JFrame {
    private CtrlPresentation ctrlPresentation;

    private JList gameList;
    private JButton loadButton;
    private JButton eraseButton;
    private JButton backButton;
    private JTextField selectedGame;
    private JPanel panel;
    private JLabel gameLabel;

    private void initializeComponents() {

        loadButton.setText("Load Game");
        eraseButton.setText("Erase Game");
        backButton.setText("Back");

        gameLabel.setText("Write a name of a Game or select it");

        setSize(400,400);
        setTitle("Load a Game");

        gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setTitle("Saved Games");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gameList.setListData(ctrlPresentation.getSavedGamesList());
        System.out.println(gameList.getSize());
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                loadButtonPressed();
            }
        });

        eraseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                eraseButtonPressed();
            }
        });

        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                backButtonPressed();
            }
        });
        gameList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent event) {
                clickOnListElement(event);
            }
        });
        setContentPane(panel);
    }

    LoadGameView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initializeComponents();
    }

    private void backButtonPressed() {
        setVisible(false);
        ctrlPresentation.loadMenuView();
    }

    private void clickOnListElement(ListSelectionEvent event) {
        String s = (String) gameList.getSelectedValue();
        selectedGame.setText(s);
    }

    private void loadButtonPressed() {
        ctrlPresentation.loadGame(selectedGame.getText());
    }

    private void eraseButtonPressed() {
        ctrlPresentation.eraseGame(selectedGame.getText());
        //We reload the saved games list
        Object[] p = ctrlPresentation.getSavedGamesList();
        gameList.setListData(p);

    }


}
