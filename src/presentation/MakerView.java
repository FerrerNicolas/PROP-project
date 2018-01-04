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

import static java.lang.Integer.parseInt;

public class MakerView extends JFrame {
    private CtrlPresentation ctrlPresentation;
    private int codeSize;
    private int turns;
    private JTextField blackPinsText;
    private JTextField whitePinsText;
    private JButton increaseBlackPins;
    private JButton decreaseBlackPins;
    private JButton increaseWhitePins;
    private JButton decreaseWhitePins;
    private JPanel codePanel;
    private JPanel panel;
    private JButton sendCodeButton;

    private JPanel answerPanel;
    private JLabel answerLabel;
    private JPanel secretPanel;
    private JLabel secretCodeLabel;
    private JButton saveAndExitButton;
    private JButton exitButton;

    MakerView(CtrlPresentation ctrlPresentation, boolean isHard, boolean startedGame) {
        this.ctrlPresentation = ctrlPresentation;
        if (isHard) codeSize = 5;
        else codeSize = 4;
        initializeComponents(startedGame);
        this.setVisible(true);
    }

    private void initializeComponents(boolean startedGame) {
        turns = 1;
        blackPinsText.setText("0");
        blackPinsText.setSize(30, 30);
        whitePinsText.setSize(30, 30);
        whitePinsText.setText("0");
        codePanel.setLayout(new BoxLayout(codePanel, BoxLayout.LINE_AXIS));
        secretPanel.setLayout(new BoxLayout(secretPanel, BoxLayout.LINE_AXIS));
        try {
            Image icon = ImageIO.read(new FileInputStream("src/presentation/icons/increase_icon.png"));
            increaseBlackPins.setIcon(new ImageIcon(icon));
            increaseWhitePins.setIcon(new ImageIcon(icon));
        } catch (IOException e) {
        }
        try {
            Image icon = ImageIO.read(new FileInputStream("src/presentation/icons/decrease_icon.png"));
            decreaseBlackPins.setIcon(new ImageIcon(icon));
            decreaseWhitePins.setIcon(new ImageIcon(icon));
        } catch (IOException e) {
        }
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
        secretCodeLabel.setText("This is your secret code: ");

        Vector<Integer> secretCode = ctrlPresentation.getSecretCode();
        for (int i = 0; i < codeSize; ++i) {
            JButton codeButton = new JButton();
            colorButton(secretCode.get(i), codeButton);
            secretPanel.add(codeButton);
        }
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
        answerLabel.setText("The ai played the code");
        setSize(900, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setContentPane(panel);
        ArrayList<Object> board = ctrlPresentation.getBoard();
        Vector<Integer> lastCode = (Vector<Integer>) board.get(board.size() - 1);
        answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.LINE_AXIS));
        for (int i = 0; i < lastCode.size(); ++i) {
            JButton codeColor = new JButton();
            colorButton(lastCode.get(i), codeColor);
            answerPanel.add(codeColor);
        }
    }

    private void sendCodeButtonPressed() {
        int blackPins = parseInt(blackPinsText.getText());
        int whitePins = parseInt(whitePinsText.getText());
        sendCodeButton.setEnabled(false);
        boolean correctCorrection = true;
        Vector<Integer> nextGuess = null;
        try {
            nextGuess = ctrlPresentation.playCorrection(blackPins, whitePins);
        } catch (IncorrectCorrection incorrectCorrection) {
            JOptionPane.showMessageDialog(this, "Incorrect correction, check your correction");
            correctCorrection = false;
            sendCodeButton.setEnabled(true);
        } catch (NoGuessToBeCorrected noGuessToBeCorrected) {
        } catch (InvalidNumberOfPins invalidNumberOfPins) {
        } catch (IOException e) {
        } catch (MismatchedRole mismatchedRole) {
        } catch (ClassNotFoundException e) {
        } catch (NoUserLoggedIn noUserLoggedIn) {
        } catch (NoActiveGame noActiveGame) {
            JOptionPane.showMessageDialog(this, "The AI won");
            ctrlPresentation.endGame();
        }
        if (blackPins == 4) exitGame();
        else if (correctCorrection) {
            JOptionPane.showMessageDialog(this, "The AI played the next code");
            ++turns;
            ArrayList<Object> board = ctrlPresentation.getBoard();
            answerPanel.removeAll();
            for (int i = 0; i < nextGuess.size(); ++i) {
                JButton codeColor = new JButton();
                colorButton(nextGuess.get(i), codeColor);
                answerPanel.add(codeColor);
            }
            revalidate();
            repaint();
            sendCodeButton.setEnabled(true);
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

    private void exitGame() {
        JOptionPane.showMessageDialog(null, "The AI won!");
        setVisible(false);
        ctrlPresentation.loadMenuView();
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        blackPinsText = new JTextField();
        panel1.add(blackPinsText, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        increaseBlackPins = new JButton();
        increaseBlackPins.setText("");
        panel1.add(increaseBlackPins, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        decreaseBlackPins = new JButton();
        decreaseBlackPins.setText("");
        panel1.add(decreaseBlackPins, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Black Pins");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        codePanel = new JPanel();
        codePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(codePanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        whitePinsText = new JTextField();
        panel.add(whitePinsText, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        increaseWhitePins = new JButton();
        increaseWhitePins.setText("");
        panel.add(increaseWhitePins, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        decreaseWhitePins = new JButton();
        decreaseWhitePins.setText("");
        panel.add(decreaseWhitePins, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("White Pins");
        panel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        answerLabel = new JLabel();
        answerLabel.setText("Label");
        panel2.add(answerLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        secretCodeLabel = new JLabel();
        secretCodeLabel.setText("Label");
        panel2.add(secretCodeLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        answerPanel = new JPanel();
        answerPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(answerPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        secretPanel = new JPanel();
        secretPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(secretPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        saveAndExitButton = new JButton();
        saveAndExitButton.setText("Button");
        panel.add(saveAndExitButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exitButton = new JButton();
        exitButton.setText("Button");
        panel.add(exitButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sendCodeButton = new JButton();
        sendCodeButton.setText("Button");
        panel.add(sendCodeButton, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
