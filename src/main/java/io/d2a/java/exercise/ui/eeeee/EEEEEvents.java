package io.d2a.java.exercise.ui.eeeee;

import static io.d2a.java.exercise.ui.eeeee.When.What.CLICK;

import java.awt.event.KeyAdapter;
import javax.swing.JButton;

public class EEEEEvents {

    @When(we = CLICK, then = "sendHelp")
    private final JButton button = new JButton();

    private void sendHelp(final KeyAdapter keyAdapter) {

    }

}
