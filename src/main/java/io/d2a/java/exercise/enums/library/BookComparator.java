package io.d2a.java.exercise.enums.library;

import io.d2a.java.exercise.enums.library.Book.Type;
import java.util.Comparator;

/**
 * Part of lectures on 'Programming in Java'. Baden-Wuerttemberg * Cooperative State University.
 * <p>
 * (C) 2016-2018 by W. Geiger, T. Schlachter, C. Schmitt, W. Suess *
 *
 * @author DHBW lecturer * @version 1.0
 */
public record BookComparator(Type order) implements Comparator<Book> {

    @Override
    public int compare(Book b1, Book b2) {
        return switch (this.order) {
            case TITLE -> b1.title().compareTo(b2.title());
            case AUTHOR -> b1.author().compareTo(b2.author());
            case YEAR -> Integer.compare(b1.year(), b2.year());
            case PUBLISHER -> b1.publisher().compareTo(b2.publisher());
        };
    }
}