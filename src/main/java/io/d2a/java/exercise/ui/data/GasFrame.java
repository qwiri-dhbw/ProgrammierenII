package io.d2a.java.exercise.ui.data;

import io.d2a.java.exercise.ui.data.builder.Botton;
import io.d2a.java.exercise.ui.data.builder.Box;
import io.d2a.java.exercise.ui.data.builder.Box.Direction;
import io.d2a.java.exercise.ui.data.builder.Grid;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class GasFrame extends JFrame {

    public static void main(String[] args) {
        new GasFrame();
    }

    private final Map<String, GasStation> gasStationsMap = new HashMap<>();

    private final JTextField stationNameField = new JTextField();
    private final JTextField dieselField = new JTextField();
    private final JTextField superE5Field = new JTextField();
    private final JTextField superE10Field = new JTextField();

    private void handleSave(final ActionEvent event) {
        final String stationName = this.stationNameField.getText().strip();
        if (stationName.isBlank()) {
            JOptionPane.showMessageDialog(
                null,
                "Please provide a station name",
                "Meldung",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        final GasStation station = new GasStation(
            this.parse(this.dieselField),
            this.parse(this.superE5Field),
            this.parse(this.superE10Field)
        );
        this.gasStationsMap.put(stationName, station);
        JOptionPane.showMessageDialog(
            null,
            "Gas Station saved: " + station,
            "Meldung",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void handleShowAll(final ActionEvent event) {
        final StringBuilder bob = new StringBuilder();
        this.gasStationsMap.keySet().stream().sorted().forEach(k -> {
            if (bob.length() > 0) {
                bob.append('\n');
            }
            bob.append(k).append(": ").append(this.gasStationsMap.get(k));
        });

        JOptionPane.showMessageDialog(
            null,
            bob.toString(),
            "Meldung",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public GasFrame() {
        this.setTitle("Gas Stations");
        this.add(new Box(Direction.VERTICAL)
            .with(new Grid(4, 2)
                .with(new JLabel("Station Name"))
                .with(stationNameField)
                .with(new JLabel("Diesel"))
                .with(dieselField)
                .with(new JLabel("Super E5"))
                .with(superE5Field)
                .with(new JLabel("Super E10"))
                .with(superE10Field))
            .with(new Box(Direction.HORIZONTAL)
                .with(new Botton("Save")
                    .click(this::handleSave))
                .with(new Botton("Show all")
                    .click(this::handleShowAll)))
        );
        this.pack();
        this.setVisible(true);
    }

    private double parse(final JTextComponent textComponent) {
        return this.parse(textComponent.getText());
    }

    private double parse(final String inp) {
        try {
            return Double.parseDouble(inp);
        } catch (final NumberFormatException nfex) {
            return -1;
        }
    }

}
