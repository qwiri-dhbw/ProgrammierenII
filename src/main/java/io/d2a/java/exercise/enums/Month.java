package io.d2a.java.exercise.enums;

import java.util.GregorianCalendar;

public enum Month {

    JANUARY(31, "Hartung", "Eismond"),
    FEBRUARY(28, "Hornung", "Schmelzmond", "Taumond", "Narrenmond", "Rebmond", "Hintester"),
    MARCH(31, "Lenzing", "Lenzmond"),
    APRIL(30, "Launing", "Ostermond"),
    MAY(31, "Winnemond", "Blumenmond"),
    JUNE(30, "Brachet", "Brachmond"),
    JULY(31, "Heuert", "Heumond"),
    AUGUST(31, "Ernting", "Erntemond", "Bisemond"),
    SEPTEMBER(30, "Scheiding", "Herbstmond"),
    OCTOBER(31, "Gilbhart", "Gilbhard", "Weinmond"),
    NOVEMBER(30, "Nebelung", "Windmond", "Wintermond"),
    DECEMBER(31, "Julmond", "Heilmond", "Christmond", "Dustermond");

    private final int days;
    private final String[] oldNames;

    Month(final int days, final String... oldNames) {
        this.days = days;
        this.oldNames = oldNames;
    }

    public int getDays() {
        return days;
    }

    public String[] getOldNames() {
        return oldNames;
    }

    public static void main(String[] args) {
        final int monthIndex  = GregorianCalendar.getInstance().get(GregorianCalendar.MONTH);
        final Month month = Month.values()[monthIndex];
        System.out.printf("Der %s hat %d Tage und hieß früher '%s'",
            month.name(), month.getDays(), String.join(", ", month.getOldNames()));
    }

}
