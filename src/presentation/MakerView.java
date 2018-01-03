package presentation;

import exceptions.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class MakerView extends JFrame{
    private CtrlPresentation ctrlPresentation;

    private JTextField blackPinsText;
    private JTextField whitePinsText;
    private JButton increaseBlackPins;
    private JButton decreaseBlackPins;
    private JButton increaseWhitePins;
    private JButton decreaseWhitePins;
    private JPanel codePanel;
    private JScrollPane previousCorrectionsPanel;
    private JPanel secretCodePanel;
    private JPanel panel;
    private JButton sendCodeButton;

    private void initializeComponents() {
        blackPinsText.setText("0");
        whitePinsText.setText("0");
        increaseBlackPins.setText("Increase"); //Change for triangular upside icon
        decreaseBlackPins.setText("Decrease");
        increaseWhitePins.setText("Increase");
        decreaseWhitePins.setText("Decrease");
        increaseWhitePins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                increaseWhitePinsPressed();
            }
        });
        decreaseWhitePins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decreaseWhitePinsPressed();
            }
        });
        increaseBlackPins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                increaseBlackPinsPressed();
            }
        });
        decreaseBlackPins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decreaseBlackPinsPressed();
            }
        });

        sendCodeButton.setText("Send");
        sendCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendCodeButtonPressed();
            }
        });
    }

    private void sendCodeButtonPressed()  {
        int blackPins = parseInt(blackPinsText.getText());
        int whitePins = parseInt(whitePinsText.getText());
        sendCodeButton.setEnabled(false);
        boolean correctCorrection = true;
        try {
            ctrlPresentation.playCorrection(blackPins, whitePins);
        } catch (IncorrectCorrection incorrectCorrection) {
            JOptionPane.showMessageDialog(this, "Incorrect correction, check your correction");
            correctCorrection = false;
        } catch (NoGuessToBeCorrected noGuessToBeCorrected) {}
        catch (InvalidNumberOfPins invalidNumberOfPins) {}
        catch (IOException e) {}
        catch (MismatchedRole mismatchedRole) {}
        catch (ClassNotFoundException e) {}
        catch (NoUserLoggedIn noUserLoggedIn) {}
        catch (NoActiveGame noActiveGame) {
            JOptionPane.showMessageDialog(this, "The AI won");
            ctrlPresentation.endGame();
        }
        if (correctCorrection) {
            //set next code
            //add previous code to scrollPanel
        }

    }

    private void increaseWhitePinsPressed() {
        int whitePins = parseInt(whitePinsText.getText());
        whitePinsText.setText(String.valueOf(++whitePins));
    }

    private void decreaseWhitePinsPressed() {
        int whitePins = parseInt(whitePinsText.getText());
        whitePinsText.setText(String.valueOf(--whitePins));
    }

    private void increaseBlackPinsPressed() {
        int blackPins = parseInt(blackPinsText.getText());
        blackPinsText.setText(String.valueOf(++blackPins));
    }

    private void decreaseBlackPinsPressed() {
        int blackPins = parseInt(blackPinsText.getText());
        blackPinsText.setText(String.valueOf(--blackPins));
    }


    MakerView(CtrlPresentation ctrlPresentation) {
        this.ctrlPresentation = ctrlPresentation;
        initializeComponents();
        this.setVisible(true);
    }
}
