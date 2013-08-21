
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Jogo extends JPanel implements ActionListener {

    private Timer timer;
    private Cenario cenario;
    private Tanque tanqueRed;
    private boolean ingame;
    private int B_WIDTH;
    private int B_HEIGHT;

    public Jogo() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        ingame = true;

        setSize(640, 480);

        cenario = new Cenario();
        tanqueRed = new Tanque();


        timer = new Timer(0, this);
        timer.start();
    }

    public void addNotify() {
        super.addNotify();
        B_WIDTH = getWidth();
        B_HEIGHT = getHeight();
    }

    public void paint(Graphics g) {
        super.paint(g);

        if (ingame) {

            Graphics2D g2d = (Graphics2D) g;


            g2d.drawImage(cenario.getImage(), cenario.getX(), cenario.getY(), this);

            if (tanqueRed.isVisible()) {
                g2d.drawImage(tanqueRed.getImage(), tanqueRed.getX(), tanqueRed.getY(),
                        this);
            }

            ArrayList ms = tanqueRed.getTiros();

            for (int i = 0; i < ms.size(); i++) {
                Tiro m = (Tiro) ms.get(i);
                g2d.drawImage(m.getImage(),(int) m.getX(),(int) m.getY(), this);
            }

            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 13));
            g2d.drawString("Posição: " + tanqueRed.getX(), 5, 14);
            g2d.drawString("Ângulo: " + tanqueRed.getAngulo(), 105, 14);
            g2d.drawString("Força: " + tanqueRed.getForca(), 185, 14);


        } else {
            String msg = "Game Over";
            Font small = new Font("Helvetica", Font.BOLD, 14);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2,
                    B_HEIGHT / 2);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void actionPerformed(ActionEvent e) {

        ArrayList ms = tanqueRed.getTiros();

        for (int i = 0; i < ms.size(); i++) {
            Tiro m = (Tiro) ms.get(i);
            if (m.isVisible()) {
                m.move(tanqueRed.getForca(), tanqueRed.getAngulo());
            } else {
                ms.remove(i);
            }
        }


        tanqueRed.move();
        checkCollisions();
        repaint();
    }

    public void checkCollisions() {

        Rectangle r3 = tanqueRed.getBounds();


    }

    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            tanqueRed.keyPressed(e);
        }
    }
}
