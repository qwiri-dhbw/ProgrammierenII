package io.d2a.java.exercise.ui.data.util.builder;

import io.d2a.java.exercise.ui.data.util.Withy;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class Grid extends JPanel implements Withy<Grid> {

    public Grid(final int rows, final int cols) {
        this.setLayout(new GridLayout(rows, cols));
    }

    @Override
    public Grid with(final Component component) {
        this.add(component);
        return this;
    }

    @Override
    public Grid with(final ComponentBuilder<?> builder) {
        this.add(builder.build());
        return this;
    }

    @Override
    public Grid all(final Component...components) {
        for (final Component component : components) {
            this.add(component);
        }
        return this;
    }

}
