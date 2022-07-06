package io.d2a.java.exercise.threads;

import io.d2a.java.exercise.threads.Letters.Letter;
import io.d2a.java.exercise.ui.data.util.presets.PaddedFrame;
import io.d2a.util.ColorRainbow;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class DancingText extends PaddedFrame implements Runnable {

    public static final int FPS = 30;
    public static final String TEXT =// "Hallo was geht ABAP?!";
    // "|||||||||||||||||||||||||||||||||||||||||||||||||||||||";
         "..........................................................................................";
    public static final Tuple[] PRESETS = {
            new Tuple(Letters.MODE_SIN, 0.2927, 0.6008, 1),
            new Tuple(Letters.MODE_TAN, 0.0501, 0.2840, 1),
            new Tuple(Letters.MODE_TAN, 0.0786, 1.0782, 1),
            new Tuple(Letters.MODE_DIGITAL_SIN, 0.0744, 0.1633, 3.6429),
    };
    // ".o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.o.";
    //"-------------------------";
    private final TextComponent comp; // component which displays the text
    private int WID = 15;

    public DancingText(final String text) {
        super("Dancing Text");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.comp = new TextComponent(text); // make text component "full screen"
        comp.setPreferredSize(this.getSize());
        this.add(comp);

        this.setBackground(Color.RED);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

        new Thread(this).start();
    }

    public static void main(String[] args) throws InterruptedException {
        new DancingText(TEXT);
    }

    @Override
    @SuppressWarnings({"InfiniteLoopStatement", "BusyWait"})
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000 / FPS);
            } catch (InterruptedException ignored) {
            }
            this.comp.letters.cycle();
            this.repaint();
        }
    }

    /// Presets

    private record Tuple(int mode, double freq, double cp, double amp) {
    }

    public class TextComponent extends JPanel implements MouseInputListener {
        private final Letters letters;

        private static final Font font = new Font("Comic Code Ligatures", Font.BOLD, 32);
        private final CycleArray<Tuple> preset = new CycleArray<>(PRESETS);
        private float hue = 0; // used for rainbow colors
        private int frame = 0;
        private int mouseX = 0, mouseY = 0;

        public TextComponent(final String text) {
            this.letters = new Letters(text);
            this.addMouseMotionListener(this);
            this.addMouseListener(this);
        }

        @Override
        public void paintComponent(final Graphics g) {
            this.frame = (this.frame + 1) % FPS;

            this.setPreferredSize(DancingText.this.getSize());

            // set background color
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            // set default color to black if anything goes wrong with the hue
            g.setColor(Color.BLACK);
            // set color to a large font


            // widthOfText is the width of the letters in pixel
            // to center the text in the middle
            final int widthOfText = this.letters.text.length() * WID;

            // x value for the first letter
            int x = getWidth() / 2 - widthOfText / 2 - WID;
            // y (start) value for the letters
            int yStart = getHeight() / 2;

            // used for "shade" effect, but wasn't worth it.
            int prevY = 0;

            // draw separate letters
            for (final Letter letter : this.letters.getLetters()) {
                g.setFont(font.deriveFont((float) letter.fontSize));

                // cycle rainbow color
                g.setColor(ColorRainbow.hslColor(Math.min(1.0f, (hue += .0003) * 1.4f), 1f, .5f));
                final int lY = yStart + letter.getY();

                g.drawString(
                        String.valueOf(letter.getC()), /* char */
                        x += WID, /* x position */
                        lY /* y position */
                );

                if (letter.prevY != Integer.MIN_VALUE) {
                    g.setColor(Color.YELLOW);
                    g.drawLine(x + 6, yStart + letter.prevY, x + 6, lY);
                }

                if (prevY <= lY) {
                    g.setColor(Color.GRAY);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                g.drawLine(mouseX, mouseY, x, lY);

                prevY = lY;
            }


            // reset rainbow cycle
            if (hue >= 1) {
                hue = 0;
            }

            g.setFont(this.getFont().deriveFont(12.f));

            // draw debug string
            g.drawString(String.format("F: %.4f, C: %.4f, A: %.4f, M: %d",
                    this.letters.multiplierFreq,
                    this.letters.multiplierCp,
                    this.letters.multiplierAmp,
                    this.letters.getModeIndex()
            ), 20, 20);
        }

        @Override
        public void mouseMoved(final MouseEvent e) {
            this.mouseX = e.getX();
            this.mouseY = e.getY();

            if (e.isAltDown()) {
                return;
            }

            if (e.isShiftDown()) {
                this.letters.multiplierAmp = .5 + (double) e.getY() / (getHeight() / 6.0);
                WID = (int) (((double) e.getX() / (double) getHeight()) * 50);
            } else {
                final double multiplierFreq = (double) e.getX() / (double) getWidth();
                final double multiplierClinch = (double) e.getY() / (getHeight() / 2.0);
                this.letters.multiplierFreq = multiplierFreq;
                this.letters.multiplierCp = multiplierClinch;
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.isShiftDown()) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    this.letters.sizer = !this.letters.sizer;
                } else {
                    this.letters.toggle();
                }
            } else {
                final Tuple preset = this.preset.cycle();
                this.letters.multiplierFreq = preset.freq();
                this.letters.multiplierCp = preset.cp();
                this.letters.multiplierAmp = preset.amp();
                this.letters.setMode(preset.mode());
            }
        }

        @Override
        public void mouseDragged(final MouseEvent e) {
            // hier könnte Ihre Werbung stehen
            // https://github.com/darmiel/sponsors
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // hier könnte Ihre Werbung stehen
            // https://github.com/darmiel/sponsors
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // hier könnte Ihre Werbung stehen
            // https://github.com/darmiel/sponsors
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // hier könnte Ihre Werbung stehen
            // https://github.com/darmiel/sponsors
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // hier könnte Ihre Werbung stehen
            // https://github.com/darmiel/sponsors
        }

    }

}
