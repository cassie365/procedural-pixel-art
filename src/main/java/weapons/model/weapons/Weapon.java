package weapons.model.weapons;

import weapons.model.materials.FoodMaterial;
import weapons.util.BladePatternBuilder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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
    private final String localeName;

    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;

    public Weapon(String displayName, String name, FoodMaterial material){
        this.displayName = displayName;
        this.name = name;
        this.material = material;

        localeName = this.material.getDisplayName()+" "+this.displayName;
        bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        pixelMap = new int[WIDTH][HEIGHT];
    }

    // TODO: REMOVE
    public BufferedImage getBufferedImage(){
        return this.bufferedImage;
    }

    public String getLocaleName() {
        return localeName;
    }

    public String getRegName() {
        return material.getName()+"_"+name;
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
    protected String getImagePath(){
        return "icons/"+this.getMaterial().getName()+"_"+this.getName()+".png";
    }

    // TODO: Combine the genBlade methods to reduce redundant code

    public static void newGenBlade(int[] startCoords, BufferedImage bufferedImage, int minLength, int maxLength, int width, Map<String, Color> colorMap) throws IOException {
        Color[] gradient = new Color[6];
        gradient[0] = colorMap.get("outline");
        gradient[1] = colorMap.get("blade_shadow2");
        gradient[2] = colorMap.get("blade_shadow");
        gradient[3] = colorMap.get("blade_base");
        gradient[4] = colorMap.get("blade_highlight");
        gradient[5] = colorMap.get("glare");

        int diagonalLength = (int) (Math.random()*(maxLength-minLength)+minLength);

        Graphics2D g2d = bufferedImage.createGraphics();

        int shrinkFactor = 3-1;

        int x = startCoords[0];
        int y = startCoords[1];

        //We must build the bottom of the sword first. This adds +2 to our blad length
        g2d.setColor(gradient[0]);
        g2d.fillRect(x,y-2,3,3);
        g2d.setColor(gradient[1]);
        g2d.fillRect(x,y-1,2,2);
        x++;
        y--;
        //Second pre-build chunk of sword
        g2d.setColor(gradient[0]);
        g2d.fillRect(x,y-2,3,3);
        g2d.setColor(gradient[1]);
        g2d.fillRect(x,y-1,2,2);
        g2d.setColor(gradient[2]);
        g2d.fillRect(x,y,1,1);
        x++;
        y--;

        for(int i = 0; i<=diagonalLength; i++){
            g2d.setColor(gradient[1]);
            g2d.fillRect(x,y-2,3,3);
            g2d.setColor(gradient[4]);
            g2d.fillRect(x,y-1,2,2);
            g2d.setColor(gradient[3]);
            g2d.fillRect(x,y,1,1);
            x++;
            y--;
        }

        //Cap our sword
        g2d.setColor(gradient[1]);
        g2d.fillRect(x,y-2,3,3);
        g2d.setColor(gradient[5]);
        g2d.fillRect(x,y-1,2,2);
        g2d.setColor(gradient[3]);
        g2d.fillRect(x,y,1,1);
        x++;
        y--;
        //Second pre-build chunk of sword
        g2d.setColor(gradient[1]);
        g2d.fillRect(x,y-2,3,3);
        g2d.setColor(gradient[5]);
        g2d.fillRect(x,y-1,2,2);
        g2d.setColor(gradient[4]);
        g2d.fillRect(x,y,1,1);
        x++;
        y--;
        g2d.setColor(gradient[4]);
        g2d.fillRect(x,y,1,1);

        g2d.dispose();
    }

    public static void genRapier(int[] startCoords, BufferedImage bufferedImage, int minLength, int maxLength, Map<String, Color> colorMap){
        Color[] gradient = new Color[6];
        gradient[0] = colorMap.get("outline");
        gradient[1] = colorMap.get("blade_shadow2");
        gradient[2] = colorMap.get("blade_shadow");
        gradient[3] = colorMap.get("blade_base");
        gradient[4] = colorMap.get("blade_highlight");
        gradient[5] = colorMap.get("glare");

        int diagonalLength = (int) (Math.random()*(maxLength-minLength)+minLength);

        Graphics2D g2d = bufferedImage.createGraphics();

        int x = startCoords[0];
        int y = startCoords[1];

        //We must build the bottom of the sword first. This adds +2 to our blad length
        g2d.setColor(gradient[0]);
        g2d.fillRect(x,y-2,3,3);
        g2d.setColor(gradient[1]);
        g2d.fillRect(x,y-1,2,2);
        x++;
        y--;
        //Second pre-build chunk of sword
        g2d.setColor(gradient[0]);
        g2d.fillRect(x,y-2,3,3);
        g2d.setColor(gradient[1]);
        g2d.fillRect(x,y-1,2,2);
        g2d.setColor(gradient[2]);
        g2d.fillRect(x,y,1,1);
        x++;
        y--;

        for(int i = 0; i<=diagonalLength; i++){
            g2d.setColor(gradient[1]);
            g2d.fillRect(x,y-2,3,3);
            g2d.setColor(gradient[4]);
            g2d.fillRect(x,y-1,2,2);
            g2d.setColor(gradient[3]);
            g2d.fillRect(x,y,1,1);
            x++;
            y--;
        }

        //Cap our sword
        g2d.setColor(gradient[1]);
        g2d.fillRect(x,y-2,3,3);
        g2d.setColor(gradient[5]);
        g2d.fillRect(x,y-1,2,2);
        g2d.setColor(gradient[3]);
        g2d.fillRect(x,y,1,1);
        x++;
        y--;
        //Second pre-build chunk of sword
        g2d.setColor(gradient[1]);
        g2d.fillRect(x,y-2,3,3);
        g2d.setColor(gradient[5]);
        g2d.fillRect(x,y-1,2,2);
        g2d.setColor(gradient[4]);
        g2d.fillRect(x,y,1,1);
        x++;
        y--;
        g2d.setColor(gradient[4]);
        g2d.fillRect(x,y,1,1);

        g2d.dispose();
    }

    protected static void genBlade(int[] guardCoords, BufferedImage bufferedImage, int minLength, int maxLength, int thickness, Map<String, Color> colorMap) {
        Color highlight = colorMap.get("blade_highlight");
        Color base = colorMap.get("blade_base");
        Color shadow = colorMap.get("blade_shadow");
        Color outline = colorMap.get("outline");

        Graphics2D g2d = bufferedImage.createGraphics();

        int diagonalLength = (int) (Math.random()*(maxLength-minLength)+minLength)+guardCoords[0];
        boolean hasSplit = (int) (Math.random() * 100) > 50 && thickness >=2;

        ArrayList<Integer[]> pixels = new ArrayList<>();

        int y = guardCoords[1];
        for(int x = guardCoords[0]; x<=diagonalLength; x++){
            if (x == diagonalLength){
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

            if(thickness>=1){
                for(int i = 1; i<=thickness; i++){

                    if(i == thickness && x+1 < diagonalLength){
                        pixels.add(new Integer[]{x-1+i, y});
                        pixels.add(new Integer[]{x-1, y-i});
                    }

                    //Taper the end of the blade off during the second to last iteration
                    if(thickness > 1 && i == thickness && x+1 == diagonalLength){
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

    protected static int[] genGuard(int[] coords, BufferedImage bufferedImage, int min, int max, Map<String, Color> colorMap) {
        Color highlight = colorMap.get("blade_highlight");
        Color base = colorMap.get("blade_base");
        Color shadow = colorMap.get("blade_shadow");

        //Generate 1 pixel at a time. Location on each gen is remembered
        Graphics2D g2d = bufferedImage.createGraphics();

        int diagonalLength = (int) (Math.random()*(max-min)+min);
        int[][] positionChanges = {{-1,-1},{0,-1},{-1,0}};
        int[][] mirroredPositionChanges = {{1,1},{1,0},{0,1}};
        ArrayList<Integer[]> pixelCoords = new ArrayList<>();

        int currentX = coords[0];
        int currentY = coords[1];
        int reflectX = coords[0];
        int reflectY = coords[1];

        g2d.setColor(base);
        g2d.fillRect(currentX,currentY,1,1);
        pixelCoords.add(new Integer[]{currentX, currentY,0});

        //Get position of guard pixels
        for(int c = 0;c<diagonalLength;c++){
            //Calculate new pixel
            int selection = (int) (Math.random() * 3);
            currentX += positionChanges[selection][0];
            currentY += positionChanges[selection][1];
            reflectX += mirroredPositionChanges[selection][0];
            reflectY += mirroredPositionChanges[selection][1];

            //We want to collect the location of each colored pixel to outline them later
            pixelCoords.add(new Integer[]{currentX, currentY,0});
            pixelCoords.add(new Integer[]{reflectX, reflectY,1});
        }

        int[][] outlinePos = {{0,-1},{-1,0},{0,1},{1,0}};

        for(int i = 0; i<pixelCoords.size(); i++){
            for(int j = 0; j<outlinePos.length; j++){
                int ox = pixelCoords.get(i)[0];
                int oy = pixelCoords.get(i)[1];
                ox+=outlinePos[j][0];
                oy+=outlinePos[j][1];
                g2d.setColor(colorMap.get("outline"));
                g2d.fillRect(ox,oy,1,1);
            }
        }

        //Color in the guard
        for(int d = 0; d<pixelCoords.size(); d++){
            int px = pixelCoords.get(d)[0];
            int py = pixelCoords.get(d)[1];
            if(pixelCoords.get(d)[2]==0){
                //Color in our pixels
                g2d.setColor(base);
                g2d.fillRect(px,py,1,1);
            }else{
                g2d.setColor(shadow);
                g2d.fillRect(px,py,1,1);
            }
        }

/*        for(int i = 0; i<diagonalLength; i++){
            //Calculate new pixel
            int selection = (int) (Math.random() * 2);
            currentX += positionChanges[selection][0];
            currentY += positionChanges[selection][1];
            reflectX += mirroredPositionChanges[selection][0];
            reflectY += mirroredPositionChanges[selection][1];

            //We want to collect the location of each colored pixel to outline them later
            shapePixelCoords.add(new Integer[]{currentX, currentY});
            shapePixelCoords.add(new Integer[]{reflectX, reflectY});

            //Color in our pixels
            g2d.setColor(base);
            g2d.fillRect(currentX,currentY,1,1);
            g2d.setColor(shadow);
            g2d.fillRect(reflectX,reflectY,1,1);
        }*/

        for(Integer[] i:pixelCoords){
            System.out.println(i[0]+" "+i[1]+" "+i[2]);
        }

        //Color outline
        //genOutline(pixelCoords,bufferedImage,colorMap);

        g2d.dispose();

        return coords;

    }

    /**
     * Generate a grip for weapon
     * @param bufferedImage the image buffer to affect
     * @param min the minimum length (diagonal)
     * @param max the maximum length (diagonal)
     * @param colorMap the color palette to use with generation
     * @return the coordinates of the last pixel generated [x,y]
     */
    protected static int[] genGrip(BufferedImage bufferedImage, int min, int max, Map<String, Color> colorMap){
        Color base = colorMap.get("hilt_base");
        Color shadow = colorMap.get("hilt_shadow");
        Color jewel = colorMap.get("jewel");

        Graphics2D g2d = bufferedImage.createGraphics();

        int diagonalLength = (int) (Math.random()*(max-min)+min);
        int[] finalCoords = null;
        boolean hasPommel = (int) (Math.random() * 100) > 50;

        int y = HEIGHT-1;
        for(int x = 0; x<=diagonalLength; x++){
            if (x == diagonalLength){
                g2d.setColor(base);
                g2d.fillRect(x,y,1,1);
                System.out.println("Final Coors Grip x="+x+" y="+y);
                finalCoords = new int[]{x,y};
                break;
            }

            // Fill in one chunk of the grip
            g2d.setColor(base);
            g2d.fillRect(x,y,1,1);
            g2d.fillRect(x,y-1,1,1);
            g2d.setColor(shadow);
            g2d.fillRect(x+1,y,1,1);

            // We want to see if the grip is tall enough to accomodate the pommel, even if hasPommel true
            if( max > 4 && x==1 && hasPommel){
                g2d.setColor(jewel);
                g2d.fillRect(x,y,1,1);
                g2d.setColor(base);
                g2d.fillRect(x-1,y-1,1,1);
                g2d.setColor(shadow);
                g2d.fillRect(x+1,y+1,1,1);
            }

            y--;
        }

        g2d.dispose();

        return finalCoords;
    }

    /**
     * Generate a spike pattern to be used as the weapon head or blad
     * @param startCoords starting x,y coords
     * @param bufferedImage the image to affect
     * @param colorMap the color palette to use
     */
    protected static void genSpike(int[] startCoords, BufferedImage bufferedImage,Map<String, Color> colorMap){
        Color highlight = colorMap.get("blade_highlight");
        Color base = colorMap.get("blade_base");
        Color shadow = colorMap.get("blade_shadow");

        Graphics2D g2d = bufferedImage.createGraphics();

        int diagonalLength = 4;
        int[] pattern = new int[diagonalLength];
        int[][] pixels = new int[diagonalLength][2];

        int endPosition = diagonalLength+startCoords[0];

        int y = startCoords[1];
        int x = startCoords[0]-1;
        for(int i = 0; x<=endPosition && i<diagonalLength; i++){
            pixels[i][0] = x;
            pixels[i][1] = y;
            pattern[i] = i%2==0?(int) (Math.random()*(5-3)+3):(int) (Math.random()*(3-1)+1);
            y--;
            x++;
        }

        BladePatternBuilder.buildPattern(g2d,pattern,pixels,colorMap);

        g2d.dispose();
    }

    protected static void genMaceHead(int[] startCoords, BufferedImage bufferedImage,Map<String, Color> colorMap){
        Graphics2D g2d = bufferedImage.createGraphics();

        int diagonalLength = 4;
        int[] pattern = new int[diagonalLength];
        int[][] pixels = new int[diagonalLength][2];

        int endPosition = diagonalLength+startCoords[0];

        int y = startCoords[1];
        int x = startCoords[0]-1;
        for(int i = 0; x<=endPosition && i<diagonalLength; i++){
            pixels[i][0] = x;
            pixels[i][1] = y;
            pattern[i] = (int) (Math.random()*(5-3)+3);
            y--;
            x++;
        }

        BladePatternBuilder.buildPattern(g2d,pattern,pixels,colorMap);

        g2d.dispose();
    }

    private static void genOutline(ArrayList<Integer[]> pixels, BufferedImage bufferedImage, Map<String, Color> colorMap){
        //Generate 1 pixel at a time. Location on each gen is remembered
        Graphics2D g2d = bufferedImage.createGraphics();

        int[][] outlinePos = {{0,-1},{-1,0},{0,1},{1,0}};

        for(int i = 0; i<pixels.size(); i++){
            for(int j = 0; j<outlinePos.length; j++){
                int x = pixels.get(i)[0];
                int y = pixels.get(i)[1];
                x+=outlinePos[j][0];
                y+=outlinePos[j][1];
                System.out.println("Contains this set - "+x+" , "+y+" : "+pixels.contains(new Integer[]{x,y}));
                if(!pixels.contains(new Integer[]{x,y})){
                    g2d.setColor(colorMap.get("outline"));
                    g2d.fillRect(x,y,1,1);
                }
            }
        }
    }
}
