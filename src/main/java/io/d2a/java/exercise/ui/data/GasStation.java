package io.d2a.java.exercise.ui.data;

public record GasStation(double diesel, double superE5, double superE10) {

    @Override
    public String toString() {
        return String.format("Diesel: %.2f, SuperE5: %.2f, SuperE10: %.2f",
            this.diesel(), this.superE5(), this.superE10());
    }

}
