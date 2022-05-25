package io.d2a.java.exercise.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ComponentFrame extends JFrame implements KeyListener {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final ComponentFrame frame = new ComponentFrame();
            frame.setSize(720, 480);
            frame.setVisible(true);
        });
    }

    private final JProgressBar bar = new JProgressBar(0, 100);
    private final JPasswordField passwordField = new JPasswordField();
    private final JLabel securitey = new JLabel("Passwort ist nicht sicher.");

    public ComponentFrame() {
        super("UwU");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        final JLabel label = new JLabel("Label");

        final JTextField textField = new JTextField();
        textField.setColumns(10);

        passwordField.setColumns(10);
        passwordField.addKeyListener(this);

        final JButton button = new JButton();
        button.setToolTipText("Tooltip!");

        final JToggleButton toggleButton = new JToggleButton();
        toggleButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent e) {
                toggleButton.setText(toggleButton.isSelected() ? "A" : "B");
            }
        });
        final JCheckBox checkBox = new JCheckBox("enable coolness");

        final JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Hello");
        comboBox.addItem("World");
        comboBox.addItem("What's");
        comboBox.addItem("Up?");

        final ButtonGroup radioButtonGroup = new ButtonGroup();
        for (int i = 0; i < 3; i++) {
            final JRadioButton radioButton = new JRadioButton("#" + (i + 1));
            radioButtonGroup.add(radioButton);
            panel.add(radioButton);
        }

        panel.add(label);
        panel.add(textField);
        panel.add(passwordField);
        panel.add(button);
        panel.add(toggleButton);
        panel.add(checkBox);
        panel.add(comboBox);

        bar.setSize(200, 20);
        panel.add(bar);
        panel.add(securitey);

        add(panel);
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        // get password strength
        final char[] password = this.passwordField.getPassword();
        final int uwu = (int) Math.sqrt(password.length * 10);
        this.bar.setValue(uwu);

        if (uwu >= 100) {
            this.securitey.setText("Passwort ist jetzt halbwegs sicher.");
        } else if (uwu >= 75) {
            this.securitey.setText("Langsam wird's sicherer");
        } else {
            this.securitey.setText("Nee, das Passwort ist echt nicht sicher. "
                + "Pack doch noch ein paar Zeichen dran, dann wird's schon!");
        }
        this.securitey.setText(this.securitey.getText() + " (" + password.length + ")");
        this.setSize(this.getSize().width, 50 + uwu * 4);
        this.passwordField.setColumns(Math.min(password.length, 64) / 2);
    }

}
