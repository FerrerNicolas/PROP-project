package presentation;

import domain.CtrlDomain;
import model.Diff;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class NewGameView {
    private CtrlPresentation ctrlPresentation;

    private JRadioButton makerButton;
    private JRadioButton breakerButton;
    private JLabel playAsText;

    private JRadioButton easyButton;
    private JRadioButton mediumButton;
    private JLabel difficultyText;
    private JRadioButton hardButton;

    private JLabel aiText;
    private JRadioButton geneticButton;
    private JRadioButton fiveGuessButton;
    private JButton createButton;
    private JButton backButton;

    private ButtonGroup roleGroup;
    private ButtonGroup difficultyGroup;
    private ButtonGroup aiGroup;


    private void initializeComponents() {
        playAsText = new JLabel();
        playAsText.setText("Choose a side to play as this game");

        roleGroup = new ButtonGroup();
        makerButton = new JRadioButton("Code Maker");
        breakerButton = new JRadioButton("Code Breaker");
        roleGroup.add(makerButton);
        roleGroup.add(breakerButton);

        difficultyText = new JLabel();
        difficultyText.setText("Choose a difficulty for the game");

        difficultyGroup = new ButtonGroup();
        easyButton = new JRadioButton("Easy");
        mediumButton = new JRadioButton("Medium");
        hardButton = new JRadioButton("Hard");
        difficultyGroup.add(easyButton);
        difficultyGroup.add(mediumButton);
        difficultyGroup.add(hardButton);

        aiText = new JLabel();
        aiText.setVisible(false);
        aiText.setText("Choose an AI to play against");

        aiGroup = new ButtonGroup();
        geneticButton = new JRadioButton("Genetic AI");
        fiveGuessButton = new JRadioButton("FiveGuess AI");
        geneticButton.setVisible(false);
        fiveGuessButton.setVisible(false);
        aiGroup.add(geneticButton);
        aiGroup.add(fiveGuessButton);

        //default parameters are a medium game as breaker with fiveguess
        fiveGuessButton.setSelected(true);
        mediumButton.setSelected(true);
        breakerButton.setSelected(true);


        createButton = new JButton("Create Game");
        backButton = new JButton("Back");

        //ItemListener activates only if state changes not on every button press
        makerButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                int state = event.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    geneticButton.setVisible(false);
                    fiveGuessButton.setVisible(false);
                    aiText.setVisible(false);
                }
            }
        });

        breakerButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                int state = event.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    geneticButton.setVisible(true);
                    fiveGuessButton.setVisible(true);
                    aiText.setVisible(true);
                }
            }
        });

        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                createGameButtonPressed();
            }
        });

        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                backButtonPressed();
            }
        });
    }

    private void backButtonPressed() {
        ctrlPresentation.loadMenuView();
    }

    public NewGameView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initializeComponents();
    }

    private void createGameButtonPressed() {
        Diff diff;
        if (easyButton.isSelected()) diff = Diff.EASY;
        else if (mediumButton.isSelected()) diff = Diff.NORMAL;
        else diff = Diff.HARD;
        ctrlPresentation.createNewGame(diff, breakerButton.isSelected(), fiveGuessButton.isSelected());
    }
}
