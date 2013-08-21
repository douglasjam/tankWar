
import java.awt.Image;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tiro {

    private double x, y;
    private Image image;
    boolean visible;
    private int width, height;
    private final int BOARD_WIDTH = 640;
    private final int MISSILE_SPEED = 1;
    public static boolean tiroExistente;
    
    private int anguloRestante;
    private int forcaRestante;

    public Tiro(int x, int y) {

        ImageIcon ii =
                new ImageIcon(this.getClass().getResource("/images/small-ball.png"));
        image = ii.getImage();
        visible = true;
        width = image.getWidth(null);
        height = image.getHeight(null);
        this.x = x;
        this.y = y;

        tiroExistente = false;
        forcaRestante = -1;
        anguloRestante = -1;
    }

    public Image getImage() {
        return image;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    public void move(int forca, int angulo) {
        if (forcaRestante == -1 && anguloRestante == -1) {
            forcaRestante = forca;
            anguloRestante = angulo;
        } else {
            // aqui aplicar formulas da gravidade


            if ((x > BOARD_WIDTH) || (y >= 430)) {
                //visible = false;
            } else {
                x += MISSILE_SPEED * 0.1 * forca;
            }

            // decrementa altura

            if (y >= 430) {
                y = 430;
                tiroExistente = false;
            } else {
                y = y + 0.7;
            }
        }
    }
}
