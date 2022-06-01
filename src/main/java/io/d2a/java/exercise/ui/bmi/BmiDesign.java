package io.d2a.java.exercise.ui.bmi;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class BmiDesign extends JFrame {

    public static void main(String[] args) {
        new BmiDesign();
    }

    public final JTextField weightTextField = new JTextField(8);
    public final JTextField sizeTextField = new JTextField(8);

    protected final JRadioButton genderMaleRadioButton = new JRadioButton("Male", true);
    protected final JRadioButton genderFemaleRadioButton = new JRadioButton("Female");

    protected final JButton calcButton = new JButton("Rechen");
    protected final JTextField bmiResultTextField = new JTextField("Ergebnis", 12);
    protected final JTextField statusTextField = new JTextField("Status");

    public BmiDesign() {
        final JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        this.add(main);

        // weight
        final JPanel weightPanel = new JPanel(new FlowLayout());
        main.add(weightPanel);
        weightPanel.add(new JLabel("Gewicht (in kg):"));
        weightPanel.add(this.weightTextField);

        // size
        final JPanel sizePanel = new JPanel(new FlowLayout());
        main.add(sizePanel);
        sizePanel.add(new JLabel("Größe (in m):"));
        sizePanel.add(this.sizeTextField);

        // gender
        final JPanel genderPanel = new JPanel(new FlowLayout());
        main.add(genderPanel);
        {
            final ButtonGroup group = new ButtonGroup();
            group.add(this.genderFemaleRadioButton);
            group.add(this.genderMaleRadioButton);
        }
        genderPanel.add(this.genderMaleRadioButton);
        genderPanel.add(this.genderFemaleRadioButton);

        // calculate
        final JPanel calcPanel = new JPanel(new FlowLayout());
        calcPanel.add(this.calcButton);
        calcPanel.add(this.bmiResultTextField);
        main.add(calcPanel);

        main.add(this.statusTextField);

        this.pack();
        this.setVisible(true);

        this.woLogik();
    }

    private void woLogik() {
        this.calcButton.addActionListener(this::calculate);
    }

    private void calculate(final ActionEvent event) {
        // get weight and calculate
        final double weight, size;
        try {
            weight = Double.parseDouble(this.weightTextField.getText());
            size = Double.parseDouble(this.sizeTextField.getText());
        } catch (final NumberFormatException nfex) {
            this.statusTextField.setText("invalid weight/size");
            return;
        }

        final boolean male = this.genderMaleRadioButton.isSelected();

        // calculate bmi
        final double bmi = weight / (size * size);
        this.bmiResultTextField.setText(String.valueOf(Math.round(bmi * 100) / 100.0));

        // get text state from bmi
        final String state = Bmi.getState(bmi, male);
        this.statusTextField.setText(state);
    }

}
