package io.d2a.java.exercise.ui.data;

import io.d2a.java.exercise.ui.data.util.builder.Botton;
import io.d2a.java.exercise.ui.data.util.builder.Box;
import io.d2a.java.exercise.ui.data.util.builder.Box.Direction;
import io.d2a.java.exercise.ui.data.util.presets.Geritt;
import io.d2a.java.exercise.ui.data.util.builder.TextField;
import io.d2a.java.exercise.ui.data.util.TextFieldGroup;
import io.d2a.java.exercise.ui.data.util.presets.PaddedFrame;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

public class GasFrame extends PaddedFrame {

    public static void main(String[] args) {
        new GasFrame();
    }

    /**
     * Map that contains gas station name -> gas station prices
     */
    private final Map<String, GasStation> gasStationsMap = new HashMap<>();

    private final TextField stationNameField = new TextField();
    private final TextField dieselField = new TextField();
    private final TextField superE5Field = new TextField();
    private final TextField superE10Field = new TextField();

    private final TextFieldGroup group = new TextFieldGroup(
        stationNameField,
        dieselField,
        superE5Field,
        superE5Field
    );

    public GasFrame() {
        super("Gas Stations");
        this.add(new Box(Direction.VERTICAL)
            .with(new Geritt()
                .with("Station Name", this.stationNameField)
                .with("Diesel", this.dieselField)
                .with("Super E5", this.superE5Field)
                .with("Super E10", this.superE10Field))
            .with(new Box(Direction.HORIZONTAL)
                .with(new Botton("Save").click(this::handleSave))
                .with(new Botton("Show all").click(this::handleShowAll)))
        );
        this.pack();
    }

    ///

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
        // clear text boxes
        this.group.clear();
    }

    private void handleShowAll(final ActionEvent event) {
        JOptionPane.showMessageDialog(
            null,
            this.gasStationsMap.keySet().stream()
                .sorted()
                .map(key -> key + ": " + this.gasStationsMap.get(key))
                .collect(Collectors.joining("\n")),
            "Meldung",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    ///

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
