package io.d2a.java.exercise.ui.data.util.builder;

import java.util.function.Supplier;
import javax.swing.JTextField;

public class TextField extends JTextField {

    public void clear() {
        this.setText("");
    }

    public <T> T wrapNumberFormatException(final Supplier<T> supplier, final T def) {
        try {
            return supplier.get();
        } catch (final NumberFormatException nfex) {
            if (def != null) {
                return def;
            }
            throw nfex; // rethrow
        }
    }

    ///

    public int asInt() {
        return this.asInt(null);
    }

    public int asInt(final Integer def) {
        return this.wrapNumberFormatException(() -> Integer.parseInt(this.asString()), def);
    }

    public double asDouble() {
        return this.asDouble(null);
    }

    public double asDouble(final Double def) {
        return this.wrapNumberFormatException(() -> Double.parseDouble(this.asString()), def);
    }

    public String asString() {
        return this.getText();
    }

}
