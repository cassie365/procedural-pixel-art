package weapons.util;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Contains static methods for styling pixel art.
 */
public class Shader {

    /**
     * Add a color gradient overlay at 10% to non-transparent pixels
     * @param bufferedImage the image to affect
     */
    public static void colorRamp(BufferedImage bufferedImage){
        Graphics2D g2d = bufferedImage.createGraphics();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = bufferedImage.getRGB(x,y);
                //I should replace this code with some calculation using vector later, but Im bad at math
                if(rgb!=0){
                    if(x>12 && height-y>12){
                        g2d.setColor(new Color(1f,0.9f,0f,.1f));
                    }
                    else if(x>8 && height-y>8){
                        g2d.setColor(new Color(1f,0.5f,0.5f,.1f));
                    }
                    else if(x>4 && height-y>4){
                        g2d.setColor(new Color(1f,0f,0.7f,.1f));
                    }
                    else{
                        g2d.setColor(new Color(0.7f,0f,1f,.1f));
                    }
                    g2d.fillRect(x,y,1,1);
                }
            }
        }
        g2d.dispose();
    }
}
