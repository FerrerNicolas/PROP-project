package presentation;

import javax.swing.*;

public class LoginView extends JFrame {

    private CtrlPresentation ctrlPresentation;

    private JButton loginButton;
    private JButton newPlayerButton;
    private JTextField loginText;
    private JButton exitButton;
    private JPanel panel;
    private JLabel loginLabel;


    private void initializeComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");

        loginButton.setText("Login");
        newPlayerButton.setText("New Player");
        setContentPane(panel);
        setSize(300,200);
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                loginButtonPress(event);
            }
        });

        newPlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                newPlayerButtonPressed(event);
            }
        });

        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                exitButtonPress(event);
            }
        });

        loginLabel.setText("Enter a previously created User");

    }

    public LoginView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initializeComponents();
        setVisible(true);
    }

    public void exitButtonPress(java.awt.event.ActionEvent event) {
        System.exit(0);
    }

    public void loginButtonPress(java.awt.event.ActionEvent event) {
        String userID = loginText.getText();
        ctrlPresentation.loginUser(userID);
    }

    public void newPlayerButtonPressed(java.awt.event.ActionEvent event) {
        setVisible(false);
        ctrlPresentation.loadUserView();
    }

    public void notExistingUser() {
        setVisible(true);
        JOptionPane.showInputDialog("The username doesn't Exists, use a different username");
    }




}
