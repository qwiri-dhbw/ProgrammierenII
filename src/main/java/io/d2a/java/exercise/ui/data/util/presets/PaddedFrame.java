package io.d2a.java.exercise.ui.data.util.presets;

import java.awt.FlowLayout;
import javax.swing.JFrame;

public class PaddedFrame extends JFrame {

    public PaddedFrame(final String title) {
        this(title, true);
    }

    public PaddedFrame(final String title, final boolean display) {
        this.setTitle(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        if (display) {
            this.setVisible(true);
        }
    }

}
