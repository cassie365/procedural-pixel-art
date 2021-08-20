package color;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Generates a color palette based off of a flat shade.
 */
public class GenColor {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 16;

    public static void main(String[] args) throws IOException {
        genHueSpread();

        BufferedImage b = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);

        Graphics2D g2d = b.createGraphics();

        Palette p = new Palette(358,76,64,10,.05f);
        //Palette p = new Palette(32,96,100,1,.05f);
        Color[] palette = p.getPalette();


        g2d.setColor(Color.white);
        g2d.fillRect(0,0,16,16);

        //First stripe on left, highlight
        g2d.setColor(palette[0]);
        g2d.fillRect(0,0,4,16);

        //Second stripe on left, lightest color
        g2d.setColor(palette[1]);
        g2d.fillRect(4,0,4,16);

        //Middle stripe, base color
        g2d.setColor(palette[2]);
        g2d.fillRect(8,0,4,16);

        //Second to last stripe, should be darkest color
        g2d.setColor(palette[3]);
        g2d.fillRect(12,0,4,16);

        //Last stripe, outline color
        g2d.setColor(palette[4]);
        g2d.fillRect(16,0,4,16);

        File file = new File("generated_colors/test.png");

        ImageIO.write(b, "png", file);

        g2d.dispose();
    }

    public static void genHueSpread() throws IOException {
        int numColors = 64;
        int colorWidth = 16;
        int colorHeight = 64;
        BufferedImage b = new BufferedImage(colorWidth*numColors,HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);

        Graphics2D g2d = b.createGraphics();

        float interval = 1f/numColors;

        int x = 0;
        for(int i = 1; i < numColors; i++){
            g2d.setColor(Color.getHSBColor(interval*i,.7f,.7f));
            g2d.fillRect(x,0,colorWidth,colorHeight);

            g2d.setColor(Color.black);
            g2d.drawString(String.valueOf(i),x,14);
            x+=colorWidth;
        }

        File file = new File("generated_colors/hueSpread.png");

        ImageIO.write(b, "png", file);

        g2d.dispose();
    }
}
