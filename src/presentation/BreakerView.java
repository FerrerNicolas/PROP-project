package presentation;

import exceptions.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class BreakerView extends JFrame{
    private CtrlPresentation ctrlPresentation;
    private boolean isDiffHard;
    private int codeSize;
    private int selectionColor;
    private int turn;
    private Vector<Boolean> notHintedColors;

    private JScrollPane correctionScrollPanel;
    private JPanel codePanel;
    private JPanel colorPanel;
    private JButton sendButton;
    private JPanel panel;
    private JButton saveAndExitButton;
    private JButton exitButton;
    private JLabel turnLabel;
    private JButton hintButton;


    BreakerView(CtrlPresentation ctrlPresentation, boolean isHard, boolean startedGame) {
        this.ctrlPresentation = ctrlPresentation;
        isDiffHard = isHard;
        if (isDiffHard) codeSize = 5;
        else codeSize = 4;
        initializeComponents(startedGame);
        setVisible(true);
    }

    private void initializeComponents(boolean startedGame) {
        turn = 1;
        //notHinted colors is a vector that controls the colors already given as hints
        //if true the color hasn't been given as a hint yet
        notHintedColors = new Vector<>();
        for (int i = 0; i < codeSize; ++i) notHintedColors.add(true);
        correctionScrollPanel.setLayout(new ScrollPaneLayout());
        sendButton = new JButton();
        correctionScrollPanel.createVerticalScrollBar();

        codePanel.setLayout(new BoxLayout(codePanel, BoxLayout.LINE_AXIS));
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.LINE_AXIS));
        setTitle("Breake the code!");
        for (int i = 0; i < codeSize; ++i) {
            JButton codePos = new JButton();
            codePos.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    changeColor(event);
                }
            });
            colorButton(0, codePos);
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
            ArrayList<Object> board = ctrlPresentation.getBoard();
            JPanel scrollPanel = new JPanel();
            scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.PAGE_AXIS));
            for (int i = 1; i < board.size(); i += 2) {
                Vector<Integer> code = (Vector<Integer>) board.get(i);
                codeSize = code.size();
                JPanel colorPanel = new JPanel();
                colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.LINE_AXIS));
                for (int j = 0; j < codeSize; ++j) {
                    JButton colorButton = new JButton();
                    colorButton(code.get(j), colorButton);
                    colorPanel.add(colorButton);
                }
                JPanel correctionPanel = new JPanel();
                correctionPanel.setLayout(new BoxLayout(correctionPanel, BoxLayout.LINE_AXIS));
                Vector<Integer> corrections = (Vector<Integer>) board.get(i + 1);
                int white_pins = corrections.get(0);
                int black_pins = corrections.get(1);
                int j = 0;
                while (j < black_pins) {
                    JButton correctionPin = new JButton();
                    try {
                        Image icon = ImageIO.read(new FileInputStream("src/presentation/icons/black_pin.png"));
                        correctionPin.setIcon(new ImageIcon(icon));

                    } catch (IOException e) {
                    }
                    correctionPin.setBackground(Color.BLACK);
                    correctionPin.setContentAreaFilled(false);
                    correctionPanel.add(correctionPin);
                    correctionPin.setVisible(true);
                    ++j;
                }
                while (j < white_pins + black_pins) {
                    JButton correctionPin = new JButton();
                    try {
                        Image icon = ImageIO.read(new FileInputStream("src/presentation/icons/white_pin.png"));
                        correctionPin.setIcon(new ImageIcon(icon));
                    } catch (IOException e) {
                    }
                    correctionPin.setBackground(Color.WHITE);
                    correctionPin.setContentAreaFilled(false);
                    correctionPanel.add(correctionPin);
                    correctionPin.setVisible(true);
                    ++j;
                }
                while (j < codeSize) {
                    JButton correctionPin = new JButton();
                    try {
                        Image icon = ImageIO.read(new FileInputStream("src/presentation/icons/blank_pin.png"));
                        correctionPin.setIcon(new ImageIcon(icon));
                    } catch (IOException e) {
                    }
                    correctionPin.setContentAreaFilled(false);
                    correctionPin.setBackground(Color.GRAY);
                    correctionPanel.add(correctionPin);
                    ++j;
                }
                JPanel turnPanel = new JPanel();
                turnPanel.setLayout(new BoxLayout(turnPanel, BoxLayout.LINE_AXIS));
                turnPanel.add(colorPanel);
                turnPanel.add(correctionPanel);
                scrollPanel.add(turnPanel);
                ++turn;
            }
            correctionScrollPanel.getViewport().add(scrollPanel);
        }
        turnLabel.setText("Turn: " + turn + "/12");

        sendButton.setText("Send code");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                sendButtonPressed();
            }
        });
        codePanel.add(sendButton, RIGHT_ALIGNMENT);
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
        hintButton.setText("Take a hint");
        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hintButtonPressed();
            }
        });
        setContentPane(panel);
        if (codeSize == 4) setSize(850, 700);
        else setSize(1000, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    private void exitButtonPressed() {
        setVisible(false);
        ctrlPresentation.exitGame();
    }

    private void saveAndExitButtonPressed() {
        boolean correctName = false;
        String name = "";
        while (name.isEmpty() || !correctName) {
            JOptionPane savePrompt = new JOptionPane();
            name = savePrompt.showInputDialog(null, "Set a saved game name");
            if (name.isEmpty()) JOptionPane.showMessageDialog(this, "Enter a valid name");
            else {
                try {
                    correctName = true;
                    ctrlPresentation.saveGame(name);
                } catch (ClassNotFoundException e) {
                } catch (UserSavesExistingID userSavesExistingID) {
                    correctName = false;
                    JOptionPane.showMessageDialog(this, "The saved game name already exists");
                } catch (NoActiveGame noActiveGame) {
                } catch (IOException e) {
                }
            }
        }
        this.setVisible(false);
        ctrlPresentation.loadMenuView();

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
                JOptionPane.showMessageDialog(this, "Congratulations you won the game! :)");
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
                JPanel pinPanel = new JPanel();
                pinPanel.setLayout(new BoxLayout(pinPanel, BoxLayout.X_AXIS));

                int i = 0;
                while( i < black_pins) {
                    JButton correctionPin = new JButton();
                    try {
                        Image icon = ImageIO.read(new FileInputStream("src/presentation/icons/black_pin.png"));
                        correctionPin.setIcon(new ImageIcon(icon));

                    } catch (IOException e) {
                    }
                    correctionPin.setBackground(Color.BLACK);
                    correctionPin.setContentAreaFilled(false);
                    pinPanel.add(correctionPin);
                    correctionPin.setVisible(true);
                    ++i;
                }
                while (i < white_pins + black_pins) {
                    JButton correctionPin = new JButton();
                    try {
                        Image icon = ImageIO.read(new FileInputStream("src/presentation/icons/white_pin.png"));
                        correctionPin.setIcon(new ImageIcon(icon));
                    } catch (IOException e) {
                    }
                    correctionPin.setBackground(Color.WHITE);
                    correctionPin.setContentAreaFilled(false);
                    pinPanel.add(correctionPin);
                    correctionPin.setVisible(true);
                    ++i;
                }
                while (i < numericalCode.size()) {
                    JButton correctionPin = new JButton();
                    try {
                        Image icon = ImageIO.read(new FileInputStream("src/presentation/icons/blank_pin.png"));
                        correctionPin.setIcon(new ImageIcon(icon));
                    } catch (IOException e) {
                    }
                    correctionPin.setContentAreaFilled(false);
                    correctionPin.setBackground(Color.GRAY);
                    pinPanel.add(correctionPin);
                    ++i;
                }
                JPanel correctionPanel = new JPanel();
                correctionPanel.setLayout(new BoxLayout(correctionPanel, BoxLayout.LINE_AXIS));
                correctionPanel.add(correctionCodePanel);
                pinPanel.setSize(200, 200);
                correctionPanel.add(pinPanel);
                Component[] components = correctionScrollPanel.getViewport().getComponents();
                JPanel turnScrollPanel = new JPanel();
                turnScrollPanel.setLayout(new BoxLayout(turnScrollPanel, BoxLayout.PAGE_AXIS));
                for (int j = 0; j < components.length; ++j) {
                    turnScrollPanel.add(components[j], BOTTOM_ALIGNMENT);
                }
                turnScrollPanel.add(correctionPanel);
                correctionScrollPanel.getViewport().add(turnScrollPanel, BOTTOM_ALIGNMENT);
                JScrollBar sb = correctionScrollPanel.getVerticalScrollBar();
                sb.setValue(sb.getMaximum());
            }
        }
        ++turn;
        turnLabel.setText("Turn: " + turn + "/12");
        if (turn == 12) gameEnded();
        revalidate();
        repaint();

    }

    private void colorButton(int color, JButton button) {
        switch (color) {
            case 0:
                try {
                    Image icon = ImageIO.read(new FileInputStream("src/presentation/icons/blank.png"));
                    button.setIcon(new ImageIcon(icon));
                } catch (IOException e) {
                }
                button.setBackground(Color.BLACK);
                button.setContentAreaFilled(false);
                break;
            case 1:
                try {
                    Image icon = ImageIO.read(new FileInputStream("src/presentation/icons/red_color.png"));
                    button.setIcon(new ImageIcon(icon));
                } catch (IOException e) {
                }
                button.setBackground(Color.RED);
                button.setContentAreaFilled(false);
                break;
            case 2:
                try {
                    Image icon = ImageIO.read(new FileInputStream("src/presentation/icons/blue_color.png"));
                    button.setIcon(new ImageIcon(icon));
                } catch (IOException e) {
                }
                button.setBackground(Color.BLUE);
                button.setContentAreaFilled(false);
                break;
            case 3:
                try {
                    Image icon = ImageIO.read(new FileInputStream("src/presentation/icons/green_color.png"));
                    button.setIcon(new ImageIcon(icon));
                } catch (IOException e) {
                }
                button.setBackground(Color.GREEN);
                button.setContentAreaFilled(false);
                break;
            case 4:
                try {
                    Image icon = ImageIO.read(new FileInputStream("src/presentation/icons/yellow_color.png"));
                    button.setIcon(new ImageIcon(icon));
                } catch (IOException e) {
                }
                button.setBackground(Color.YELLOW);
                button.setContentAreaFilled(false);
                break;
            case 5:
                try {
                    Image icon = ImageIO.read(new FileInputStream("src/presentation/icons/pink_color.png"));
                    button.setIcon(new ImageIcon(icon));
                } catch (IOException e) {
                }
                button.setBackground(Color.CYAN);
                button.setContentAreaFilled(false);
                break;
            case 6:
                try {
                    Image icon = ImageIO.read(new FileInputStream("src/presentation/icons/orange_color.png"));
                    button.setIcon(new ImageIcon(icon));
                } catch (IOException e) {
                }
                button.setBackground(Color.WHITE);
                button.setContentAreaFilled(false);
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

    public void gameEnded() {
        if (turn >= 12) JOptionPane.showMessageDialog(this, "You didn't get the code in the available turns :(");
        setVisible(false);
        ctrlPresentation.exitGame();
    }

    public void hintButtonPressed() {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to take a hint. \n" +
                "If you do the game score will not be counted", "Warning", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            Vector<Integer> code = null;
            try {
                code = ctrlPresentation.takeHint(notHintedColors);
            } catch (BadParameters badParameters) {
            } catch (NoUserLoggedIn noUserLoggedIn) {
            } catch (EmptyMask emptyMask) {
                JOptionPane.showMessageDialog(null, "You have already been given all the " +
                        "code colors. Press the Send Code button to finish the game.");
            } catch (MismatchedRole mismatchedRole) {
            } catch (NoActiveGame noActiveGame) {
            }
            if (code != null) {
                int codePosition = code.get(1);
                int color = code.get(0);
                JButton hintedColor = (JButton) codePanel.getComponent(codePosition);
                colorButton(color, hintedColor);
                hintedColor.removeActionListener(hintedColor.getAction());
                JOptionPane.showMessageDialog(null, "You have been given a color from the code. \n" +
                        "You can no longer interact with that color and your score will not have an effect on ranking.");
                notHintedColors.set(codePosition, false);
            }
        }

    }

}

