package io.d2a.java.exercise.ui.data.util.builder;

import io.d2a.java.exercise.ui.data.util.Withy;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Box extends JPanel implements Withy<Box> {

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

    @Override
    public Box with(final Component component) {
        this.add(component);
        return this;
    }

    @Override
    public Box with(final ComponentBuilder<?> builder) {
        this.add(builder.build());
        return this;
    }

    @Override
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
