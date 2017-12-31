package presentation;

import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

public class BreakerView extends JFrame{
    private CtrlPresentation ctrlPresentation;
    private boolean isDiffHard;
    private int codeSize;

    private JScrollPane correctionScrollPanel;
    private JPanel codePanel;
    private JPanel colorPanel;
    private JButton sendButton;
    private JPanel panel;
    private JButton saveAndExitButton;
    private JButton exitButton;
    private int selectionColor;


    private void initializeComponents(boolean startedGame) {
        correctionScrollPanel.setLayout(new ScrollPaneLayout());
        sendButton = new JButton();
        correctionScrollPanel.createVerticalScrollBar();

        codePanel.setLayout(new BoxLayout(codePanel, BoxLayout.LINE_AXIS));
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.LINE_AXIS));
        setTitle("Mastermind!");
        for (int i = 0; i < codeSize; ++i) {
            JButton codePos = new JButton();
            codePos.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    changeColor(event);
                }
            });
            codePanel.add(codePos);
        }
        if (isDiffHard) {
            for (int i = 0; i < 7; i++) {
                JButton color = new JButton();
                color.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        changeSelectedColor(event);
                    }
                });
                colorButton(i, color);
                colorPanel.add(color);
            }
        }
        else {
            for (int i = 1; i < 7; i++) {
                JButton color = new JButton();
                color.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        changeSelectedColor(event);
                    }
                });
                colorButton(i, color);
                colorPanel.add(color);
            }
        }
        if (startedGame) {
            ctrlPresentation.getBoard();
            //How to get board???
        }

        sendButton.setText("Send code");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                sendButtonPressed();
            }
        });
        codePanel.add(sendButton);
        exitButton.setText("Exit");
        saveAndExitButton.setText("Save and exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButtonPressed();
            }
        });

        saveAndExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAndExitButtonPressed();
            }
        });

        setContentPane(panel);
        setSize(300,250);
    }


    private void exitButtonPressed() {
        ctrlPresentation.exitGame();
    }

    private void saveAndExitButtonPressed() {
        JOptionPane savePrompt = new JOptionPane();
        String name = savePrompt.showInputDialog(null, "Set a saved game name");
        if (name != null) ctrlPresentation.saveGame(name);
    }


    private void sendButtonPressed() {
        Vector<Integer> numericalCode = new Vector<>();
        Component[] code = codePanel.getComponents();
        boolean validCode = true;
        for (int i = 0; i < code.length-1; ++i) {
            Color c = code[i].getBackground();
            int codePos;
            if (c.equals(Color.BLACK) && isDiffHard) numericalCode.add(0);
            else if (c.equals(Color.RED)) {
                numericalCode.add(1);
            }
            else if (c.equals(Color.BLUE)) numericalCode.add(2);
            else if (c.equals(Color.GREEN)) numericalCode.add(3);
            else if (c.equals(Color.YELLOW)) numericalCode.add(4);
            else if (c.equals(Color.CYAN)) numericalCode.add(5);
            else if (c.equals(Color.WHITE)) numericalCode.add(6);
            else {
                validCode = false;
                wrongCode();
            }
        }
        if (validCode) {
            Vector<Integer> correction = null;
            try {
                correction = ctrlPresentation.correctCode(numericalCode);
            } catch (GameIsFinished gameIsFinished) {
            }
            catch (IOException e) {}
            catch (CodeIsInvalid codeIsInvalid) {}
            catch (MismatchedRole mismatchedRole) {}
            catch (ClassNotFoundException e) {}
            catch (BadlyFormedCode badlyFormedCode) {}
            catch (NoUserLoggedIn noUserLoggedIn) {}
            catch (NoActiveGame noActiveGame) {}

            int black_pins = correction.get(1);
            int white_pins = correction.get(0);

            if (black_pins == codeSize) {
                JOptionPane.showInputDialog("Congratulations you won the game!");
                ctrlPresentation.loadMenuView();
            }
            else {
                JPanel correctionCodePanel = new JPanel();
                correctionCodePanel.setLayout(new BoxLayout(correctionCodePanel, BoxLayout.LINE_AXIS));
                for (int i = 0; i < numericalCode.size(); ++i) {
                    JButton color = new JButton();
                    int c = numericalCode.get(i);
                    colorButton(c, color);
                    correctionCodePanel.add(color);
                }
                JPanel correctionCorrPanel = new JPanel();
                correctionCorrPanel.setLayout(new BoxLayout(correctionCorrPanel, BoxLayout.X_AXIS));
                int i = 0;
                while( i < black_pins) {
                    JButton correctionPin = new JButton();
                    correctionPin.setBackground(Color.BLACK);
                    correctionCorrPanel.add(correctionPin);
                    correctionPin.setVisible(true);
                    ++i;
                }
                while (i < white_pins) {
                    JButton correctionPin = new JButton();
                    correctionPin.setBackground(Color.WHITE);
                    correctionCorrPanel.add(correctionPin);
                    correctionPin.setVisible(true);
                    ++i;
                }
                while (i < numericalCode.size()) {
                    JButton correctionPin = new JButton();
                    correctionPin.setBackground(Color.GRAY);
                    correctionCorrPanel.add(correctionPin);
                    ++i;
                }
                JPanel correctionPanel = new JPanel();
                correctionPanel.setLayout(new BoxLayout(correctionPanel, BoxLayout.LINE_AXIS));
                correctionPanel.add(correctionCodePanel);
                correctionPanel.add(correctionCorrPanel);
                correctionScrollPanel.getViewport().add(correctionPanel);
            }
        }
        correctionScrollPanel.setLayout(new ScrollPaneLayout());
        revalidate();
        repaint();

    }

    BreakerView(CtrlPresentation ctrlPresentation, boolean isHard, boolean startedGame) {
        this.ctrlPresentation = ctrlPresentation;
        isDiffHard = isHard;
        if (isDiffHard) codeSize = 5;
        else codeSize = 4;
        initializeComponents(startedGame);
        setVisible(true);
    }

    private void colorButton(int color, JComponent button) {
        switch (color) {
            case 0:
                button.setBackground(Color.BLACK);
                break;
            case 1:
                button.setBackground(Color.RED);
                break;
            case 2:
                button.setBackground(Color.BLUE);
                break;
            case 3:
                button.setBackground(Color.GREEN);
                break;
            case 4:
                button.setBackground(Color.YELLOW);
                break;
            case 5:
                button.setBackground(Color.CYAN);
                break;
            case 6:
                button.setBackground(Color.WHITE);
                break;
            default:
        }

    }


    private void changeSelectedColor(ActionEvent event) {
        JButton pressed = (JButton)event.getSource();
        Component[] buttons = colorPanel.getComponents();
        Color c = null;
        for (int i = 0; i < buttons.length; ++i) {
            if (pressed.equals(buttons[i])) c = buttons[i].getBackground();
        }
        if (c.equals(Color.BLACK)) selectionColor = 0;
        else if (c.equals(Color.RED)) selectionColor =1;
        else if (c.equals(Color.BLUE)) selectionColor = 2;
        else if (c.equals(Color.GREEN)) selectionColor = 3;
        else if (c.equals(Color.YELLOW)) selectionColor = 4;
        else if (c.equals(Color.CYAN)) selectionColor = 5;
        else if (c.equals(Color.WHITE)) selectionColor = 6;
    }

    private void changeColor(ActionEvent event) {
        JButton codePos = (JButton) event.getSource();
        colorButton(selectionColor, codePos);
    }

    public void wrongCode() {
        JOptionPane.showMessageDialog(this, "The code submitted is wrong, play a different code");
    }


}

