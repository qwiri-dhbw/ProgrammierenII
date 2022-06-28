package io.d2a.java.exercise.ui.data;

import io.d2a.swag.builder.components.Button;
import io.d2a.swag.builder.layouts.Box;
import io.d2a.swag.builder.layouts.Flow;
import io.d2a.swag.builder.layouts.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Library extends JFrame {

    public static void main(String[] args) {
        new Library();
    }

    public static final String BY_TITLE = "Title";
    public static final String BY_AUTHOR = "Author";
    public static final String BY_YEAR = "Year";
    public static final String BY_PUBLISHER = "Publisher";

    private final JTextField titleText = new JTextField();
    private final JTextField authorText = new JTextField();
    private final JTextField yearText = new JTextField();
    private final JTextField publisherText = new JTextField();

    private final Collection<Book> books = new HashSet<>();

    public Library() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        this.add(Box.vertical()
                .with(Grid.builder()
                        .with(BY_TITLE, this.titleText)
                        .with(BY_AUTHOR, this.authorText)
                        .with(BY_YEAR, this.yearText)
                        .with(BY_PUBLISHER, this.publisherText))
                .with(Flow.center()
                        .with(new Button("Save Entry").click(this::handleSave)))
                .with(Box.horizontal().
                        with(new JLabel("Ordered Output:"))
                        .with(new Button(BY_TITLE).click(handleSort(BY_TITLE)))
                        .with(new Button(BY_AUTHOR).click(handleSort(BY_AUTHOR)))
                        .with(new Button(BY_YEAR).click(handleSort(BY_YEAR))))
        );

        this.setVisible(true);
        this.pack();
    }

    private ActionListener handleSort(final String by) {
        return e -> JOptionPane.showMessageDialog(null, this.books.stream()
                .sorted((o1, o2) -> {
                    if (BY_YEAR.equals(by)) {
                        return Integer.compare(o1.year(), o2.year());
                    }
                    final Comparator<String> comp = String.CASE_INSENSITIVE_ORDER;
                    return switch (by) {
                        case BY_TITLE -> comp.compare(o1.title(), o2.title());
                        case BY_AUTHOR -> comp.compare(o1.author(), o2.author());
                        case BY_PUBLISHER -> comp.compare(o1.publisher(), o2.publisher());
                        default -> 0;
                    };
                })
                .map(Book::toString)
                .collect(Collectors.joining("\n")));
    }

    private void handleSave(final ActionEvent event) {
        // create book from text-fields
        // we don't check if anything is null
        // if this happens - we blame the user not the program :)
        final Book book = new Book(
                this.titleText.getText(),
                this.authorText.getText(),
                Integer.parseInt(this.yearText.getText()),
                this.publisherText.getText()
        );
        // "save" to temp collection
        this.books.add(book);

        JOptionPane.showMessageDialog(
                null,
                "Book saved",
                "Okidoki!",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

}
