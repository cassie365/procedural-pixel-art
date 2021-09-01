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
        numBranches = 4;

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
        //The inner padding right and left of tree branches in pixels
        int padding = 4;

        //Find the max branch pixel x val
        int numPixelsX = IMG_WIDTH-padding*2;

        //range of where a branch may move
        int branchCell = numPixelsX/numBranches;


        drawTrunk(g);
        int currentCell = 0+padding;
        for(int i = -1; i<2; i++){
            drawBranches(g,IMG_WIDTH/2,IMG_HEIGHT-trunkHeight,currentCell,currentCell+branchCell,20);
            currentCell+=branchCell;
        }
    }

    private void toFile(BufferedImage b, File output){
        try{
            ImageIO.write(b,"png",output);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Draws the trunk of the tree.
     * TODO: Refactor to remove repeated code
     * @param g
     */
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

    /**
     * Method which draws branches on tree
     */
    private void drawBranches(Graphics2D g, int startx, int starty, int minEndX,int maxEndX, int height){
        g.setColor(Color.BLACK);
        int middle = IMG_HEIGHT/2+1;
        int x1 = startx;
        int y1 = starty;
        int x2 = (int) (Math.random()*(maxEndX-minEndX)+minEndX);
        int y2 = IMG_HEIGHT-trunkHeight-height; //IMG_HEIGHT-height creates willows branches
        int curveFactorX = (int) (Math.random()*(6-3)+3);
        int curveFactorY = (int) (Math.random()*(10-6)+6);
        int ctrlx;
        if(x2<startx){
            ctrlx=x2-curveFactorX;
        }else{
            ctrlx=x2+curveFactorX;
        }
        int ctrly = IMG_HEIGHT-trunkHeight-curveFactorY;

        QuadCurve2D s = new QuadCurve2D.Float();
        s.setCurve(x1,y1,ctrlx,ctrly,x2,y2);

        //Select x
        int newPoint = x1-1;
        int branchy = y1;
        for(int i = 0; i<20; i++){
            if(s.contains(newPoint,i)){
                branchy = i;
            }
        }
        g.draw(s);

        shade(g,s,x1,y1,ctrlx,ctrly,x2,y2);

        g.setColor(Color.BLACK);
        s.setCurve(newPoint,branchy,ctrlx,ctrly,x2+4,y2);
        g.draw(s);

        shade(g,s,newPoint,branchy,ctrlx,ctrly,x2+4,y2);
    }

    private int getTrunkLayerHeight(int base,int min){
        return (int) (Math.random() * ((base-base/3)-min+1)+min);
    }

    public static void shade(Graphics2D g, QuadCurve2D s, int x1, int y1, int ctrlx, int ctrly, int x2, int y2){
        highlight(g,s,x1,y1,ctrlx,ctrly,x2,y2);
        shadow(g,s,x1,y1,ctrlx,ctrly,x2,y2);
    }

    public static void highlight(Graphics2D g, QuadCurve2D s, int x1, int y1, int ctrlx, int ctrly, int x2, int y2){
        g.setColor(Color.RED);
        s.setCurve(x1-1,y1,ctrlx-1,ctrly,x2-1,y2);
        g.draw(s);
    }

    public static void shadow(Graphics2D g, QuadCurve2D s, int x1, int y1, int ctrlx, int ctrly, int x2, int y2){
        g.setColor(Color.BLUE);
        s.setCurve(x1+1,y1,ctrlx+1,ctrly,x2+1,y2);
        g.draw(s);
    }
}
