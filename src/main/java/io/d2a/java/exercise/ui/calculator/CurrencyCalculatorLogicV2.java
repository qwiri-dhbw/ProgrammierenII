package io.d2a.java.exercise.ui.calculator;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Function;

public class CurrencyCalculatorLogicV2 extends CurrencyCalculatorDesign {

    public CurrencyCalculatorLogicV2() {
        // EUR -> USD
        this.eurUsd.addActionListener(createConvertAction(d -> d * 1.09));

        // USD -> EUR
        this.usdEur.addActionListener(createConvertAction(d -> d / 1.09));

        // Cancel -> Close
        this.cancel.addActionListener(e -> this.dispose());

        // clear text on click
        this.inputTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                CurrencyCalculatorLogicV2.this.inputTextField.setText("");
            }
        });
    }

    private void sendStatus(final Object status) {
        this.inputTextField.setText(status.toString());
    }

    private ActionListener createConvertAction(final Function<Double, Double> convert) {
        return e -> {
            // convert amount in text-box
            final double amount;
            try {
                amount = Double.parseDouble(
                    CurrencyCalculatorLogicV2.this.inputTextField.getText()
                );
            } catch (final NumberFormatException nfex) {
                CurrencyCalculatorLogicV2.this.sendStatus("Invalid Number entered");
                return;
            }
            CurrencyCalculatorLogicV2.this.sendStatus(
                Math.round(convert.apply(amount) * 100) / 100.0
            );
        };
    }

}
