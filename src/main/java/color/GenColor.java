package color;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Generates a color palette based off of a flat shade.
 */
public class GenColor {
    private static final int WIDTH = 70;
    private static final int HEIGHT = 70;

    public static void main(String[] args){
        rainbowSpheres();
        int color = (int) (Math.random()*(359-1)+1);
        //color = 26;
        int sat = 79;
        int bri = 38;
        System.out.println("Color="+color+" Sat=50 Bri=50 Shift=15 Interval=.15f");
        Palette p = new Palette(color,sat,bri,15,.15f);
        try {
            greyscaleToColor(p);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void rainbowSpheres(){
        int color = (int) (Math.random()*(359-1)+1);
        System.out.println("Color="+color+" Sat=50 Bri=50 Shift=15 Interval=.15f");
        Palette p = new Palette(color,50,50,15,.15f);
        //Palette p = new Palette(71,50,50,15,.15f);

        BufferedImage b = new BufferedImage(1200,2200, BufferedImage.TYPE_4BYTE_ABGR);
        try {
            int x = 0;
            int y = 0;
            int counter = 0;
            for(int i = 1; i<=360; i++){
                p = new Palette(i,70,70,20,.15f);
                createBall(p,b,x,y);

                if(counter<=16){
                    x+=70;
                }else{
                    x=0;
                    y+=70;
                    counter=0;
                }
                counter++;
            }

            File file = new File("generated_colors/spheres.png");

            ImageIO.write(b, "png", file);
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
        g2d.fillOval(8,2,WIDTH-12,WIDTH-12);

        //Second to last stripe, should be darkest color
        g2d.setColor(palette[1]);
        g2d.fillOval(12,3,WIDTH-20,WIDTH-20);

        //Last stripe, outline color
        g2d.setColor(palette[0]);
        g2d.fillOval(26,6,WIDTH-45,WIDTH-45);

        //glare
        g2d.setColor(palette[5]);
        g2d.fillOval(28,7,WIDTH-60,WIDTH-60);

        File file = new File("generated_colors/test.png");

        ImageIO.write(b, "png", file);

        g2d.dispose();
    }

    public static void createBall(Palette p, BufferedImage b, int startx, int starty) throws IOException {
        int width = 70;
        Graphics2D g2d = b.createGraphics();

        Color[] palette = p.getPalette();


        g2d.setColor(Color.white);
        g2d.fillRect(startx,starty,width,width);

        //First stripe on left, highlight
        g2d.setColor(palette[4]);
        g2d.fillOval(startx,starty,width,width);

        //Second stripe on left, lightest color
        g2d.setColor(palette[3]);
        g2d.fillOval(startx+2,starty+2,width-4,width-4);

        //Middle stripe, base color
        g2d.setColor(palette[2]);
        g2d.fillOval(startx+8,starty+2,width-12,width-12);

        //Second to last stripe, should be darkest color
        g2d.setColor(palette[1]);
        g2d.fillOval(startx+12,starty+3,width-20,width-20);

        //Last stripe, outline color
        g2d.setColor(palette[0]);
        g2d.fillOval(startx+26,starty+6,width-45,width-45);

        //glare
        g2d.setColor(palette[5]);
        g2d.fillOval(startx+28,starty+7,width-60,width-60);

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

    public static void greyscaleToColor(Palette p) throws IOException {
        BufferedImage buff = ImageIO.read(new File("generated_colors/grey_sword.png"));
        BufferedImage buff2 = new BufferedImage(160,16,BufferedImage.TYPE_4BYTE_ABGR);
        int[] colors = new int[6];
        Map<Integer,Color> colorMap = new HashMap<>();

        for(int i = 1; i<=6; i++){
            int clr = buff.getRGB(i, 1);
            int red =   (clr & 0x00ff0000) >> 16;
            int green = (clr & 0x0000ff00) >> 8;
            int blue =   clr & 0x000000ff;
            System.out.println("Red Color value = " + red);
            System.out.println("Green Color value = " + green);
            System.out.println("Blue Color value = " + blue);
            colors[i-1] = clr;
        }

        Graphics2D gg = buff2.createGraphics();
        int width = buff.getWidth();
        int height = buff.getHeight();

        int curx = 0;
        for(int i = 0; i<16; i++){
            Palette pal = new Palette((int) (Math.random()*(359-1)+1),60,60,15,.15f);
            Color[] palette = pal.getPalette();

            colorMap.put(colors[0],palette[4]);
            colorMap.put(colors[1],palette[3]);
            colorMap.put(colors[2],palette[2]);
            colorMap.put(colors[3],palette[1]);
            colorMap.put(colors[4],palette[0]);
            colorMap.put(colors[5],palette[5]);

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int rgb = buff.getRGB(x,y);
                    Color newColor = colorMap.get(rgb);
                    System.out.println(newColor);
                    if(newColor!=null){
                        gg.setColor(newColor);
                        gg.fillRect(curx+x,y,1,1);
                    }
                }
            }

            curx+=16;
        }

        File file = new File("generated_colors/test_swords.png");

        ImageIO.write(buff2, "png", file);

        gg.dispose();
    }
}
