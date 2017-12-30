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
    private JTextField SelectedGame;

    private void initializeComponents() {
        gameList = new JList();
        loadButton = new JButton("Load Game");
        eraseButton = new JButton("Erase Game");
        backButton = new JButton("Back");
        SelectedGame = new JTextField();

        SelectedGame.setText("Write a name of a Game or select it");

        gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setTitle("Saved Games");

        gameList.setListData(ctrlPresentation.getSavedGamesList());

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

    }

    public LoadGameView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initializeComponents();
    }

    private void backButtonPressed() {
        ctrlPresentation.loadMenuView();
    }

    private void clickOnListElement(ListSelectionEvent event) {
        String s = (String) gameList.getSelectedValue();
        SelectedGame.setText(s);
    }

    private void loadButtonPressed() {
        ctrlPresentation.loadGame(SelectedGame.getText());
    }

    private void eraseButtonPressed() {
        ctrlPresentation.eraseGame(SelectedGame.getText());
        //We reload the saved games list
        Object[] p = ctrlPresentation.getSavedGamesList();
        gameList.setListData(p);

    }
}
