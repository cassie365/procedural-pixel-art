package color;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Generates a color palette based off of a flat shade.
 */
public class GenColor {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 16;

    public static void main(String[] args){
        //Palette p = new Palette((int) (Math.random()*(360-1)+1),70,70,10,.06f);
        Palette p = new Palette(32,96,90,10,.06f);
        try {
            createBall(p);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void createBall(Palette p) throws IOException {
        BufferedImage b = new BufferedImage(WIDTH,WIDTH, BufferedImage.TYPE_4BYTE_ABGR);

        Graphics2D g2d = b.createGraphics();

        Color[] palette = p.getPalette();


        g2d.setColor(Color.white);
        g2d.fillRect(0,0,WIDTH,WIDTH);

        //First stripe on left, highlight
        g2d.setColor(palette[4]);
        g2d.fillOval(0,0,WIDTH,WIDTH);

        //Second stripe on left, lightest color
        g2d.setColor(palette[3]);
        g2d.fillOval(2,2,WIDTH-4,WIDTH-4);

        //Middle stripe, base color
        g2d.setColor(palette[2]);
        g2d.fillOval(4,2,WIDTH-8,WIDTH-8);

        //Second to last stripe, should be darkest color
        g2d.setColor(palette[1]);
        g2d.fillOval(6,1,WIDTH-12,WIDTH-12);

        //Last stripe, outline color
        g2d.setColor(palette[0]);
        g2d.fillOval(7,1,WIDTH-16,WIDTH-16);

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

    /**
     * Print a palette as an image with all colors light to dark
     * @param p the palette to print
     * @throws IOException
     */
    public static void printPalette(Palette p) throws IOException {
        BufferedImage b = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);

        Graphics2D g2d = b.createGraphics();

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
}
