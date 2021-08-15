package model.weapons;

import model.materials.FoodMaterial;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * POJO representing a minecraft weapon
 */
public abstract class Weapon {
    protected final BufferedImage bufferedImage;
    protected final int[][] pixelMap;

    private final String displayName;
    private final String name;
    private final FoodMaterial material;

    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;

    public Weapon(String displayName, String name, FoodMaterial material){
        this.displayName = displayName;
        this.name = name;
        this.material = material;
        this.bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        this.pixelMap = new int[WIDTH][HEIGHT];
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getName() {
        return name;
    }

    public FoodMaterial getMaterial() {
        return material;
    }

    public abstract boolean genImage() throws IOException;
    public boolean genWeaponInfo(){
        return false;
    }

    protected String getImagePath(){
        return "icons/"+this.getMaterial().getName()+"_"+this.getName()+".png";
    }

    protected static void genBlade(int guardLoc, BufferedImage bufferedImage, int min, int max, int width, Map<String, Color> colorMap) {
        Color highlight = colorMap.get("blade_highlight");
        Color base = colorMap.get("blade_base");
        Color shadow = colorMap.get("blade_shadow");
        Color outline = colorMap.get("outline");

        Graphics2D g2d = bufferedImage.createGraphics();

        int limit = (int) (Math.random()*(max-min)+min)+guardLoc;
        boolean hasSplit = (int) (Math.random() * 100) > 50 && width >=2;
        int[][] pos = {{-1,-1},{0,-1},{-1,0}};
        int[][] reflecPos = {{1,1},{1,0},{0,1}};
        ArrayList<Integer[]> pixels = new ArrayList<>();

        /*
                int[][] pos = {{-1,-1},{0,-1},{-1,0}};
        int[][] reflecPos = {{1,1},{1,0},{0,1}};
        ArrayList<Integer[]> pixels = new ArrayList<>();

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

            //add to guardPixels Array
            pixels.add(new Integer[]{currentX, currentY});
            pixels.add(new Integer[]{reflectX, reflectY});


            g2d.setColor(base);
            g2d.fillRect(currentX,currentY,1,1);
            g2d.setColor(shadow);
            g2d.fillRect(reflectX,reflectY,1,1);
        }

        //Color outline
        genOutline(pixels,bufferedImage,colorMap);
         */

        int y = 16-guardLoc;
        for(int x = guardLoc; x<=limit; x++){
            if (x == limit){
                g2d.setColor(base);
                g2d.fillRect(x-1,y,1,1);
                g2d.dispose();
                return;
            }

            if(hasSplit){
                if ((int) (Math.random() * 100) > 75)
                    g2d.setColor(new Color(5,5,5,0));
            }else{
                g2d.setColor(base);
            }

            g2d.fillRect(x-1,y,1,1);

            if(width>=1){
                for(int i = 1; i<=width; i++){
                    if(i == width && x+1 < limit){
                        //add to guardPixels Array
                        pixels.add(new Integer[]{x-1+i, y});
                        pixels.add(new Integer[]{x-1, y-i});
                    }
                    if(width>1 && i==width && x+1 == limit){
                        g2d.setColor(outline);
                        g2d.fillRect(x,y,1,1);
                        g2d.fillRect(x-1,y-1,1,1);
                        pixels.add(new Integer[]{x, y});
                        continue;
                    }
                    g2d.setColor(shadow);
                    g2d.fillRect(x-1+i,y,1,1);
                    g2d.setColor(highlight);
                    g2d.fillRect(x-1,y-i,1,1);
                }
            } else{
                pixels.add(new Integer[]{x-1, y});
            }

            genOutline(pixels,bufferedImage,colorMap);

            y--;
        }
    }

    protected static int genGuard(int[] coords, BufferedImage bufferedImage, int min, int max, Map<String, Color> colorMap) {
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
        ArrayList<Integer[]> pixels = new ArrayList<>();

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

            //add to guardPixels Array
            pixels.add(new Integer[]{currentX, currentY});
            pixels.add(new Integer[]{reflectX, reflectY});


            g2d.setColor(base);
            g2d.fillRect(currentX,currentY,1,1);
            g2d.setColor(shadow);
            g2d.fillRect(reflectX,reflectY,1,1);
        }

        //Color outline
        genOutline(pixels,bufferedImage,colorMap);

        //Color pixels
        for(int i = 0; i<pixels.size(); i+=2){
            g2d.setColor(base);
            g2d.fillRect(pixels.get(i)[0],pixels.get(i)[1],1,1);
            g2d.setColor(shadow);
            g2d.fillRect(pixels.get(i+1)[0],pixels.get(i+1)[1],1,1);
        }

        g2d.dispose();

        return coords[0];

    }

    protected static int[] genHilt(BufferedImage bufferedImage, int min, int max, Map<String, Color> colorMap){
        Color base = colorMap.get("hilt_base");
        Color shadow = colorMap.get("hilt_shadow");
        Color jewel = colorMap.get("jewel");

        Graphics2D g2d = bufferedImage.createGraphics();

        int limit = (int) (Math.random()*(max-min)+min);
        int[] result = null;
        boolean hasJewel = (int) (Math.random() * 100) > 50;

        g2d.setColor(new Color(5,5,5,0));
        g2d.fillRect(0,0,16,16);

        int y = 16;
        for(int x = 0; x<=limit; x++){
            if (x == limit){
                g2d.setColor(base);
                g2d.fillRect(x-1,y,1,1);
                g2d.dispose();
                result = new int[]{x,y};
                break;
            }

            g2d.setColor(base);
            g2d.fillRect(x-1,y,1,1);
            g2d.setColor(shadow);
            g2d.fillRect(x,y,1,1);
            g2d.setColor(base);
            g2d.fillRect(x-1,y-1,1,1);

            if( max > 4 && x==2 && hasJewel){
                g2d.setColor(jewel);
                g2d.fillRect(x-1,y,1,1);
                g2d.setColor(base);
                g2d.fillRect(x-2,y-1,1,1);
                g2d.setColor(shadow);
                g2d.fillRect(x,y+1,1,1);
            }

            y--;
        }

        return result;
    }

    protected static void genSpike(int start, BufferedImage bufferedImage,Map<String, Color> colorMap){
        Color highlight = colorMap.get("blade_highlight");
        Color base = colorMap.get("blade_base");
        Color shadow = colorMap.get("blade_shadow");

        Graphics2D g2d = bufferedImage.createGraphics();

        int numBases = 4;
        int[] pattern = new int[numBases];
        int[][] pixels = new int[numBases][2];

        int limit = numBases+start;

        int y = 16-start;
        int place = 0;
        for(int x = start-1; x<=limit && place<numBases; x++){
            pixels[place][0] = x;
            pixels[place][1] = y;
            pattern[place] = place%2==0?(int) (Math.random()*(5-3)+3):(int) (Math.random()*(3-1)+1);
            place++;
            y--;
        }

        buildPattern(g2d,pattern,pixels,colorMap);

        g2d.dispose();
    }

    protected static void genMaceHead(int start, BufferedImage bufferedImage,Map<String, Color> colorMap){
        Color highlight = colorMap.get("blade_highlight");
        Color base = colorMap.get("blade_base");
        Color shadow = colorMap.get("blade_shadow");

        Graphics2D g2d = bufferedImage.createGraphics();

        int numBases = 4;
        int[] pattern = new int[numBases];
        int[][] pixels = new int[numBases][2];

        int limit = numBases+start;

        int y = 16-start;
        int place = 0;
        for(int x = start-1; x<=limit && place<numBases; x++){
            pixels[place][0] = x;
            pixels[place][1] = y;
            pattern[place] = (int) (Math.random()*(5-3)+3);
            place++;
            y--;
        }

        buildPattern(g2d,pattern,pixels,colorMap);

        g2d.dispose();
    }

    protected static void genSpearHead(int start, BufferedImage bufferedImage, Map<String, Color> colorMap){
        Color highlight = colorMap.get("blade_highlight");
        Color base = colorMap.get("blade_base");
        Color shadow = colorMap.get("blade_shadow");

        Graphics2D g2d = bufferedImage.createGraphics();

        int limit = 6+start;

        int y = 16-start;
        for(int x = start-1; x<=limit; x++){
            if (x == limit){
                g2d.setColor(base);
                g2d.fillRect(x,y,1,1);
                g2d.dispose();
                return;
            }


            g2d.setColor(base);
            g2d.fillRect(x,y,1,1);
            for(int i = 1; i<=1; i++){
                g2d.setColor(shadow);
                g2d.fillRect(x+i,y,1,1);
                g2d.setColor(highlight);
                g2d.fillRect(x,y-i,1,1);
            }
            y--;
        }
    }

    private static void buildPattern(Graphics2D g2d, int[] pattern, int[][] pixels, Map<String, Color> colorMap){
        Color highlight = colorMap.get("blade_highlight");
        Color base = colorMap.get("blade_base");
        Color shadow = colorMap.get("blade_shadow");

        for(int i = 0; i<pattern.length; i++){
            switch(pattern[i]){
                case 1:
                    p1(pixels[i],colorMap,g2d);
                    break;
                case 2:
                    p2(pixels[i],colorMap,g2d);
                    break;
                case 3:
                    p3(pixels[i],colorMap,g2d);
                    break;
                case 4:
                    p4(pixels[i],colorMap,g2d);
                    break;
            }
        }
    }

    private static void p1(int[] coords, Map<String, Color> colorMap,Graphics2D g2d){
        Color base = colorMap.get("blade_base");

        g2d.setColor(base);
        g2d.fillRect(coords[0],coords[1],1,1);
    }

    private static void p2(int[] coords, Map<String, Color> colorMap,Graphics2D g2d){
        Color highlight = colorMap.get("blade_highlight");
        Color base = colorMap.get("blade_base");
        Color shadow = colorMap.get("blade_shadow");

        g2d.setColor(base);
        g2d.fillRect(coords[0],coords[1],1,1);
        for(int i = 1; i<=1; i++){
            g2d.setColor(highlight);
            g2d.fillRect(coords[0],coords[1]-i,1,1);
            g2d.setColor(shadow);
            g2d.fillRect(coords[0]+i,coords[1],1,1);
        }
    }

    private static void p3(int[] coords, Map<String, Color> colorMap,Graphics2D g2d){
        Color highlight = colorMap.get("blade_highlight");
        Color base = colorMap.get("blade_base");
        Color shadow = colorMap.get("blade_shadow");
        g2d.setColor(base);
        g2d.fillRect(coords[0],coords[1],1,1);
        for(int i = 1; i<=2; i++){
            g2d.setColor(highlight);
            g2d.fillRect(coords[0],coords[1]-i,1,1);
            g2d.setColor(shadow);
            g2d.fillRect(coords[0]+i,coords[1],1,1);
        }
    }

    private static void p4(int[] coords, Map<String, Color> colorMap,Graphics2D g2d){
        Color highlight = colorMap.get("blade_highlight");
        Color base = colorMap.get("blade_base");
        Color shadow = colorMap.get("blade_shadow");
        g2d.setColor(base);
        g2d.fillRect(coords[0],coords[1],1,1);
        for(int i = 1; i<=2; i++){
            g2d.setColor(highlight);
            g2d.fillRect(coords[0],coords[1]-i,1,1);
            g2d.setColor(shadow);
            g2d.fillRect(coords[0]+i,coords[1],1,1);

            if(i == 2){
                g2d.setColor(shadow);
                g2d.fillRect(coords[0]+i,coords[1],1,1);
                g2d.setColor(highlight);
                g2d.fillRect(coords[0],coords[1]-i,1,1);
                g2d.setColor(shadow);
                g2d.fillRect(coords[0]+i+1,coords[1],1,1);
                g2d.fillRect(coords[0]+i,coords[1]+1,1,1);
                g2d.setColor(highlight);
                g2d.fillRect(coords[0],coords[1]-i-1,1,1);
                g2d.fillRect(coords[0]-1,coords[1]-i,1,1);
            }
        }
    }

    private static void genOutline(ArrayList<Integer[]> pixels, BufferedImage bufferedImage, Map<String, Color> colorMap){
        //Generate 1 pixel at a time. Location on each gen is remembered
        Graphics2D g2d = bufferedImage.createGraphics();

        int[][] outlinePos = {{0,-1},{-1,0},{0,1},{1,0}};

        for(Integer[] i: pixels){
            for(int j = 0; j<outlinePos.length; j++){
                int x = i[0];
                int y = i[1];
                x+=outlinePos[j][0];
                y+=outlinePos[j][1];
                if(!pixels.contains(new Integer[]{x,y})){
                    g2d.setColor(colorMap.get("outline"));
                    g2d.fillRect(x,y,1,1);
                }
            }
        }
    }

    protected static void colorRamp(BufferedImage bufferedImage, int width, int height){
        Graphics2D g2d = bufferedImage.createGraphics();
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
