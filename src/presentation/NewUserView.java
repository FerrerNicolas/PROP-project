package presentation;

import javax.swing.*;
import java.awt.*;

public class NewUserView extends JFrame {
    private CtrlPresentation ctrlPresentation;

    private JTextField usernameText;
    private JPanel panel;
    private JButton createButton;
    private JButton backButton;
    private JLabel usernameLabel;

    private void initializeComponents()  {
        createButton.setText("Create and Login");
        backButton.setText("Back");
        usernameLabel.setText("Set the user name");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        setTitle("Create a new user");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300,100);
        //The below function makes the screen appear in the center
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2 - this.getSize().height/2);
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
        setContentPane(panel);
    }

    NewUserView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initializeComponents();
        setVisible(true);
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
