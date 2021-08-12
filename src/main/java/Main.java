import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        int width = 15;
        int height = 15;
        
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        genGuard(genHilt(bufferedImage),bufferedImage);

        File file = new File("sword.png");
        ImageIO.write(bufferedImage, "png", file);
    }

    private static void genGuard(int[] coords, BufferedImage bufferedImage) {
        //Generate 1 pixel at a time. Location on each gen is remembered
        Graphics2D g2d = bufferedImage.createGraphics();

        //determine length of guard, min 3 max of 5
        int limit = (int) (Math.random()*(5-3)+3);
        //We need a way for our algo to pick a random change. 0-2
        //I am for now, placing the possible changes in a 2d array
        //These changes will need to be reflected on the other side of the parent pixel.
        int[][] pos = {{0,1},{-1,0},{-1,-1}};

        int currentX = coords[0];
        int currentY = coords[1];
        int reflectX = coords[0];
        int reflectY = coords[1];
        for(int i = 0; i<limit; i++){
            //Calculate new pixel

            //Reflect pixels to other side.
        }



    }

    public static int[] genHilt(BufferedImage bufferedImage){
        Graphics2D g2d = bufferedImage.createGraphics();

        int limit = (int) (Math.random()*(6-3)+3);
        boolean hasJewel = (int) (Math.random() * 100) > 50;

        g2d.setColor(Color.white);
        g2d.fillRect(0,0,15,15);

        int y = 15;
        for(int x = 0; x<=limit; x++){
            if (x == limit){
                g2d.setColor(Color.black);
                g2d.fillRect(x-1,y,1,1);
                g2d.dispose();
                return new int[]{x,y};
            }

            g2d.setColor(Color.black);
            g2d.fillRect(x-1,y,1,1);
            g2d.setColor(Color.pink);
            g2d.fillRect(x,y,1,1);
            g2d.setColor(Color.blue);
            g2d.fillRect(x-1,y-1,1,1);

            if(x==2 && hasJewel){
                g2d.setColor(Color.green);
                g2d.fillRect(x-1,y,1,1);
                g2d.setColor(Color.blue);
                g2d.fillRect(x-2,y-1,1,1);
                g2d.setColor(Color.pink);
                g2d.fillRect(x,y+1,1,1);
            }

            y--;
        }
    }
}
