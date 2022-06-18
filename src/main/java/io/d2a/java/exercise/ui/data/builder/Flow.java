package io.d2a.java.exercise.ui.data.builder;

import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JPanel;

public class Flow extends JPanel {

    public Flow(final int align) {
        this.setLayout(new FlowLayout(align));
    }

    public Flow with(final Component component) {
        this.add(component);
        return this;
    }

    public Flow all(final Component...components) {
        for (final Component component : components) {
            this.add(component);
        }
        return this;
    }

}
