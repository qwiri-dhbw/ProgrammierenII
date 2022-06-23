package io.d2a.java.exercise.enums.library;

import io.d2a.java.exercise.enums.library.Book.Type;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Part of lectures on 'Programming in Java'. Baden-Wuerttemberg * Cooperative State University.
 * <p>
 * (C) 2016-2018 by W. Geiger, T. Schlachter, C. Schmitt, W. Suess *
 *
 * @author DHBW lecturer
 * @version 1.1
 */
public class Library {

    private final JFrame frame;
    private final String filename = "books.txt";
    private final JTextField[] inputFields;
    private final List<Book> books = new ArrayList<>();

    public Library() {
        this.loadBooks();
        // Input fields incl. labels
        final JPanel panInput = new JPanel();
        panInput.setLayout(new GridLayout(4, 2, 5, 5));
        this.inputFields = new JTextField[Type.values().length];
        for (int i = 0; i < Type.values().length; i++) {
            panInput.add(new JLabel(Type.values()[i].friendly));
            this.inputFields[i] = new JTextField("");
            panInput.add(this.inputFields[i]);
        }
        // Continued on next page
        // save button incl. event handling
        final JButton btnSave = new JButton("Save entry");
        btnSave.addActionListener(e -> {
            Library.this.saveBook(
                Library.this.inputFields[0].getText(),
                Library.this.inputFields[1].getText(),
                Integer.parseInt(Library.this.inputFields[2].getText()),
                Library.this.inputFields[3].getText()
            );
            for (JTextField field : Library.this.inputFields) {
                field.setText("");
            }
        });
        // sort buttons incl. event handling
        JPanel panSort = new JPanel(new FlowLayout());
        panSort.add(new JLabel("Ordered output:"));
        for (final Type value : Type.values()) {
            JButton but = new JButton(value.friendly);
            // artificial attribute
            but.setActionCommand(value.name());
            but.addActionListener(e -> Library.this.sort(value));
            panSort.add(but);
        }
        this.frame = new JFrame("Library");
        this.frame.setLayout(new BorderLayout());
        this.frame.add(panInput, BorderLayout.NORTH);
        this.frame.add(btnSave, BorderLayout.CENTER);
        this.frame.add(panSort, BorderLayout.SOUTH);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(500, 190);
        this.frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Library();
    }

    public void saveBook(String title, String author, int year, String publisher) {
        final Book book = new Book(title, author, year, publisher);
        this.books.add(book);
        try (PrintWriter pw = new PrintWriter(new FileWriter(this.filename, true))) {
            pw.println(book); // uses toString of Book } catch ( Exception ex ) {
        } catch (final IOException ex) {
            System.err.println("Write error: " + ex.getLocalizedMessage());
        }
    }

    public void loadBooks() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            while (br.ready()) {
                final String[] parts = br.readLine().split(";");
                if (parts.length == 4) {
                    this.books.add(new Book(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]));
                }
            }
        } catch (Exception ex) {
            System.err.println("Read error: " + ex.getLocalizedMessage());
        }
    }

    /**
     * Order books by order criteria and display ordered list
     */
    public void sort(final Type order) {
        this.books.sort(new BookComparator(order)); // uses Library.toString()
        JOptionPane.showMessageDialog(
            this.frame,
            this,
            "Books ordered by " + order.friendly,
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * All books as a single multi line String *
     *
     * @return string with one book per line
     */
    @Override
    public String toString() {
        final StringBuilder output = new StringBuilder();
        for (Book book : this.books) {
            output.append(book).append(System.lineSeparator());
        }
        return output.toString();
    }
}