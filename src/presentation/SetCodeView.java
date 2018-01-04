package presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

public class SetCodeView extends JFrame {
    CtrlPresentation ctrlPresentation;
    private boolean isHard;
    private int codeSize;
    private int selectionColor;
    private JPanel colorPanel;
    private JPanel codePanel;
    private JPanel panel;

    private void initializeComponents() {
        codePanel.setLayout(new BoxLayout(codePanel, BoxLayout.LINE_AXIS));
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.LINE_AXIS));
        setTitle("Create a Secret code");
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
        if (isHard) {
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
        JButton confirmButton = new JButton();
        confirmButton.setText("Confirm code");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmButtonPressed();
            }
        });
        codePanel.add(confirmButton, RIGHT_ALIGNMENT);
        setContentPane(panel);
        setSize(1000, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
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

    private void confirmButtonPressed() {
        Vector<Integer> numericalCode = new Vector<>();
        Component[] code = codePanel.getComponents();
        boolean validCode = true;
        for (int i = 0; i < code.length - 1; ++i) {
            Color c = code[i].getBackground();
            int codePos;
            if (c.equals(Color.BLACK) && isHard) numericalCode.add(0);
            else if (c.equals(Color.RED)) {
                numericalCode.add(1);
            } else if (c.equals(Color.BLUE)) numericalCode.add(2);
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
            setVisible(false);
            ctrlPresentation.setSecretCode(numericalCode);
        }
    }


    SetCodeView(CtrlPresentation ctrlPresentation, boolean isHard) {
        this.ctrlPresentation = ctrlPresentation;
        this.isHard = isHard;
        if (isHard) codeSize = 5;
        else codeSize = 4;
        initializeComponents();
        setVisible(true);
    }
    public void wrongCode() {
        JOptionPane.showInputDialog("The code submitted is wrong, play a different code");
    }
}
