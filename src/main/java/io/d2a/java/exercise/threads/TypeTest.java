package io.d2a.java.exercise.threads;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TypeTest extends JFrame {

    public TypeTest() {
        this.setSize(720, 480);
        this.add(new TypePanel(new String[]{
                "hello", "world", "my", "name", "is", "daniel", "how", "are", "you",
        }));
        this.setTitle("Type Test");
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TypeTest();
    }

    public class TypePanel extends JPanel implements KeyListener {

        private final String[] wordsSplit;

        private final String wordsFinal;
        private final Color darkGrayFont = new Color(94, 94, 94);
        private final Color redFont = new Color(255, 83, 46);
        private final Color orangeFont = new Color(255, 109, 0);
        private final Color greenFont = new Color(182, 255, 109);
        private String progress;
        public TypePanel(String[] words) {
            this.wordsSplit = words;
            this.wordsFinal = String.join(" ", this.wordsSplit);
            this.progress = "";

            this.setFocusable(true);
            this.requestFocusInWindow();
            this.addKeyListener(this);
        }

        @Override
        protected void paintComponent(Graphics g) {
            // draw background
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setFont(new Font("Comic Code Ligatures", Font.BOLD, 17));

            final char[] finalWords = this.wordsFinal.toCharArray();
            final char[] progressWords = this.progress.toCharArray();

            final int margin = 15;

            int x = (int)(getWidth() / 2.0 - (margin * this.wordsFinal.length()) / 2.0);
            int lastX = x;
            final int y = getHeight() / 2;

            for (int i = 0; i < finalWords.length; i++) {
                final char f = finalWords[i];

                // draw silhouette
                g.setColor(darkGrayFont);
                g.drawString(String.valueOf(f), x, y);

                if (i < progressWords.length) {
                    final char p = progressWords[i];

                    if (p == f) {
                        g.setColor(Color.WHITE);
                    } else if (p != 0) {
                        g.setColor(redFont);
                    }
                    g.drawString(String.valueOf(f), x, y);

                    lastX = x;
                }

                x += margin;
            }

            g.setColor(orangeFont);
            g.fillRect(lastX - 3 + 15, y + 10, 15, 5);

        }

        @Override
        public void keyTyped(KeyEvent e) {
            final char c = Character.toLowerCase(e.getKeyChar());
            // backspace
            if (c == 8) {
                if (this.progress.length() >= 1) {
                    this.progress = this.progress.substring(0, this.progress.length() - 1);
                }
            }
            if (c >= ' ' && c <= '~') {
                this.progress += c;
                System.out.println(this.progress);
            }
            this.repaint();
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

}
