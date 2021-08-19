package weapons.util;

import java.awt.*;
import java.util.Map;

/**
 * Class which can be used to draw weapon blade patterns for Graphics2D
 */
public class BladePatternBuilder {
    public static void buildPattern(Graphics2D g2d, int[] pattern, int[][] pixels, Map<String, Color> colorMap){

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
}
