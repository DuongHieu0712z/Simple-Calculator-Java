package window;

import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.Stack;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import calculator.Calculator;
import symbol.Symbol;

public class Window extends JFrame {

    private static final long serialVersionUID = -7150710923108249953L;

    private JMenuBar menuBar;

    private JMenu editMenu;
    private JMenuItem copyItem;
    private JMenuItem pasteItem;

    private JMenu viewMenu;
    private JCheckBoxMenuItem digitGroupingItem;
    private JCheckBoxMenuItem FEItem;

    private JMenu helpMenu;
    private JMenuItem helpItem;
    private JMenuItem aboutItem;
    private JMenuItem exitItem;

    private JTextField textField;

    private JPanel memoryButtonsPanel;

    private JButton buttonM;
    private JButton buttonMC;
    private JButton buttonMR;
    private JButton buttonMS;
    private JButton buttonMPlus;
    private JButton buttonMMinus;

    private JPanel numberButtonsPanel;

    private JButton button0;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;

    private JButton buttonDot;
    private JButton buttonPlusMinus;

    private JButton buttonPlus;
    private JButton buttonMinus;
    private JButton buttonMultiplication;
    private JButton buttonDivision;
    private JButton buttonEqual;

    private JButton buttonPercent;
    private JButton buttonInverse;
    private JButton buttonSquare;
    private JButton buttonSquareRoot;

    private JButton buttonBackspace;
    private JButton buttonCE;
    private JButton buttonAC;

    private final static Font fontMenu = new Font("Arial", Font.PLAIN, 12);
    private final static Font fontText = new Font("Monospace", Font.PLAIN, 20);
    private final static Font fontButton = new Font("Monospace", Font.PLAIN, 12);

    private Calculator calculator = new Calculator();
    private Stack<Double> memoryStore = new Stack<>();
    private boolean hasDigitGrouping = false;
    private boolean hasUnaryOperator = false;
    private boolean hasBinaryOperator = false;
    private boolean isEqualed = false;

    public Window() {
        super("Calculator");
        ImageIcon icon = new ImageIcon("src/image/calculator.png");
        setIconImage(icon.getImage());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        UIManager.put("Menu.font", fontMenu);
        UIManager.put("MenuItem.font", fontMenu);
        UIManager.put("CheckBoxMenuItem.font", fontMenu);
        UIManager.put("Button.font", fontButton);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        editMenu = new JMenu("Edit");
        editMenu.setMnemonic('E');
        menuBar.add(editMenu);

        copyItem = new JMenuItem("Copy");
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        editMenu.add(copyItem);

        pasteItem = new JMenuItem("Paste");
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        editMenu.add(pasteItem);

        viewMenu = new JMenu("View");
        viewMenu.setMnemonic('V');
        menuBar.add(viewMenu);

        digitGroupingItem = new JCheckBoxMenuItem("Digit grouping");
        digitGroupingItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, InputEvent.ALT_DOWN_MASK));
        digitGroupingItem.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                hasDigitGrouping = digitGroupingItem.isSelected();
                digitGrouping();
            }
        });
        viewMenu.add(digitGroupingItem);

        FEItem = new JCheckBoxMenuItem("F-E");
        FEItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, InputEvent.ALT_DOWN_MASK));
        viewMenu.add(FEItem);

        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');
        menuBar.add(helpMenu);

        helpItem = new JMenuItem("Help");
        helpMenu.add(helpItem);

        aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);

        helpMenu.addSeparator();

        exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        helpMenu.add(exitItem);

        textField = new JTextField("0");
        textField.setFont(fontText);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBackground(Color.white);
        textField.setEditable(false);

        memoryButtonsPanel = new JPanel(new GridLayout(6, 1, 5, 5));

        buttonM = new JButton("M");
        buttonM.setEnabled(false);
        memoryButtonsPanel.add(buttonM);

        buttonMC = new JButton("MC");
        buttonMC.setEnabled(false);
        buttonMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonM.setEnabled(false);
                buttonMC.setEnabled(false);
                buttonMR.setEnabled(false);
                memoryStore.clear();
            }
        });
        memoryButtonsPanel.add(buttonMC);

        buttonMR = new JButton("MR");
        buttonMR.setEnabled(false);
        memoryButtonsPanel.add(buttonMR);

        buttonMS = new JButton("MS");
        memoryButtonsPanel.add(buttonMS);

        buttonMPlus = new JButton("M" + Symbol.PLUS);
        memoryButtonsPanel.add(buttonMPlus);

        buttonMMinus = new JButton("M" + Symbol.MINUS);
        memoryButtonsPanel.add(buttonMMinus);

        numberButtonsPanel = new JPanel(new GridLayout(6, 4, 5, 5));

        buttonPercent = new JButton(Symbol.PERCENT);
        buttonPercent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOperator(Symbol.PERCENT);
            }
        });
        numberButtonsPanel.add(buttonPercent);

        buttonCE = new JButton("CE");
        buttonCE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("0");
            }
        });
        numberButtonsPanel.add(buttonCE);

        buttonAC = new JButton("AC");
        buttonAC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("0");
                calculator.clear();

                hasUnaryOperator = false;
                hasBinaryOperator = false;
                isEqualed = false;
            }
        });
        numberButtonsPanel.add(buttonAC);

        buttonBackspace = new JButton(Symbol.BACKSPACE);
        buttonBackspace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backspace();
            }
        });
        numberButtonsPanel.add(buttonBackspace);

        buttonInverse = new JButton("1/x");
        buttonInverse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOperator(Symbol.INVERSE);
            }
        });
        numberButtonsPanel.add(buttonInverse);

        buttonSquare = new JButton("x" + Symbol.SQUARE);
        buttonSquare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOperator(Symbol.SQUARE);
            }
        });
        numberButtonsPanel.add(buttonSquare);

        buttonSquareRoot = new JButton(Symbol.SQUARE_ROOT + "x");
        buttonSquareRoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOperator(Symbol.SQUARE_ROOT);
            }
        });
        numberButtonsPanel.add(buttonSquareRoot);

        buttonDivision = new JButton(Symbol.DIVISION);
        buttonDivision.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOperator(Symbol.DIVISION);
            }
        });
        numberButtonsPanel.add(buttonDivision);

        button7 = new JButton("7");
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNumber(7);
            }
        });
        numberButtonsPanel.add(button7);

        button8 = new JButton("8");
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNumber(8);
            }
        });
        numberButtonsPanel.add(button8);

        button9 = new JButton("9");
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNumber(9);
            }
        });
        numberButtonsPanel.add(button9);

        buttonMultiplication = new JButton(Symbol.MULTIPLICATION);
        buttonMultiplication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOperator(Symbol.MULTIPLICATION);
            }
        });
        numberButtonsPanel.add(buttonMultiplication);

        button4 = new JButton("4");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNumber(4);
            }
        });
        numberButtonsPanel.add(button4);

        button5 = new JButton("5");
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNumber(5);
            }
        });
        numberButtonsPanel.add(button5);

        button6 = new JButton("6");
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNumber(6);
            }
        });
        numberButtonsPanel.add(button6);

        buttonMinus = new JButton(Symbol.MINUS);
        buttonMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOperator(Symbol.MINUS);
            }
        });
        numberButtonsPanel.add(buttonMinus);

        button1 = new JButton("1");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNumber(1);
            }
        });
        numberButtonsPanel.add(button1);

        button2 = new JButton("2");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNumber(2);
            }
        });
        numberButtonsPanel.add(button2);

        button3 = new JButton("3");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNumber(3);
            }
        });
        numberButtonsPanel.add(button3);

        buttonPlus = new JButton(Symbol.PLUS);
        buttonPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOperator(Symbol.PLUS);
            }
        });
        numberButtonsPanel.add(buttonPlus);

        button0 = new JButton("0");
        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNumber(0);
            }
        });
        numberButtonsPanel.add(button0);

        buttonDot = new JButton(".");
        buttonDot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDot();
            }
        });
        numberButtonsPanel.add(buttonDot);

        buttonPlusMinus = new JButton(Symbol.PLUS_MINUS);
        buttonPlusMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                char firstChar = text.charAt(0);
                char minusSign = Symbol.MINUS.charAt(0);

                if (firstChar == minusSign) {
                    text = text.substring(1);
                } else {
                    if (firstChar == '0') {
                        if (text.contains(".")) {
                            text = minusSign + text;
                        }
                    } else {
                        text = minusSign + text;
                    }
                }

                textField.setText(text);
            }
        });
        numberButtonsPanel.add(buttonPlusMinus);

        buttonEqual = new JButton(Symbol.EQUAL);
        // buttonEqual.set
        buttonEqual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equal();
            }
        });
        numberButtonsPanel.add(buttonEqual);

        setGroupLayout();

        pack();
    }

    private void digitGrouping() {
        String text = textField.getText();
        text = text.replaceAll(",", "");

        if (hasDigitGrouping) {
            StringBuilder string = new StringBuilder(text);
            int index = string.indexOf(".");

            if (index == -1) {
                index = string.length() - 3;
            } else {
                index -= 3;
            }

            char minusSign = Symbol.MINUS.charAt(0);
            while (index > 0 && string.charAt(index - 1) != minusSign) {
                string.insert(index, ',');
                index -= 3;
            }

            text = string.toString();
        }

        textField.setText(text);
    }

    private void addNumber(int num) {
        String text = textField.getText();

        if (hasUnaryOperator || hasBinaryOperator || isEqualed) {
            text = Integer.toString(num);

            hasUnaryOperator = false;
            hasBinaryOperator = false;
            isEqualed = false;
        } else {
            char firstChar = text.charAt(0);
            if (firstChar == '0' && !text.contains(".")) {
                text = Integer.toString(num);
            } else {
                text += num;
            }
        }

        textField.setText(text);
        digitGrouping();
    }

    private void addOperator(String operator) {
        String text = textField.getText();

        if (Symbol.UNARY_OPERATOR.contains(operator)) {
            String tempOperator = calculator.getOperator();
            calculator.setOperator(operator);

            if (Double.isNaN(calculator.getFirstNumber())) {
                calculator.setFirstNumber(text);
                calculator.calculate();
                text = calculator.getFirstNumberString();
                calculator.clearFirstNumber();
            } else {
                calculator.setSecondNumber(text);
                calculator.calculate();
                text = calculator.getSecondNumberString();
                calculator.clearSecondNumber();
            }

            calculator.setOperator(tempOperator);
            hasUnaryOperator = true;
        } else {
            if (Double.isNaN(calculator.getFirstNumber())) {
                calculator.setFirstNumber(text);
            } else {
                calculator.setSecondNumber(text);
                calculator.calculate();
                calculator.clearSecondNumber();
            }

            text = calculator.getFirstNumberString();
            calculator.setOperator(operator);
            hasBinaryOperator = true;
        }

        textField.setText(text);
        digitGrouping();
    }

    private void addDot() {
        String text = textField.getText();

        if (hasUnaryOperator || hasBinaryOperator || isEqualed) {
            text = "0.";

            hasUnaryOperator = false;
            hasBinaryOperator = false;
            isEqualed = false;
        } else {
            if (!text.contains(".")) {
                text += ".";
            }
        }

        textField.setText(text);
    }

    private void backspace() {
        String text = textField.getText();
        text = text.substring(0, text.length() - 1);

        if (text.isEmpty()) {
            text = "0";
        } else if (text.contains(Symbol.MINUS)) {
            if (text.compareTo(Symbol.MINUS) == 0) {
                text = "0";
            } else if (text.charAt(1) == '0' && !text.contains(".")) {
                text = "0";
            }
        }
        calculator.clearOperator();

        textField.setText(text);
        digitGrouping();
    }

    private void equal() {
        String text = textField.getText();

        if (!hasBinaryOperator) {
            if (Double.isNaN(calculator.getFirstNumber())) {
                calculator.setFirstNumber(text);
            } else {
                calculator.setSecondNumber(text);
                calculator.calculate();
            }
            text = calculator.getFirstNumberString();
        }

        calculator.clear();
        isEqualed = true;
        textField.setText(text);
        digitGrouping();
    }

    private void setGroupLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup().addComponent(textField).addGroup(
                layout.createSequentialGroup().addComponent(memoryButtonsPanel).addComponent(numberButtonsPanel)));

        layout.setVerticalGroup(layout.createSequentialGroup().addComponent(textField).addGroup(
                layout.createParallelGroup().addComponent(memoryButtonsPanel).addComponent(numberButtonsPanel)));

        getContentPane().setLayout(layout);
    }

    public void run() {
        setVisible(true);
    }

}
