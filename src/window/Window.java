package window;

import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
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

    public Window() {
        super("Calculator");
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

        textField = new JTextField();
        textField.setFont(fontText);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setCaretColor(Color.white);

        memoryButtonsPanel = new JPanel(new GridLayout(6, 1, 5, 5));

        buttonM = new JButton("M");
        memoryButtonsPanel.add(buttonM);

        buttonMC = new JButton("MC");
        memoryButtonsPanel.add(buttonMC);

        buttonMR = new JButton("MR");
        memoryButtonsPanel.add(buttonMR);

        buttonMS = new JButton("MS");
        memoryButtonsPanel.add(buttonMS);

        buttonMPlus = new JButton("M+");
        memoryButtonsPanel.add(buttonMPlus);

        buttonMMinus = new JButton("M\u2212");
        memoryButtonsPanel.add(buttonMMinus);

        numberButtonsPanel = new JPanel(new GridLayout(6, 4, 5, 5));

        buttonPercent = new JButton("%");
        numberButtonsPanel.add(buttonPercent);

        buttonCE = new JButton("CE");
        numberButtonsPanel.add(buttonCE);

        buttonAC = new JButton("AC");
        numberButtonsPanel.add(buttonAC);

        buttonBackspace = new JButton("\u232b");
        numberButtonsPanel.add(buttonBackspace);

        buttonInverse = new JButton("1/x");
        numberButtonsPanel.add(buttonInverse);

        buttonSquare = new JButton("x\u00b2");
        numberButtonsPanel.add(buttonSquare);

        buttonSquareRoot = new JButton("\u221ax");
        numberButtonsPanel.add(buttonSquareRoot);

        buttonDivision = new JButton("\u00f7");
        numberButtonsPanel.add(buttonDivision);

        button7 = new JButton("7");
        numberButtonsPanel.add(button7);

        button8 = new JButton("8");
        numberButtonsPanel.add(button8);

        button9 = new JButton("9");
        numberButtonsPanel.add(button9);

        buttonMultiplication = new JButton("\u00d7");
        numberButtonsPanel.add(buttonMultiplication);

        button4 = new JButton("4");
        numberButtonsPanel.add(button4);

        button5 = new JButton("5");
        numberButtonsPanel.add(button5);

        button6 = new JButton("6");
        numberButtonsPanel.add(button6);

        buttonMinus = new JButton("\u2212");
        numberButtonsPanel.add(buttonMinus);

        button1 = new JButton("1");
        numberButtonsPanel.add(button1);

        button2 = new JButton("2");
        numberButtonsPanel.add(button2);

        button3 = new JButton("3");
        numberButtonsPanel.add(button3);

        buttonPlus = new JButton("+");
        numberButtonsPanel.add(buttonPlus);

        button0 = new JButton("0");
        numberButtonsPanel.add(button0);

        buttonDot = new JButton(".");
        numberButtonsPanel.add(buttonDot);

        buttonPlusMinus = new JButton("+/\u2212");
        numberButtonsPanel.add(buttonPlusMinus);

        buttonEqual = new JButton("=");
        numberButtonsPanel.add(buttonEqual);

        setGroupLayout();

        pack();
    }

    private void setGroupLayout() {
        GroupLayout layout = new GroupLayout(getContentPane());

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

        getContentPane().setLayout(layout);
    }

    public void run() {
        setVisible(true);
    }

}
