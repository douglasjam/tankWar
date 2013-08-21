
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


import javax.swing.ImageIcon;

public class Cenario {

    private String cenarioPath = "/images/game_background1.png";
    private int x;
    private int y;
    private int width;
    private int height;
    private Image cenarioIMG;

    public Cenario() {

        ImageIcon cenario = new ImageIcon(this.getClass().getResource(cenarioPath));
        cenarioIMG = cenario.getImage();
        width = cenarioIMG.getWidth(null);
        height = cenarioIMG.getHeight(null);
        x = 0;
        y = 20;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return cenarioIMG;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
