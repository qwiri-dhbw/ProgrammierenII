package io.d2a.java.exercise.ui.data.builder;

import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Botton extends JButton {

    public Botton(final String text) {
        super(text);
    }

    public Botton click(final ActionListener listener) {
        this.addActionListener(listener);
        return this;
    }

}
