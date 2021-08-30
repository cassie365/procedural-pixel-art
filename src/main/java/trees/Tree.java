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
    private int numTrunks;
    private int numBranches;
    private int height;

    public Tree(){
        BufferedImage b = new BufferedImage(20,20,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = b.createGraphics();

        drawTree(g,20,20);
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

    private void drawTree(Graphics2D g, int width, int height){
        //Draw trunks, focus on 1 trunk, slight curve of 1. Should extend all the way to top of height
        int x1 = width/2;   //center
        int y1 = height;    //bottom

        int x2 = width/2-1; //center offset 1
        int y2 = -height; //top of image

        int ctrlx = width/2+x2;
        int ctrly = height/2;

        g.setColor(Color.BLACK);
        QuadCurve2D s = new QuadCurve2D.Float();
        s.setCurve(x1,y1,ctrlx,ctrly,x2,y2);
        g.draw(s);
    }

    private void toFile(BufferedImage b, File output){
        try{
            ImageIO.write(b,"png",output);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
