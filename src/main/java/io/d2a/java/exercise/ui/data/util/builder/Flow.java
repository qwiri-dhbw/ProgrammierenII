package io.d2a.java.exercise.ui.data.util.builder;

import io.d2a.java.exercise.ui.data.util.Withy;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JPanel;

public class Flow extends JPanel implements Withy<Flow> {

    public Flow(final int align) {
        this.setLayout(new FlowLayout(align));
    }

    @Override
    public Flow with(final Component component) {
        this.add(component);
        return this;
    }

    @Override
    public Flow with(final ComponentBuilder<?> builder) {
        this.add(builder.build());
        return this;
    }

    @Override
    public Flow all(final Component...components) {
        for (final Component component : components) {
            this.add(component);
        }
        return this;
    }

}
