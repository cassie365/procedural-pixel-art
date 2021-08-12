import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        genIcons(12);
    }

    private static void genIcons(int amt) throws IOException {
        Map<String,Color> colorMap = new HashMap();
        colorMap.put("blade_highlight",Color.decode("#b8b5b9"));
        colorMap.put("blade_base",Color.decode("#868188"));
        colorMap.put("blade_shadow",Color.decode("#646365"));
        colorMap.put("hilt_base",Color.decode("#a77b5b"));
        colorMap.put("hilt_shadow",Color.decode("#80493a"));
        colorMap.put("jewel",Color.decode("#2555a6"));
        colorMap.put("outline",Color.decode("#45444f"));

        for(int i = 0; i<amt; i++){

            int width = 15;
            int height = 15;

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            int[] hiltCoords = genHilt(bufferedImage,5,6,colorMap);

            int guardLoc = genGuard(hiltCoords,bufferedImage,3,3,colorMap);

            genBlade(guardLoc, bufferedImage,8,10,2,colorMap);

            File file = new File("icons/sword_"+i+".png");
            ImageIO.write(bufferedImage, "png", file);
        }
    }

    private static void genBlade(int guardLoc, BufferedImage bufferedImage, int min, int max, int width, Map<String, Color> colorMap) {
        Color highlight = colorMap.get("blade_highlight");
        Color base = colorMap.get("blade_base");
        Color shadow = colorMap.get("blade_shadow");

        Graphics2D g2d = bufferedImage.createGraphics();

        int limit = (int) (Math.random()*(max-min)+min)+guardLoc;
        boolean hasSplit = (int) (Math.random() * 100) > 50 && width >=2;

        int y = 15-guardLoc;
        for(int x = guardLoc; x<=limit; x++){
            if (x == limit){
                g2d.setColor(base);
                g2d.fillRect(x-1,y,1,1);
                g2d.dispose();
                return;
            }

            if(hasSplit){
                if ((int) (Math.random() * 100) > 75)
                    g2d.setColor(Color.white);
            }else{
                g2d.setColor(base);
            }

            g2d.fillRect(x-1,y,1,1);

            if(width>=1){
                for(int i = 1; i<=width; i++){
                    if(i==width && x+1 == limit)
                        continue;
                    g2d.setColor(shadow);
                    g2d.fillRect(x-1+i,y,1,1);
                    g2d.setColor(highlight);
                    g2d.fillRect(x-1,y-i,1,1);
                }
            }

            y--;
        }
    }

    private static int genGuard(int[] coords, BufferedImage bufferedImage, int min, int max, Map<String, Color> colorMap) {
        Color highlight = colorMap.get("blade_highlight");
        Color base = colorMap.get("blade_base");
        Color shadow = colorMap.get("blade_shadow");

        //Generate 1 pixel at a time. Location on each gen is remembered
        Graphics2D g2d = bufferedImage.createGraphics();

        //determine length of guard, min 3 max of 5
        int limit = (int) (Math.random()*(max-min)+min);
        //We need a way for our algo to pick a random change. 0-2
        //I am for now, placing the possible changes in a 2d array
        //These changes will need to be reflected on the other side of the parent pixel.
        int[][] pos = {{-1,-1},{0,-1},{-1,0}};
        int[][] reflecPos = {{1,1},{1,0},{0,1}};

        int currentX = coords[0]-1;
        int currentY = coords[1];
        int reflectX = coords[0]-1;
        int reflectY = coords[1];
        for(int i = 0; i<limit; i++){
            //Calculate new pixel
            int selection = (int) (Math.random() * 3);
            currentX+=pos[selection][0];
            currentY+=pos[selection][1];
            reflectX+=reflecPos[selection][0];
            reflectY+=reflecPos[selection][1];

            g2d.setColor(base);
            g2d.fillRect(currentX,currentY,1,1);
            g2d.setColor(shadow);
            g2d.fillRect(reflectX,reflectY,1,1);
        }

        g2d.dispose();

        return coords[0];

    }

    public static int[] genHilt(BufferedImage bufferedImage, int min, int max, Map<String, Color> colorMap){
        Color base = colorMap.get("hilt_base");
        Color shadow = colorMap.get("hilt_shadow");
        Color jewel = colorMap.get("jewel");

        Graphics2D g2d = bufferedImage.createGraphics();

        int limit = (int) (Math.random()*(max-min)+min);
        boolean hasJewel = (int) (Math.random() * 100) > 50;

        g2d.setColor(Color.white);
        g2d.fillRect(0,0,15,15);

        int y = 15;
        for(int x = 0; x<=limit; x++){
            if (x == limit){
                g2d.setColor(base);
                g2d.fillRect(x-1,y,1,1);
                g2d.dispose();
                return new int[]{x,y};
            }

            g2d.setColor(base);
            g2d.fillRect(x-1,y,1,1);
            g2d.setColor(shadow);
            g2d.fillRect(x,y,1,1);
            g2d.setColor(base);
            g2d.fillRect(x-1,y-1,1,1);

            if(x==2 && hasJewel){
                g2d.setColor(jewel);
                g2d.fillRect(x-1,y,1,1);
                g2d.setColor(base);
                g2d.fillRect(x-2,y-1,1,1);
                g2d.setColor(shadow);
                g2d.fillRect(x,y+1,1,1);
            }

            y--;
        }

        return null;
    }
}
