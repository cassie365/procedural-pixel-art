package color;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Palette {
    private Color[] palette;

    /**
     *
     * @param baseColor a color between 1-360
     */
    public Palette(int baseColor,int percentSaturation, int percentBrightness, int shift, float interval){

        palette = initPalette(baseColor, percentSaturation/100f, percentBrightness/100f, shift, interval);
    }

    public Color[] getPalette(){
        return this.palette;
    }

    /**
     * Find aesthetic matches for the base color and return
     * @return
     */
    public Color[] initPalette(int color,float sat, float bri, int shift, float interval){
        //What are the rules for the palette?
        //Lets try a simple color ramp with hue shift from blue <- base -> yellow

        Color[] colors = new Color[5];

        if(color >=55 && color<= 243){
            colors[0] = Color.getHSBColor((color-shift*2)/360f,sat-interval*2,bri+interval*2);   //highlight
            colors[1] = Color.getHSBColor((color-shift)/360f,sat-interval,bri+interval);         //lighter
            colors[2] = Color.getHSBColor(color/360f,sat,bri);                                             //base
            colors[3] = Color.getHSBColor((color+shift)/360f,sat+interval,bri-interval);         //darker
            colors[4] = Color.getHSBColor((color+shift*2)/360f,sat+interval*2,bri-interval*2);   //outline
        }else{
            colors[0] = Color.getHSBColor((color+shift*2)/360f,sat-interval*2,bri+interval*2);   //highlight
            colors[1] = Color.getHSBColor((color+shift)/360f,sat-interval,bri+interval);         //lighter
            colors[2] = Color.getHSBColor(color/360f,sat,bri);                                             //base
            colors[3] = Color.getHSBColor((color-shift)/360f,sat+interval,bri-interval);         //darker
            colors[4] = Color.getHSBColor((color-shift*2)/360f,sat+interval*2,bri-interval*2);   //outline
        }

        return colors;
    }
}
