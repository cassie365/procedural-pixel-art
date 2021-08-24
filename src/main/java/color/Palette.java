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
                colors[0] = shiftColor(color,sat-sat/2,bri+interval*2);   //highlight
                colors[1] = shiftColor(color,sat,bri+interval);       //lighter
                colors[2] = shiftColor(color,sat,bri);                                 //base
                colors[3] = shiftColor(color,sat,bri-interval);        //darker
                colors[4] = shiftColor(color,sat-sat/4,bri-interval*2);    //outline
            }
            else{
                colors[0] = shiftColor(color+shift*2,sat-sat/2,bri+interval*2);   //highlight
                colors[1] = shiftColor(color+shift,sat,bri+interval);       //lighter
                colors[2] = shiftColor(color,sat,bri);                                 //base
                colors[3] = shiftColor(color-shift,sat,bri-interval);       //darker
                colors[4] = shiftColor(color-shift*2,sat-sat/4,bri-interval*2);    //outline
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
                colors[0] = shiftColor(color,sat-sat/2,bri+interval*2);   //highlight
                colors[1] = shiftColor(color,sat,bri+interval);       //lighter
                colors[2] = shiftColor(color,sat,bri);                                 //base
                colors[3] = shiftColor(color+shift,sat,bri-interval);   //highlight
                colors[4] = shiftColor(color+shift*2,sat-sat/4,bri-interval*2);        //lighter
            }else{
                colors[0] = shiftColor(color,sat-sat/2,bri+interval*2);    //highlight
                colors[1] = shiftColor(color,sat,bri+interval);        //lighter
                colors[2] = shiftColor(color,sat,bri);                                 //base
                colors[3] = shiftColor(color-shift,sat,bri-interval);      //darker
                colors[4] = shiftColor(color-shift*2,sat-sat/4,bri-interval*2);    //outline
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
                colors[0] = shiftColor(color,sat-sat/2,bri+interval*2);   //highlight
                colors[1] = shiftColor(color,sat,bri+interval);       //lighter
                colors[2] = shiftColor(color,sat,bri);                                 //base
                colors[3] = shiftColor(color,sat,bri-interval);        //darker
                colors[4] = shiftColor(color,sat-sat/4,bri-interval*2);    //outline
            }
            else if(color < 110){       //Only yellow shift
                colors[0] = shiftColor(color-shift*2,sat-sat/2,bri+interval*2);   //highlight
                colors[1] = shiftColor(color-shift,sat,bri+interval);       //lighter
                colors[2] = shiftColor(color,sat,bri);                                 //base
                colors[3] = shiftColor(color,sat,bri-interval);      //darker
                colors[4] = shiftColor(color,sat-sat/4,bri-interval*2);    //outline
            }
            else{                       //Only cyan shift shift
                colors[0] = shiftColor(color,sat-sat/2,bri+interval*2);    //highlight
                colors[1] = shiftColor(color,sat,bri+interval);        //lighter
                colors[2] = shiftColor(color,sat,bri);                                 //base
                colors[3] = shiftColor(color+shift,sat,bri-interval);   //highlight
                colors[4] = shiftColor(color+shift*2,sat-sat/4,bri-interval*2);        //lighter
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
                colors[0] = shiftColor(color,sat-sat/2,bri+interval*2);    //highlight
                colors[1] = shiftColor(color,sat,bri+interval);       //lighter
                colors[2] = shiftColor(color,sat,bri);                                 //base
                colors[3] = shiftColor(color-shift,sat,bri-interval);        //darker
                colors[4] = shiftColor(color-shift*2,sat-sat/4,bri-interval*2);    //outline
            }else{
                colors[0] = shiftColor(color,sat-sat/2,bri+interval*2);    //highlight
                colors[1] = shiftColor(color,sat,bri+interval);     //lighter
                colors[2] = shiftColor(color,sat,bri);                                 //base
                colors[3] = shiftColor(color+shift,sat,bri-interval);        //darker
                colors[4] = shiftColor(color+shift*2,sat-sat/4,bri-interval*2);    //outline
            }
        }

        /*
         * Blues
         * Darken with brightness only
         * Lighten towards cyan
         */
        if(color>210 && color <= 270){
            if(color>240){  //Lighten towards cyan, no dark shift
                colors[0] = shiftColor(color-shift*2,sat-sat/2,bri+interval*2);    //highlight
                colors[1] = shiftColor(color-shift,sat,bri+interval);       //lighter
                colors[2] = shiftColor(color,sat,bri);                                 //base
                colors[3] = shiftColor(color,sat,bri-interval);        //darker
                colors[4] = shiftColor(color,sat-sat/4,bri-interval*2);   //outline
            }else{          //Darken towards purple, no light shift
                colors[0] = shiftColor(color,sat-sat/2,bri+interval*2);    //highlight
                colors[1] = shiftColor(color,sat,bri+interval);       //lighter
                colors[2] = shiftColor(color,sat,bri);                                 //base
                colors[3] = shiftColor(color+shift,sat,bri-interval);     //darker
                colors[4] = shiftColor(color+shift*2,sat-sat/4,bri-interval*2);    //outline
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
                colors[0] = shiftColor(color,sat-sat/2,bri+interval*2);   //highlight
                colors[1] = shiftColor(color,sat,bri+interval);       //lighter
                colors[2] = shiftColor(color,sat,bri);                                 //base
                colors[3] = shiftColor(color,sat,bri-interval);        //darker
                colors[4] = shiftColor(color,sat-sat/4,bri-interval*2);    //outline
            }
            else if(color < 290){       //Dark shift towards blue
                colors[0] = shiftColor(color,sat-sat/2,bri+interval*2);  //highlight
                colors[1] = shiftColor(color,sat,bri+interval);       //lighter
                colors[2] = shiftColor(color,sat,bri);                                 //base
                colors[3] = shiftColor(color-shift,sat,bri-interval);      //darker
                colors[4] = shiftColor(color-shift*2,sat-sat/4,bri-interval*2);   //outline
            }
            else{//Light shift to red
                colors[0] = shiftColor(color+shift*2,sat-sat/2,bri+interval*2);  //highlight
                colors[1] = shiftColor(color+shift,sat,bri+interval);       //lighter
                colors[2] = shiftColor(color,sat,bri);                               //base
                colors[3] = shiftColor(color,sat,bri-interval);     //darker
                colors[4] = shiftColor(color,sat-sat/4,bri-interval*2);  //outline
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

    private static Color shiftColor(int hue, float sat, float bri){
        float brightness = bri;
        float changeSat = 0;

        System.out.println("brightness: "+bri);

        if(bri<0){
            brightness = 0f;
            changeSat=-bri-.1f;
        }
        else if (bri>1){
            brightness = 1f;
            changeSat=1f-bri+.1f;
        }
        else
            brightness = bri;

        float newSat = sat+changeSat;
        System.out.println("newSat: "+newSat);

        if(newSat<0f)
            newSat = 0f;
        else if (newSat>1f)
            newSat = 1f;


        Color c = Color.getHSBColor(hue/360f,newSat,brightness);
        return c;
    }
}
