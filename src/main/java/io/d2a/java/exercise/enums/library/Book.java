package io.d2a.java.exercise.enums.library;

/**
 * Part of lectures on 'Programming in Java'. Baden-Wuerttemberg * Cooperative State University.
 * <p>
 * (C) 2016-2018 by W. Geiger, T. Schlachter, C. Schmitt, W. Suess *
 *
 * @author DHBW lecturer
 * @version 1.1
 */
public record Book(String title, String author, int year, String publisher) {

    @Override
    public String toString() {
        return this.title + ";" + this.author + ";" + this.year + ";"
            + this.publisher;
    }

    enum Type {
        TITLE("Titel"),
        AUTHOR("Author"),
        YEAR("Year"),
        PUBLISHER("Publisher");

        final String friendly;

        Type(final String friendly) {
            this.friendly = friendly;
        }
    }

}