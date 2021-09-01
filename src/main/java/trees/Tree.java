package trees;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Model representing a basic tree
 * Generates 1-3 main trunk connected at center point
 * - Then picks a point on each trunk to generate 1-2 branches.
 * - Each branch then picks a point to generate 1-2 subbranches.
 *
 * Trunk-branch thickness rules are as follows:
 * Trunk = given val
 * Branch lvl 1 = Trunk-Trunk/3
 * Branch lvl 2 = Trunk-Trunk/2
 *
 * Thickness is achieves by offsetting the curve by 1 on each side evenly if possible
 */
public class Tree {

    //These attributes may be useful for modifying trees conditionally in more complex scenes
    private int trunkThickness;
    private int trunkHeight;
    private int numTrunks;
    private int numBranches;
    private int height;
    private final int IMG_WIDTH = 31;
    private final int IMG_HEIGHT = 64;

    public Tree(){
        trunkThickness = 3;
        trunkHeight = 20;

        BufferedImage b = new BufferedImage(IMG_WIDTH,IMG_HEIGHT,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = b.createGraphics();

        drawTree(g);
        g.dispose();

        toFile(b,new File("tree/trunk-test.png"));

    }

    public Tree(int trunkThickness){
        this.trunkThickness = trunkThickness;

        //Randomize unitialized values

        //Call method to build this tree.
    }

    /**
     * Constructor allows for full control of tree generation with the exception
     * of branch origin points on trunk and branch lvl1
     * @param trunkThickness the desired thickness of trunk
     * @param numTrunks the number of trunks to generate
     * @param numBranches the number of branches per trunk
     * @param height the maximum -y value of this tree, cieling being 0
     */
    public Tree(int trunkThickness, int numTrunks, int numBranches, int height){
        this.trunkThickness = trunkThickness;
        this.numTrunks = numTrunks;
        this.numBranches = numBranches;
        this.height = height;
    }

    private void drawTree(Graphics2D g){
        drawTrunk(g);
    }

    private void toFile(BufferedImage b, File output){
        try{
            ImageIO.write(b,"png",output);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void drawTrunk(Graphics2D g){
        int middle = IMG_WIDTH/2+1;
        int layerWidth = trunkThickness;
        int x = middle-trunkThickness;

        //fill with white to help us see
        g.setColor(Color.white);
        g.fillRect(0,0,IMG_WIDTH,IMG_HEIGHT);

        // Start with drawing the base to max
        g.setColor(Color.green);
        g.fillRect(x,IMG_HEIGHT-trunkHeight,layerWidth,trunkHeight);

        layerWidth+=2;
        x--;

        //Draw layer 2
        int l2_height = getTrunkLayerHeight(trunkHeight,1);
        g.setColor(Color.cyan);
        g.fillRect(x,IMG_HEIGHT-l2_height,layerWidth,l2_height);

        layerWidth+=2;
        x--;

        if(l2_height>trunkHeight/3){
            //Draw layer3
            int l3_height = getTrunkLayerHeight(l2_height,1);
            g.setColor(Color.blue);
            g.fillRect(x,IMG_HEIGHT-l3_height,layerWidth,l3_height);
        }
    }

    private int getTrunkLayerHeight(int base,int min){
        return (int) (Math.random() * ((base-base/3)-min+1)+min);
    }
}
