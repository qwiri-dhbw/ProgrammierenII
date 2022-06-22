package io.d2a.java.exercise.ui.data.util.presets;

import io.d2a.java.exercise.ui.data.util.builder.ComponentBuilder;
import io.d2a.java.exercise.ui.data.util.builder.Grid;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class Geritt implements ComponentBuilder<Grid> {

    private record Tuple (String label, JTextComponent component) {
    }

    private final List<Tuple> tuples = new ArrayList<>();

    public Geritt with(final String label) {
        return this.with(label, new JTextField());
    }

    public Geritt with(final String label, final JTextComponent component) {
        this.tuples.add(new Tuple(label, component));
        return this;
    }

    @Override
    public Grid build() {
        final Grid grid = new Grid(this.tuples.size(), 2);
        for (final Tuple tuple : this.tuples) {
            grid.with(new JLabel(tuple.label))
                .with(tuple.component);
        }
        return grid;
    }

    public void setText(final String newText) {
        for (final Tuple tuple : this.tuples) {
            tuple.component.setText(newText);
        }
    }

    public void clear() {
        this.setText("");
    }

}
