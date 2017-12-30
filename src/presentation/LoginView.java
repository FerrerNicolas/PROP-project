package presentation;

import javax.swing.*;

public class LoginView extends JFrame {

    private CtrlPresentation ctrlPresentation;

    private JButton loginButton;
    private JButton newPlayerButton;
    private JTextField loginText;
    private JButton exitButton;


    private void initializeComponents() {

        loginButton = new JButton();
        newPlayerButton = new JButton();
        loginText = new JTextField();
        exitButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");

        loginButton.setText("Login");
        newPlayerButton.setText("New Player");

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

        loginText.setText("Enter a previously created User");

    }

    public LoginView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initializeComponents();
    }

    public void exitButtonPress(java.awt.event.ActionEvent event) {
        System.exit(0);
    }

    public void loginButtonPress(java.awt.event.ActionEvent event) {
        String userID = loginText.getText();
        ctrlPresentation.loginUser(userID);
    }

    public void newPlayerButtonPressed(java.awt.event.ActionEvent event) {
        ctrlPresentation.loadUserView();
    }

    public static void notExistingUser() {
        JOptionPane.showInputDialog("The username doesn't Exists, use a different username");
    }




}
