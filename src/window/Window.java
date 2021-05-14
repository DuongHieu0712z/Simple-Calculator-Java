package window;

import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
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
import javax.swing.plaf.ColorUIResource;

import calculator.Calculator;
import symbol.Symbol;

public class Window {
    
    private JFrame frame;

    private JMenuBar menuBar;

    private JMenu editMenu;
    private JMenuItem copyItem;
    private JMenuItem pasteItem;

    private JMenu viewMenu;
    private JCheckBoxMenuItem digitGroupingItem;
    private JCheckBoxMenuItem fixedToExpItem;

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

    private JButton buttonPercent;
    private JButton buttonInverse;
    private JButton buttonSquare;
    private JButton buttonSquareRoot;

    private JButton buttonPlus;
    private JButton buttonMinus;
    private JButton buttonMultiplication;
    private JButton buttonDivision;

    private JButton buttonDot;
    private JButton buttonPlusMinus;
    private JButton buttonEqual;

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
        initialize();
    }

    private void initialize() {
        setUIManager();
        initFrame();
    }

    private void setUIManager() {
        UIManager.put("Menu.font", fontMenu);
        UIManager.put("MenuItem.font", fontMenu);
        UIManager.put("CheckBoxMenuItem.font", fontMenu);
        UIManager.put("Button.font", fontButton);
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        // UIManager.put("Button.margin", new Insets(5, 5, 5, 5));
    }

    private void initFrame() {
        frame = new JFrame("Calculator");
        ImageIcon icon = new ImageIcon("src/image/calculator.png");
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initMenuBar();
        initContentPane();

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    private void initMenuBar() {
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        initEditMenu();
        initViewMenu();
        initHelpMenu();
    }

    private void initEditMenu() {
        editMenu = new JMenu("Edit");
        editMenu.setMnemonic('E');
        menuBar.add(editMenu);

        initCopyItem();
        initPasteItem();
    }

    private void initCopyItem() {
        copyItem = new JMenuItem("Copy");
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        editMenu.add(copyItem);
    }

    private void initPasteItem() {
        pasteItem = new JMenuItem("Paste");
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        editMenu.add(pasteItem);
    }

    private void initViewMenu() {
        viewMenu = new JMenu("View");
        viewMenu.setMnemonic('V');
        menuBar.add(viewMenu);

        initDigitGroupingItem();
        initFixedToExpItem();
    }

    private void initDigitGroupingItem() {
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
    }

    private void initFixedToExpItem() {
        fixedToExpItem = new JCheckBoxMenuItem("F-E");
        fixedToExpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, InputEvent.ALT_DOWN_MASK));
        viewMenu.add(fixedToExpItem);
    }

    private void initHelpMenu() {
        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');
        menuBar.add(helpMenu);
        
        initHelpItem();
        initAboutItem();
        helpMenu.addSeparator();
        initExitItem();
    }

    private void initHelpItem() {
        helpItem = new JMenuItem("Help");
        helpMenu.add(helpItem);
    }

    private void initAboutItem() {
        aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
    }

    private void initExitItem() {
        exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        helpMenu.add(exitItem);
    }

    private void initContentPane() {
        initTextField();
        initMemoryButtonsPanel();
        initNumberButtonsPanel();

        setGroupLayout();
    }

    private void initTextField() {
        textField = new JTextField("0");
        textField.setFont(fontText);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBackground(Color.white);
        textField.setEditable(false);
    }

    private void initMemoryButtonsPanel() {
        memoryButtonsPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        
        initButtonM();
        initButtonMC();
        initButtonMR();
        initButtonMS();
        initButtonMPlus();
        initButtonMMinus();
    }

    private void initButtonM() {
        buttonM = new JButton("M");
        buttonM.setForeground(Color.RED);
        buttonM.setEnabled(false);
        buttonM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        memoryButtonsPanel.add(buttonM);
    }

    private void initButtonMC() {
        buttonMC = new JButton("MC");
        buttonMC.setForeground(Color.RED);
        buttonMC.setEnabled(false);
        buttonMC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEnableMemoryButtons(false);
                memoryStore.clear();
            }
        });
        memoryButtonsPanel.add(buttonMC);
    }

    private void initButtonMR() {
        buttonMR = new JButton("MR");
        buttonMR.setForeground(Color.RED);
        buttonMR.setEnabled(false);
        buttonMR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double number = memoryStore.peek();
                calculator.setFirstNumber(number);
                String text = calculator.getFirstNumberString();
                textField.setText(text);
            }
        });
        memoryButtonsPanel.add(buttonMR);
    }

    private void initButtonMS() {
        buttonMS = new JButton("MS");
        buttonMS.setForeground(Color.RED);
        buttonMS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double tempNumber = calculator.getFirstNumber();
                String text = textField.getText();

                calculator.setFirstNumber(text);
                memoryStore.push(calculator.getFirstNumber());
                calculator.setFirstNumber(tempNumber);

                isEqualed = true;
                setEnableMemoryButtons(true);
            }
        });
        memoryButtonsPanel.add(buttonMS);
    }

    private void initButtonMPlus() {
        buttonMPlus = new JButton("M" + Symbol.PLUS);
        buttonMPlus.setForeground(Color.RED);
        buttonMPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (memoryStore.isEmpty()) {
                    memoryStore.push(Double.valueOf(0));
                }

                Double tempNumber = calculator.getFirstNumber();
                String text = textField.getText();
                Double top = memoryStore.peek();

                calculator.setFirstNumber(text);
                memoryStore.push(top + calculator.getFirstNumber());
                calculator.setFirstNumber(tempNumber);

                isEqualed = true;
                setEnableMemoryButtons(true);
            }
        });
        memoryButtonsPanel.add(buttonMPlus);
    }

    private void initButtonMMinus() {
        buttonMMinus = new JButton("M" + Symbol.MINUS);
        buttonMMinus.setForeground(Color.RED);
        buttonMMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (memoryStore.isEmpty()) {
                    memoryStore.push(Double.valueOf(0));
                }

                Double tempNumber = calculator.getFirstNumber();
                String text = textField.getText();
                Double top = memoryStore.peek();

                calculator.setFirstNumber(text);
                memoryStore.push(top - calculator.getFirstNumber());
                calculator.setFirstNumber(tempNumber);

                isEqualed = true;
                setEnableMemoryButtons(true);
            }
        });
        memoryButtonsPanel.add(buttonMMinus);
    }

    private void initNumberButtonsPanel() {
        numberButtonsPanel = new JPanel(new GridLayout(6, 4, 5, 5));

        buttonPercent = createButtonUnaryOperator(Symbol.PERCENT, Symbol.PERCENT);
        initButtonCE();
        initButtonAC();
        initButtonBackspace();

        buttonInverse = createButtonUnaryOperator("1/x", Symbol.INVERSE);
        buttonSquare = createButtonUnaryOperator("x" + Symbol.SQUARE, Symbol.SQUARE);
        buttonSquareRoot = createButtonUnaryOperator(Symbol.SQUARE_ROOT + "x", Symbol.SQUARE_ROOT);
        buttonDivision = createButtonBinaryOperator(Symbol.DIVISION);

        button7 = createButtonNumber(7);
        button8 = createButtonNumber(8);
        button9 = createButtonNumber(9);
        buttonMultiplication = createButtonBinaryOperator(Symbol.MULTIPLICATION);

        button4 = createButtonNumber(4);
        button5 = createButtonNumber(5);
        button6 = createButtonNumber(6);
        buttonMinus = createButtonBinaryOperator(Symbol.MINUS);

        button1 = createButtonNumber(1);
        button2 = createButtonNumber(2);
        button3 = createButtonNumber(3);
        buttonPlus = createButtonBinaryOperator(Symbol.PLUS);

        button0 = createButtonNumber(0);
        initButtonDot();
        initButtonPlusMinus();
        initButtonEqual();
    }

    private JButton createButtonNumber(int number) {
        JButton button = new JButton(String.valueOf(number));
        button.setForeground(Color.BLUE);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNumber(number);
            }
        });
        numberButtonsPanel.add(button);
        return button;
    }

    private JButton createButtonUnaryOperator(String name, String symbol) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUnaryOperator(symbol);
            }
        });
        numberButtonsPanel.add(button);
        return button;
    }

    private JButton createButtonBinaryOperator(String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBinaryOperator(name);
            }
        });
        numberButtonsPanel.add(button);
        return button;
    }

    private void initButtonCE() {
        buttonCE = new JButton("CE");
        buttonCE.setForeground(Color.RED);
        buttonCE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("0");
            }
        });
        numberButtonsPanel.add(buttonCE);
    }

    private void initButtonAC() {
        buttonAC = new JButton("AC");
        buttonAC.setForeground(Color.RED);
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
    }

    private void initButtonBackspace() {
        buttonBackspace = new JButton(Symbol.BACKSPACE);
        buttonBackspace.setForeground(Color.RED);
        buttonBackspace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backspace();
            }
        });
        numberButtonsPanel.add(buttonBackspace);
    }

    private void initButtonDot() {
        buttonDot = new JButton(".");
        buttonDot.setForeground(Color.BLUE);
        buttonDot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDot();
            }
        });
        numberButtonsPanel.add(buttonDot);
    }

    private void initButtonPlusMinus() {
        buttonPlusMinus = new JButton(Symbol.PLUS_MINUS);
        buttonPlusMinus.setForeground(Color.BLUE);
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
    }

    private void initButtonEqual() {
        buttonEqual = new JButton(Symbol.EQUAL);
        buttonEqual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equal();
            }
        });
        numberButtonsPanel.add(buttonEqual);
    }

    private void setGroupLayout() {
        GroupLayout layout = new GroupLayout(frame.getContentPane());

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup()
            .addComponent(textField)
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(memoryButtonsPanel)
                .addComponent(numberButtonsPanel)
            )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(textField)
            .addGroup(
                layout.createParallelGroup()
                .addComponent(memoryButtonsPanel)
                .addComponent(numberButtonsPanel)
            )
        );

        frame.getContentPane().setLayout(layout);
    }

    private void setEnableMemoryButtons(boolean b) {
        buttonM.setEnabled(b);
        buttonMC.setEnabled(b);
        buttonMR.setEnabled(b);
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

    private void addUnaryOperator(String operator) {
        String text = textField.getText();

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

        textField.setText(text);
        digitGrouping();
    }

    private void addBinaryOperator(String operator) {
        String text = textField.getText();
        
        if (!hasBinaryOperator) {
            if (Double.isNaN(calculator.getFirstNumber())) {
                calculator.setFirstNumber(text);
            } else {
                calculator.setSecondNumber(text);
                calculator.calculate();
                calculator.clearSecondNumber();
            }
        }

        text = calculator.getFirstNumberString();
        calculator.setOperator(operator);
        hasBinaryOperator = true;
    
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

        if (!hasUnaryOperator && !hasBinaryOperator && !isEqualed) {
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
        }

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

    public void run() {
        frame.setVisible(true);
    }

}
