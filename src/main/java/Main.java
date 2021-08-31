import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static Map<String,String> locale = new HashMap<>();
    public static void main(String[] args) throws IOException {
        Map<String,Color> colorMap = new HashMap();
        colorMap.put("blade_highlight",Color.decode("#b8b5b9"));
        colorMap.put("blade_base",Color.decode("#868188"));
        colorMap.put("blade_shadow",Color.decode("#646365"));
        colorMap.put("hilt_base",Color.decode("#a77b5b"));
        colorMap.put("hilt_shadow",Color.decode("#80493a"));
        colorMap.put("jewel",Color.decode("#2555a6"));
        colorMap.put("outline",Color.decode("#45444f"));



        genSet("wheat", wheatColor());
        genSet("carrot", carrotColor());
        genSet("potato", potatoColor());
        genSet("beetroot", beetrootColor());
        //s
    }

    public static void processJson(String filename) throws FileNotFoundException {
        try {
            // create Gson instance
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("json_files/base.json"));

            // convert JSON file to map
            Map<String, Map<String, String>> map = gson.fromJson(reader, Map.class);
            Map<String,String> textures = new HashMap<>();
            textures.put("layer0","edibleweapons:item/"+filename);

            map.put("textures",textures);

            // close reader
            reader.close();

            // create a writer
            Writer writer = new FileWriter("json_files/"+filename+".json");

            // convert map to JSON File
            gson.toJson(map, writer);

            // close the writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void makeRecipe(String material,String type){
        try {
            // create Gson instance
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("recipies/base/"+type+"_base.json"));

            // convert JSON file to map
            Map<String, Object> map = gson.fromJson(reader, Map.class);
            Map<String,Map<String,String>> keymap = (Map<String, Map<String, String>>) map.get("key");
            Map<String,String> result = (Map<String, String>) map.get("result");
            Map<String,String> item = keymap.get("X");
            item.put("tag",getItemKey(material));
            result.put("item","edibleweapons:"+material.toLowerCase()+"_"+type.toLowerCase());
            keymap.put("X",item);
            map.put("key",keymap);
            map.put("result",result);
            System.out.println(item.get("tag"));
            System.out.println(result.get("item"));

            // close reader
            reader.close();

            // create a writer
            Writer writer = new FileWriter("recipies/"+material.toLowerCase()+"_"+type.toLowerCase()+".json");

            // convert map to JSON File
            gson.toJson(map, writer);

            // close the writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private static String getItemKey(String material){
        String result = "invalid";
        switch(material){
            /*
                "#forge:crops/beetroot",
                "#forge:crops/carrot",
                "#forge:crops/nether_wart",
                "#forge:crops/potato",
                "#forge:crops/wheat"
             */
            case "wheat":
                result = "forge:crops/wheat";
                break;
            case "carrot":
                result = "forge:crops/carrot";
                break;
            case "potato":
                result = "forge:crops/potato";
                break;
            case "beetroot":
                result = "forge:crops/beetroot";
                break;
            default:
                System.out.println("UNKNOWN");
        }
        return result;
    }

    public static Map<String,Color> wheatColor(){
        Map<String,Color> colorMap = new HashMap();
        colorMap.put("blade_highlight",Color.decode("#90866a"));
        colorMap.put("blade_base",Color.decode("#685f4e"));
        colorMap.put("blade_shadow",Color.decode("#4e493a"));
        colorMap.put("hilt_base",Color.decode("#825b34"));
        colorMap.put("hilt_shadow",Color.decode("#643621"));
        colorMap.put("jewel",Color.decode("#ffcc2c"));
        colorMap.put("outline",Color.decode("#565138"));
        return colorMap;
    }
    public static Map<String,Color> carrotColor(){
        Map<String,Color> colorMap = new HashMap();
        colorMap.put("blade_highlight",Color.decode("#b88249"));
        colorMap.put("blade_base",Color.decode("#865d35"));
        colorMap.put("blade_shadow",Color.decode("#644728"));
        colorMap.put("hilt_base",Color.decode("#a75823"));
        colorMap.put("hilt_shadow",Color.decode("#803517"));
        colorMap.put("jewel",Color.decode("#33be30"));
        colorMap.put("outline",Color.decode("#752802"));
        return colorMap;
    }

    public static Map<String,Color> potatoColor(){
        Map<String,Color> colorMap = new HashMap();
        colorMap.put("blade_highlight",Color.decode("#a78f69"));
        colorMap.put("blade_base",Color.decode("#79664d"));
        colorMap.put("blade_shadow",Color.decode("#5b4e39"));
        colorMap.put("hilt_base",Color.decode("#976134"));
        colorMap.put("hilt_shadow",Color.decode("#743a21"));
        colorMap.put("jewel",Color.decode("#bdd855"));
        colorMap.put("outline",Color.decode("#6d3701"));
        return colorMap;
    }

    public static Map<String,Color> beetrootColor(){
        Map<String,Color> colorMap = new HashMap();
        colorMap.put("blade_highlight",Color.decode("#8e5458"));
        colorMap.put("blade_base",Color.decode("#683c41"));
        colorMap.put("blade_shadow",Color.decode("#4d2e30"));
        colorMap.put("hilt_base",Color.decode("#81392c"));
        colorMap.put("hilt_shadow",Color.decode("#63221c"));
        colorMap.put("jewel",Color.decode("#3a6a68"));
        colorMap.put("outline",Color.decode("#4a1d17"));
        return colorMap;
    }

    public static void genSet(String type, Map<String, Color> colorMap) throws IOException {
        genSword(type,1,colorMap);
        genMace(type,1,colorMap);
        genStaff(type,1,colorMap);
        genDagger(type,1,colorMap);
        genSpear(type,1,colorMap);
        genRapier(type,1,colorMap);
        genMorningstar(type,1,colorMap);
    }

    public static void genSword(String type,int amt, Map<String, Color> colorMap) throws IOException {
        for(int i = 0; i<amt; i++){

            int width = 16;
            int height = 16;
            int[][] pixelMap = new int[width][height];

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

            int[] hiltCoords = genHilt(bufferedImage,5,6,colorMap);

            int guardLoc = genGuard(hiltCoords,bufferedImage,3,3,colorMap);

            genBlade(guardLoc, bufferedImage,8,10,1,colorMap);

            colorRamp(bufferedImage,width,height);

            File file;
            if(amt==1){
                file = new File("icons/"+type+"_long_sword.png");
            } else{
                file = new File("icons/"+type+"_long_sword_"+i+".png");
            }
            ImageIO.write(bufferedImage, "png", file);
        }

        processJson(type+"_long_sword");
        makeRecipe(type,"long_sword");
    }

    public static void genDagger(String type,int amt, Map<String, Color> colorMap) throws IOException {

        for(int i = 0; i<amt; i++){

            int width = 16;
            int height = 16;
            int[][] pixelMap = new int[width][height];

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

            int[] hiltCoords = genHilt(bufferedImage,4,4,colorMap);

            int guardLoc = genGuard(hiltCoords,bufferedImage,1,3,colorMap);

            genBlade(guardLoc, bufferedImage,4,6,1,colorMap);

            colorRamp(bufferedImage,width,height);
            File file;
            if(amt==1){
                file = new File("icons/"+type+"_dagger.png");
            } else{
                file = new File("icons/"+type+"_dagger_"+i+".png");
            }

            ImageIO.write(bufferedImage, "png", file);
        }

        processJson(type+"_dagger");
        makeRecipe(type,"dagger");
    }

    public static void genRapier(String type,int amt, Map<String, Color> colorMap) throws IOException {

        for(int i = 0; i<amt; i++){

            int width = 16;
            int height = 16;
            int[][] pixelMap = new int[width][height];

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

            int[] hiltCoords = genHilt(bufferedImage,5,5,colorMap);

            int guardLoc = genGuard(hiltCoords,bufferedImage,4,4,colorMap);

            genBlade(guardLoc, bufferedImage,12,12,0,colorMap);

            colorRamp(bufferedImage,width,height);

            File file;
            if(amt==1){
                file = new File("icons/"+type+"_rapier.png");
            } else{
                file = new File("icons/"+type+"_rapier_"+i+".png");
            }

            ImageIO.write(bufferedImage, "png", file);
        }

        processJson(type+"_rapier");
        makeRecipe(type,"rapier");
    }

    public static void genStaff(String type,int amt, Map<String, Color> colorMap) throws IOException {

        for(int i = 0; i<amt; i++){

            int width = 16;
            int height = 16;
            int[][] pixelMap = new int[width][height];

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

            int[] hiltCoords = genHilt(bufferedImage,12,14,colorMap);

            colorRamp(bufferedImage,width,height);

            File file;
            if(amt==1){
                file = new File("icons/"+type+"_staff.png");
            } else{
                file = new File("icons/"+type+"_staff_"+i+".png");
            }

            ImageIO.write(bufferedImage, "png", file);
        }

        processJson(type+"_staff");
        makeRecipe(type,"staff");
    }

    public static void genMace(String type,int amt, Map<String, Color> colorMap) throws IOException {

        for(int i = 0; i<amt; i++){

            int width = 16;
            int height = 16;
            int[][] pixelMap = new int[width][height];

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

            int[] hiltCoords = genHilt(bufferedImage,8,10,colorMap);

            genMaceHead(hiltCoords[0], bufferedImage,colorMap);

            colorRamp(bufferedImage,width,height);

            File file;
            if(amt==1){
                file = new File("icons/"+type+"_mace.png");
            } else{
                file = new File("icons/"+type+"_mace_"+i+".png");
            }
            ImageIO.write(bufferedImage, "png", file);
        }
        processJson(type+"_mace");
        makeRecipe(type,"mace");
    }

    public static void genMorningstar(String type,int amt, Map<String, Color> colorMap) throws IOException {

        for(int i = 0; i<amt; i++){

            int width = 16;
            int height = 16;
            int[][] pixelMap = new int[width][height];

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

            int[] hiltCoords = genHilt(bufferedImage,8,10,colorMap);

            genSpike(hiltCoords[0], bufferedImage,colorMap);

            colorRamp(bufferedImage,width,height);

            File file;
            if(amt==1){
                file = new File("icons/"+type+"_morningstar.png");
            } else{
                file = new File("icons/"+type+"_morningstar_"+i+".png");
            }

            ImageIO.write(bufferedImage, "png", file);
        }
        processJson(type+"_morningstar");
        makeRecipe(type,"morningstar");
    }

    public static void genSpear(String type,int amt, Map<String, Color> colorMap) throws IOException {
        for(int i = 0; i<amt; i++){

            int width = 16;
            int height = 16;
            int[][] pixelMap = new int[width][height];

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

            int[] hiltCoords = genHilt(bufferedImage,12,14,colorMap);

            int guardLoc = genGuard(hiltCoords,bufferedImage,2,3,colorMap);

            genSpike(guardLoc, bufferedImage,colorMap);

            colorRamp(bufferedImage,width,height);

            File file;
            if(amt==1){
                file = new File("icons/"+type+"_spear.png");
            } else{
                file = new File("icons/"+type+"_spear_"+i+".png");
            }

            ImageIO.write(bufferedImage, "png", file);
        }
        processJson(type+"_spear");
        makeRecipe(type,"spear");
    }


    private static void genBlade(int guardLoc, BufferedImage bufferedImage, int min, int max, int width, Map<String, Color> colorMap) {
        Color highlight = colorMap.get("blade_highlight");
        Color base = colorMap.get("blade_base");
        Color shadow = colorMap.get("blade_shadow");

        Graphics2D g2d = bufferedImage.createGraphics();

        int limit = (int) (Math.random()*(max-min)+min)+guardLoc;
        System.out.println(limit);
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
                    g2d.setColor(new Color(5,5,5,0));
            }else{
                g2d.setColor(base);
            }

            g2d.fillRect(x-1,y,1,1);

            if(width>=1){
                for(int i = 1; i<=width; i++){
                    if(width>1 && i==width && x+1 == limit)
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
        System.out.println(limit);
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

    public static int[] genHilt(BufferedImage bufferedImage, int min, int max, Map<String, Color> colorMap){
        Color base = colorMap.get("hilt_base");
        Color shadow = colorMap.get("hilt_shadow");
        Color jewel = colorMap.get("jewel");

        Graphics2D g2d = bufferedImage.createGraphics();

        int limit = (int) (Math.random()*(max-min)+min);
        int[] result = null;
        boolean hasJewel = (int) (Math.random() * 100) > 50;

        g2d.setColor(new Color(5,5,5,0));
        g2d.fillRect(0,0,15,15);

        int y = 15;
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

    public static void genSpike(int start, BufferedImage bufferedImage,Map<String, Color> colorMap){
        Color highlight = colorMap.get("blade_highlight");
        Color base = colorMap.get("blade_base");
        Color shadow = colorMap.get("blade_shadow");

        Graphics2D g2d = bufferedImage.createGraphics();

        int numBases = 4;
        int[] pattern = new int[numBases];
        int[][] pixels = new int[numBases][2];

        int limit = numBases+start;

        int y = 15-start;
        int place = 0;
        for(int x = start-1; x<=limit && place<numBases; x++){
            pixels[place][0] = x;
            pixels[place][1] = y;
            pattern[place] = place%2==0?(int) (Math.random()*(5-3)+3):(int) (Math.random()*(3-1)+1);
            System.out.println("x= "+x+" y="+y+" pat="+pattern[place]);
            place++;
            y--;
        }

        buildPattern(g2d,pattern,pixels,colorMap);

        g2d.dispose();
    }

    public static void genMaceHead(int start, BufferedImage bufferedImage,Map<String, Color> colorMap){
        Color highlight = colorMap.get("blade_highlight");
        Color base = colorMap.get("blade_base");
        Color shadow = colorMap.get("blade_shadow");

        Graphics2D g2d = bufferedImage.createGraphics();

        int numBases = 4;
        int[] pattern = new int[numBases];
        int[][] pixels = new int[numBases][2];

        int limit = numBases+start;

        int y = 15-start;
        int place = 0;
        for(int x = start-1; x<=limit && place<numBases; x++){
            pixels[place][0] = x;
            pixels[place][1] = y;
            pattern[place] = (int) (Math.random()*(5-3)+3);
            System.out.println("x= "+x+" y="+y+" pat="+pattern[place]);
            place++;
            y--;
        }

        buildPattern(g2d,pattern,pixels,colorMap);

        g2d.dispose();
    }

    public static void genSpearHead(int start, BufferedImage bufferedImage, Map<String, Color> colorMap){
        Color highlight = colorMap.get("blade_highlight");
        Color base = colorMap.get("blade_base");
        Color shadow = colorMap.get("blade_shadow");

        Graphics2D g2d = bufferedImage.createGraphics();

        int limit = 6+start;

        int y = 15-start;
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

    private static void colorRamp(BufferedImage bufferedImage, int width, int height){
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
