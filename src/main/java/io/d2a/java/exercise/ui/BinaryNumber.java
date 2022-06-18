package io.d2a.java.exercise.ui;

import io.d2a.eeee.converter.StringConverter;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class BinaryNumber extends JFrame {

    public static void main(String[] args) {
        new BinaryNumber();
    }

    public static final boolean DEFAULT_HARDCORE = false;
    public static final int NUMBER_OF_CONTROLS = 12;
    public static final BigInteger BASE = new BigInteger("1337");

    private final JLabel resultLabel = new JLabel("0", JLabel.CENTER);
    private final JToggleButton[] buttons = new JToggleButton[NUMBER_OF_CONTROLS];

    private boolean hardcore = DEFAULT_HARDCORE;

    public BinaryNumber() {
        final JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, NUMBER_OF_CONTROLS));

        final ImageIcon onIcon = new ImageIcon("on.png");
        final ImageIcon offIcon = new ImageIcon("off.png");

        for (int i = 0; i < NUMBER_OF_CONTROLS; i++) {
            final JToggleButton button = new JToggleButton();
            button.setIcon(offIcon);
            button.setSelectedIcon(onIcon);
            button.addActionListener(this::yes);

            buttons[NUMBER_OF_CONTROLS - i - 1] = button;
            buttonPanel.add(button);
        }

        for (int i = 0; i < NUMBER_OF_CONTROLS; i++) {
            final JLabel label = new JLabel(
                BASE.toString() + StringConverter.toPowString(NUMBER_OF_CONTROLS - i - 1),
                JLabel.CENTER
            );
            buttonPanel.add(label);
        }

        main.add(buttonPanel);

        final JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        main.add(resultPanel);
        this.resultLabel.setFont(new Font("Comic Code Ligatures", Font.BOLD, 32));
        this.resultLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(final MouseEvent e) {
                BinaryNumber.this.hardcore = !BinaryNumber.this.hardcore;
                BinaryNumber.this.yes(null);
            }
        });
        resultPanel.add(this.resultLabel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(main);

        this.pack();
        this.setVisible(true);
    }

    private void yes(final ActionEvent event) {
        BigInteger result = new BigInteger("0");
        final StringBuilder bob = new StringBuilder();

        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isSelected()) {
                result = result.add(BASE.pow(i));
                if (bob.length() > 0) {
                    bob.append(" + ");
                }
                bob.append(BASE).append(StringConverter.toPowString(i));
            }
        }

        if (!this.hardcore) {
            bob.delete(0, bob.length()).append(result);
        }
        this.resultLabel.setText(bob.toString());

        Toolkit.getDefaultToolkit().beep();
    }

}
