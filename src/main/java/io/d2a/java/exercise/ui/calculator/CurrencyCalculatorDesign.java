package io.d2a.java.exercise.ui.calculator;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class CurrencyCalculatorDesign extends JFrame {

    protected final JTextField inputTextField = new JTextField();
    protected final JButton eurUsd = new JButton("EUR -> USD");
    protected final JButton usdEur = new JButton("USD -> EUR");
    protected final JButton cancel = new JButton("Cancel");

    public CurrencyCalculatorDesign() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(15, 5));

        this.inputTextField.setText("Please enter amount to convert!");
        this.add(this.inputTextField, BorderLayout.NORTH);

        this.add(eurUsd, BorderLayout.WEST);
        this.add(usdEur, BorderLayout.CENTER);
        this.add(cancel, BorderLayout.EAST);

        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new CurrencyCalculatorLogicV2();
    }

}
