package io.d2a.java.exercise.ui.data.builder;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class Grid extends JPanel {

    public Grid(final int rows, final int cols) {
        this.setLayout(new GridLayout(rows, cols));
    }

    public Grid with(final Component component) {
        this.add(component);
        return this;
    }

    public Grid all(final Component...components) {
        for (final Component component : components) {
            this.add(component);
        }
        return this;
    }

}
