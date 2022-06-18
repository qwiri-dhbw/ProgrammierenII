package io.d2a.java.exercise.ui.data.builder;

import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Box extends JPanel {

    public enum Direction {
        HORIZONTAL,
        VERTICAL;
    }

    public Box(final Direction direction) {
        final int axis = switch (direction) {
            case HORIZONTAL -> BoxLayout.X_AXIS;
            default -> BoxLayout.Y_AXIS;
        };

        // noinspection MagicConstant
        this.setLayout(new BoxLayout(this, axis));
    }

    public Box with(final Component component) {
        this.add(component);
        return this;
    }

    public Box all(final Component...components){
        for (final Component component : components) {
            this.add(component);
        }
        return this;
    }

    public Box alignX(final float alignment) {
        this.setAlignmentX(alignment);
        return this;
    }

}
