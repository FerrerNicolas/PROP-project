package presentation;

import javax.swing.*;

public class MenuView extends JFrame{
    private CtrlPresentation ctrlPresentation;

    private JTextField greetingText;
    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton recordsButton;
    private JButton logOutButton;
    private JButton exitButton;
    private JPanel panel;

    private void initializeComponents() {


        setTitle("Main menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panel);
        setSize(200,400);
        greetingText.setText("Hello " + ctrlPresentation.getUser() + "!");
        greetingText.setEditable(false);
        newGameButton.setText("New Game");
        loadGameButton.setText("Load Game");
        recordsButton.setText("Show Records");
        logOutButton.setText("Log Out");
        exitButton.setText("Exit");

        newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                newGameButtonPressed();
            }
        });

        loadGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                loadGameButtonPressed();
            }
        });

        recordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                gameRecordsButtonPressed();
            }
        });

        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                logOutButtonPressed();
            }
        });

        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                System.exit(0);
            }
        });
    }

    private void newGameButtonPressed() {
        ctrlPresentation.loadNewGameView();
    }

    private void loadGameButtonPressed() {
        ctrlPresentation.loadLoadGameView();
    }

    private void gameRecordsButtonPressed() {
        //Load gameRecordsView
    }

    private void logOutButtonPressed() {
        ctrlPresentation.logOutUser();
    }

    public MenuView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initializeComponents();
        setVisible(true);
    }
}
