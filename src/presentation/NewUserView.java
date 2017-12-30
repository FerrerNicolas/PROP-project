package presentation;

import javax.swing.*;

public class NewUserView extends JFrame {
    private CtrlPresentation ctrlPresentation;

    private JTextField usernameText;
    private JButton createButton;
    private JButton backButton;

    public void initializeComponents()  {
        usernameText = new JTextField();
        createButton = new JButton();
        backButton = new JButton();

        createButton.setText("Create and Login");
        backButton.setText("Back");
        usernameText.setText("Set the user name");

        setTitle("Create a new user");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        backButton.setText("Back");
        createButton.setText("Create user and login");

        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                createButtonPressed(event);
            }
        });

        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                backButtonPressed(event);
            }
        });

    }

    public NewUserView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initializeComponents();
    }

    private void createButtonPressed(java.awt.event.ActionEvent event) {
        String username = usernameText.getText();
        ctrlPresentation.createUser(username);

    }

    private void backButtonPressed(java.awt.event.ActionEvent event) {
        ctrlPresentation.loadLoginView();
    }

    public void userAlredyExists() {
        JOptionPane.showInputDialog("The user alredy exists.\n " +
                "If you want to log in with the previous user write your username in the previous window.");
    }
}
