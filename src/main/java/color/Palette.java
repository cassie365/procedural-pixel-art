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

        /*
         * Red
         * Lighten towards yellows
         * Darken towards pinks
         * Middle values 10-350 Don't darken or lighten with hue shift
         */
        if(color <= 30 || color > 330){
            //Do not hue shift
            if(color <=10 || color >= 350){
                colors[0] = Color.getHSBColor(color/360f,sat-interval*2,bri+interval*2);    //highlight
                colors[1] = Color.getHSBColor(color/360f,sat-interval,bri+interval);        //lighter
                colors[2] = Color.getHSBColor(color/360f,sat,bri);                                  //base
                colors[3] = Color.getHSBColor(color/360f,sat+interval,bri-interval);        //darker
                colors[4] = Color.getHSBColor(color/360f,sat+interval*4,bri-interval*4);    //outline
            }
            else{
                colors[0] = Color.getHSBColor((color+shift*2)/360f,sat-interval*2,bri+interval*2);    //highlight
                colors[1] = Color.getHSBColor((color+shift)/360f,sat-interval,bri+interval);        //lighter
                colors[2] = Color.getHSBColor(color/360f,sat,bri);                                  //base
                colors[3] = Color.getHSBColor((color-shift)/360f,sat+interval,bri-interval);        //darker
                colors[4] = Color.getHSBColor((color-shift*2)/360f,sat+interval*4,bri-interval*4);    //outline
            }
        }

        /*
         * Yellow
         * Lighten with brightness only
         * Darken towards green OR reds depending
         */
        if(color > 30 && color <= 90){
            // Darken towards green if leaning towards green, otherwise towards red
            if(color>60){
                colors[0] = Color.getHSBColor(color/360f,sat-interval*2,bri+interval*2);    //highlight
                colors[1] = Color.getHSBColor(color/360f,sat-interval,bri+interval);        //lighter
                colors[2] = Color.getHSBColor(color/360f,sat,bri);                                  //base
                colors[0] = Color.getHSBColor((color+shift)/360f,sat-interval*2,bri+interval*2);    //highlight
                colors[1] = Color.getHSBColor((color+shift*2)/360f,sat-interval,bri+interval);        //lighter
            }else{
                colors[0] = Color.getHSBColor(color/360f,sat-interval*2,bri+interval*2);    //highlight
                colors[1] = Color.getHSBColor(color/360f,sat-interval,bri+interval);        //lighter
                colors[2] = Color.getHSBColor(color/360f,sat,bri);                                  //base
                colors[3] = Color.getHSBColor((color-shift)/360f,sat+interval,bri-interval);        //darker
                colors[4] = Color.getHSBColor((color-shift*2)/360f,sat+interval*4,bri-interval*4);    //outline
            }
        }


        /*
         * Greens
         * Lighten towards yellows
         * Darken towards cyan
         */
        if(color>90 && color <= 150){
            //Do not hue shift
            if(color >=110 && color <= 130){
                colors[0] = Color.getHSBColor(color/360f,sat-interval*2,bri+interval*2);    //highlight
                colors[1] = Color.getHSBColor(color/360f,sat-interval,bri+interval);        //lighter
                colors[2] = Color.getHSBColor(color/360f,sat,bri);                                  //base
                colors[3] = Color.getHSBColor(color/360f,sat+interval,bri-interval);        //darker
                colors[4] = Color.getHSBColor(color/360f,sat+interval*4,bri-interval*4);    //outline
            }
            else if(color < 110){       //Only yellow shift
                colors[0] = Color.getHSBColor((color-shift*2)/360f,sat-interval*2,bri+interval*2);    //highlight
                colors[1] = Color.getHSBColor((color-shift)/360f,sat-interval,bri+interval);        //lighter
                colors[2] = Color.getHSBColor(color/360f,sat,bri);                                  //base
                colors[3] = Color.getHSBColor(color/360f,sat+interval,bri-interval);        //darker
                colors[4] = Color.getHSBColor(color/360f,sat+interval*4,bri-interval*4);    //outline
            }
            else{                       //Only cyan shift shift
                colors[0] = Color.getHSBColor(color/360f,sat-interval*2,bri+interval*2);    //highlight
                colors[1] = Color.getHSBColor(color/360f,sat-interval,bri+interval);        //lighter
                colors[2] = Color.getHSBColor(color/360f,sat,bri);                                  //base
                colors[0] = Color.getHSBColor((color+shift)/360f,sat-interval*2,bri+interval*2);    //highlight
                colors[1] = Color.getHSBColor((color+shift*2)/360f,sat-interval,bri+interval);        //lighter
            }
        }

        /*
         * Cyans
         * Lighten with brightness only
         * Darken towards blues
         */
        if(color>150 && color <= 210){
            // Darken towards green, else darken towards blue
            if(color<=180){
                colors[0] = Color.getHSBColor(color/360f,sat-interval*2,bri+interval*2);    //highlight
                colors[1] = Color.getHSBColor(color/360f,sat-interval,bri+interval);        //lighter
                colors[2] = Color.getHSBColor(color/360f,sat,bri);                                  //base
                colors[3] = Color.getHSBColor((color-shift)/360f,sat+interval,bri-interval);        //darker
                colors[4] = Color.getHSBColor((color-shift*2)/360f,sat+interval*4,bri-interval*4);    //outline
            }else{
                colors[0] = Color.getHSBColor(color/360f,sat-interval*2,bri+interval*2);    //highlight
                colors[1] = Color.getHSBColor(color/360f,sat-interval,bri+interval);        //lighter
                colors[2] = Color.getHSBColor(color/360f,sat,bri);                                  //base
                colors[3] = Color.getHSBColor((color+shift)/360f,sat+interval,bri-interval);        //darker
                colors[4] = Color.getHSBColor((color+shift*2)/360f,sat+interval*4,bri-interval*4);    //outline
            }
        }

        /*
         * Blues
         * Darken with brightness only
         * Lighten towards cyan
         */
        if(color>210 && color <= 270){
            if(color>240){  //Lighten towards cyan, no dark shift
                colors[0] = Color.getHSBColor((color-shift*2)/360f,sat-interval*2,bri+interval*2);    //highlight
                colors[1] = Color.getHSBColor((color-shift)/360f,sat-interval,bri+interval);        //lighter
                colors[2] = Color.getHSBColor(color/360f,sat,bri);                                  //base
                colors[3] = Color.getHSBColor(color/360f,sat+interval,bri-interval);        //darker
                colors[4] = Color.getHSBColor(color/360f,sat+interval*4,bri-interval*4);    //outline
            }else{          //Darken towards purple, no light shift
                colors[0] = Color.getHSBColor(color/360f,sat-interval*2,bri+interval*2);    //highlight
                colors[1] = Color.getHSBColor(color/360f,sat-interval,bri+interval);        //lighter
                colors[2] = Color.getHSBColor(color/360f,sat,bri);                                  //base
                colors[3] = Color.getHSBColor((color+shift)/360f,sat+interval,bri-interval);        //darker
                colors[4] = Color.getHSBColor((color+shift*2)/360f,sat+interval*4,bri-interval*4);    //outline
            }
        }

        /*
         * Pinks
         * Darken towards purple
         * Lighten towards reds
         */
        if(color>270 && color <=330){
            //Do not hue shift
            if(color >=290 && color <= 310){
                colors[0] = Color.getHSBColor(color/360f,sat-interval*2,bri+interval*2);    //highlight
                colors[1] = Color.getHSBColor(color/360f,sat-interval,bri+interval);        //lighter
                colors[2] = Color.getHSBColor(color/360f,sat,bri);                                  //base
                colors[3] = Color.getHSBColor(color/360f,sat+interval,bri-interval);        //darker
                colors[4] = Color.getHSBColor(color/360f,sat+interval*4,bri-interval*4);    //outline
            }
            else if(color < 290){       //Dark shift towards blue
                colors[0] = Color.getHSBColor(color/360f,sat-interval*2,bri+interval*2);    //highlight
                colors[1] = Color.getHSBColor(color/360f,sat-interval,bri+interval);        //lighter
                colors[2] = Color.getHSBColor(color/360f,sat,bri);                                  //base
                colors[3] = Color.getHSBColor((color-shift)/360f,sat+interval,bri-interval);        //darker
                colors[4] = Color.getHSBColor((color-shift*2)/360f,sat+interval*4,bri-interval*4);    //outline
            }
            else{                       //Light shift to red
                colors[0] = Color.getHSBColor((color+shift*2)/360f,sat-interval*2,bri+interval*2);    //highlight
                colors[1] = Color.getHSBColor((color+shift)/360f,sat-interval,bri+interval);        //lighter
                colors[2] = Color.getHSBColor(color/360f,sat,bri);                                  //base
                colors[3] = Color.getHSBColor(color/360f,sat+interval,bri-interval);        //darker
                colors[4] = Color.getHSBColor(color/360f,sat+interval*4,bri-interval*4);    //outline
            }
        }

/*        if(color >=55 && color<= 243){
            colors[0] = Color.getHSBColor((color-shift*2)/360f,sat-interval*2,bri+interval*2);   //highlight
            colors[1] = Color.getHSBColor((color-shift)/360f,sat-interval,bri+interval);         //lighter
            colors[2] = Color.getHSBColor(color/360f,sat,bri);                                             //base
            colors[3] = Color.getHSBColor((color+shift)/360f,sat+interval,bri-interval);         //darker
            colors[4] = Color.getHSBColor((color+shift*2)/360f,sat+interval*4,bri-interval*4);   //outline
        }else{
            colors[0] = Color.getHSBColor((color+shift*2)/360f,sat-interval*2,bri+interval*2);   //highlight
            colors[1] = Color.getHSBColor((color+shift)/360f,sat-interval,bri+interval);         //lighter
            colors[2] = Color.getHSBColor(color/360f,sat,bri);                                             //base
            colors[3] = Color.getHSBColor((color-shift)/360f,sat+interval,bri-interval);         //darker
            colors[4] = Color.getHSBColor((color-shift*2)/360f,sat+interval*4,bri-interval*4);   //outline
        }*/

        return colors;
    }
}
