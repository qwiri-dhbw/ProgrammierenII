package io.d2a.java.exercise.ui.calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("Convert2Lambda")
public class CurrencyCalculatorLogic extends CurrencyCalculatorDesign {

    public CurrencyCalculatorLogic() {
        // EUR -> USD:
        // amount * 1.09
        this.eurUsd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String text = CurrencyCalculatorLogic.this.inputTextField.getText();
                try {
                    final double amount = Double.parseDouble(text) * 1.09;
                    CurrencyCalculatorLogic.this.inputTextField.setText(String.valueOf(amount));
                } catch (final NumberFormatException nfex) {
                    CurrencyCalculatorLogic.this.inputTextField.setText("Invalid Number entered");
                }
            }
        });

        // USD -> EUR
        // amount / 1.09
        this.usdEur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String text = CurrencyCalculatorLogic.this.inputTextField.getText();
                try {
                    final double amount = Double.parseDouble(text) * 1.09;
                    CurrencyCalculatorLogic.this.inputTextField.setText(String.valueOf(amount));
                } catch (final NumberFormatException nfex) {
                    CurrencyCalculatorLogic.this.inputTextField.setText("Invalid Number entered");
                }
            }
        });

        // Cancel
        // Close Window
        this.cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                CurrencyCalculatorLogic.this.dispose();
                // or: System.exit(0);
            }
        });
    }

}
