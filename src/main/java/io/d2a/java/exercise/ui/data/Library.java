package io.d2a.java.exercise.ui.data;

import io.d2a.java.exercise.ui.data.builder.Botton;
import io.d2a.java.exercise.ui.data.builder.Box;
import io.d2a.java.exercise.ui.data.builder.Box.Direction;
import io.d2a.java.exercise.ui.data.builder.Flow;
import io.d2a.java.exercise.ui.data.builder.Grid;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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

        this.add(new Box(Direction.VERTICAL)
            .with(new Grid(4, 2)
                .all(new JLabel(BY_TITLE), this.titleText,
                    new JLabel(BY_AUTHOR), this.authorText,
                    new JLabel(BY_YEAR), this.yearText,
                    new JLabel(BY_PUBLISHER), this.publisherText))
            .with(new Flow(FlowLayout.CENTER)
                .with(new Botton("Save Entry")
                    .click(this::handleSave)))
            .with(new Box(Direction.HORIZONTAL).all(
                new JLabel("Ordered Output:"),
                new Botton(BY_TITLE)
                    .click(handleSort(BY_TITLE)),
                new Botton(BY_AUTHOR)
                    .click(handleSort(BY_AUTHOR)),
                new Botton(BY_YEAR)
                    .click(handleSort(BY_YEAR)),
                new Botton(BY_PUBLISHER)
                    .click(handleSort(BY_PUBLISHER))
            ))
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
