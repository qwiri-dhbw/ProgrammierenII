package io.d2a.java.exercise.ui.data.util.presets;

import io.d2a.java.exercise.ui.data.util.builder.Grid;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Geritt {

    private record Tuple (String label, JComponent component) {
    }

    private final List<Tuple> tuples = new ArrayList<>();

    public Geritt with(final String label) {
        return this.with(label, new JTextField());
    }

    public Geritt with(final String label, final JComponent component) {
        this.tuples.add(new Tuple(label, component));
        return this;
    }

    public Grid build() {
        final Grid grid = new Grid(this.tuples.size(), 2);
        for (final Tuple tuple : this.tuples) {
            grid.with(new JLabel(tuple.label))
                .with(tuple.component);
        }
        return grid;
    }

}
