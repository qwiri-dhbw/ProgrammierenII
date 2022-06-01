package io.d2a.java.exercise.ui;

import java.awt.HeadlessException;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextFrame extends JFrame {

    public static void main(String[] args) {
        new TextFrame();
    }

    public TextFrame() throws HeadlessException {
        this.setTitle("UwU");
        this.setSize(720, 480);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JFileChooser chooser = new JFileChooser();
        final FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Select Text Files",
            "txt", "log", "raw", "yml", "yaml", "json"
        );
        chooser.setFileFilter(filter);

        final int result = chooser.showOpenDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        final JTextArea area = new JTextArea();
        this.add(area);

        try {
            final String join = String.join(
                "\n",
                Files.readAllLines(chooser.getSelectedFile().toPath())
            );
            area.setText(join);
        } catch (IOException e) {
            area.setText("Da ist etwas schepp gelaufen: " + e.getMessage());
        }
    }

}
