
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import javax.swing.ImageIcon;

public class Tanque {

    private String tanquePath = "/images/tanque_normal.png";
    private String canoPath = "/images/cano_normal.png";
    private int dx;
    private int dy;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private Image tanqueIMG;
    private int angulo;
    private int forca;
    private ArrayList tiros;

    public Tanque() {

        ImageIcon corpo = new ImageIcon(this.getClass().getResource(tanquePath));
        ImageIcon cano = new ImageIcon(this.getClass().getResource(canoPath));

        tanqueIMG = juntaCorpoCano(corpo.getImage(), cano.getImage());
        // imagem do tamanho em que o tanque + cano podem girar livremente
        // fazendo -20 a a 90 graus
        width = 85;
        height = 85;
        visible = true;
        x = 100;
        y = 350;

        tiros = new ArrayList();
    }

    public Image rotacionaCano(Image cano, int angulo) {
        BufferedImage novaImagem = new BufferedImage(85, 85, BufferedImage.TRANSLUCENT);
        Graphics g = novaImagem.getGraphics();
        g.drawImage(cano, 25, 59, null);
        // agora rotacionar imagem em sei la onde
        Graphics2D g2d = (Graphics2D) g ;
        g2d.rotate(50);
        


        return novaImagem;
    }

    public Image juntaCorpoCano(Image corpo, Image cano) {
        BufferedImage novaImagem = new BufferedImage(85, 85, BufferedImage.TRANSLUCENT);
        Graphics g = novaImagem.getGraphics();

        g.drawImage(rotacionaCano(cano, 0), 0, 0, null);
        g.drawImage(corpo, 0, 56, null);

        return novaImagem;
    }

    public void move() {

        if (x < 1) {
            x = 1;
        }
        if (y < 1) {
            y = 1;
        }
        if (angulo > 85) {
            angulo = 85;
        }
        if (angulo < - 20) {
            angulo = -20;
        }
        if (forca < 10) {
            forca = 10;
        }
        if (forca > 60) {
            forca = 60;
        }
    }

    public ArrayList getTiros() {
        return tiros;
    }

    public int getAngulo() {
        return angulo;
    }

    public int getForca() {
        return forca;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return tanqueIMG;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void atirar() {
        if (Tiro.tiroExistente == false) {
            tiros.add(new Tiro(x + width, y + height / 2));
            Tiro.tiroExistente = true;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            atirar();
        }

        if (key == KeyEvent.VK_LEFT) {
            x -= 1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            x += 1;
        }

        if (key == KeyEvent.VK_UP) {
            angulo += 1;
        }

        if (key == KeyEvent.VK_DOWN) {
            angulo -= 1;
        }

        if (key == KeyEvent.VK_A) {
            forca += 1;
        }

        if (key == KeyEvent.VK_Z) {
            forca -= 1;
        }
    }
    /*
    public void fire() {
    missiles.add(new Missile(x + width, y + height/2));
    }
     *
     */
}
