package presentation;

import domain.CtrlDomain;
import model.Diff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class NewGameView extends JFrame {
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
    private JPanel panel;

    private ButtonGroup roleGroup;
    private ButtonGroup difficultyGroup;
    private ButtonGroup aiGroup;


    private void initializeComponents(){
        playAsText.setText("Choose a side to play as this game");

        roleGroup = new ButtonGroup();
        makerButton.setText("Code Maker");
        breakerButton.setText("Code Breaker");
        roleGroup.add(makerButton);
        roleGroup.add(breakerButton);

        difficultyText.setText("Choose a difficulty for the game");
        setSize(500,500);
        difficultyGroup = new ButtonGroup();
        easyButton.setText("Easy");
        mediumButton.setText("Medium");
        hardButton.setText("Hard");

        difficultyGroup.add(easyButton);
        difficultyGroup.add(mediumButton);
        difficultyGroup.add(hardButton);

        aiText.setVisible(false);
        aiText.setText("Choose an AI to play against");

        aiGroup = new ButtonGroup();
        geneticButton.setVisible(false);
        fiveGuessButton.setVisible(false);
        aiGroup.add(geneticButton);
        aiGroup.add(fiveGuessButton);

        //default parameters are a medium game as maker with fiveguess when breakerSelected
        fiveGuessButton.setSelected(true);
        mediumButton.setSelected(true);
        breakerButton.setSelected(true);


        createButton.setText("Create Game");
        backButton.setText("Back");

        //ItemListener activates only if state changes not on every button press
        makerButton.addItemListener(new ItemListener() {
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

        breakerButton.addItemListener(new ItemListener() {
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

        setContentPane(panel);
        //The below function makes the screen appear in the center
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    private void backButtonPressed() {
        setVisible(false);
        ctrlPresentation.loadMenuView();
    }

    public NewGameView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initializeComponents();
        setVisible(true);
    }

    private void createGameButtonPressed() {
        Diff diff;
        if (easyButton.isSelected()) diff = Diff.EASY;
        else if (mediumButton.isSelected()) diff = Diff.NORMAL;
        else diff = Diff.HARD;
        setVisible(false);
        ctrlPresentation.createNewGame(diff, breakerButton.isSelected(), fiveGuessButton.isSelected());
    }
}
