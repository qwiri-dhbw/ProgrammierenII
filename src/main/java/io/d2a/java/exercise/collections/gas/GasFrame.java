package io.d2a.java.exercise.collections.gas;

import io.d2a.java.exercise.ui.data.util.presets.PaddedFrame;
import io.d2a.swag.builder.components.Button;
import io.d2a.swag.builder.components.text.TextField;
import io.d2a.swag.builder.layouts.Box;
import io.d2a.swag.builder.layouts.Grid;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GasFrame extends PaddedFrame {

    public static void main(String[] args) {
        new GasFrame();
    }

    /**
     * Map that contains gas station name -> gas station prices
     */
    private final Map<String, GasStationPrices> gasStationsMap = new HashMap<>();

    private final TextField stationNameField = new TextField();
    private final TextField dieselField = new TextField();
    private final TextField superE5Field = new TextField();
    private final TextField superE10Field = new TextField();

    public GasFrame() {
        super("Gas Stations");
        this.add(Box.vertical()
                .with(Grid.builder()
                        .with("Station Name", this.stationNameField.build())
                        .with("Diesel", this.dieselField)
                        .with("Super E5", this.superE5Field)
                        .with("Super E10", this.superE10Field))
                .with(Box.horizontal()
                        .with(new Button("Save").click(this::handleSave))
                        .with(new Button("Show all").click(this::handleShowAll)))
        );
        this.pack();
    }

    ///

    private void handleSave(final ActionEvent event) {
        final String stationName = this.stationNameField.asString().strip();
        if (stationName.isBlank()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Please provide a station name",
                    "Meldung",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        final GasStationPrices station = new GasStationPrices(
                this.dieselField.asDouble(-1D),
                this.superE5Field.asDouble(-1.),
                this.superE10Field.asDouble(-1.0)
        );
        this.gasStationsMap.put(stationName, station);
        JOptionPane.showMessageDialog(
                null,
                "Gas Station saved: " + station,
                "Meldung",
                JOptionPane.INFORMATION_MESSAGE
        );
        // clear text boxes
        this.stationNameField.clear();
        this.dieselField.clear();
        this.superE5Field.clear();
        this.superE10Field.clear();
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

}
