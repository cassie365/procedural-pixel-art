import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GenBezier {
    public static void main(String[] args) throws IOException {
        BufferedImage b = new BufferedImage(20,20,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = b.createGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0,20,20);

        g.setColor(Color.BLACK);

        //Find middle of image
        int center = 20/2;

        int numBranches = 3;

        //The inner padding right and left of tree branches in pixels
        int padding = 4;

        //Find the max branch pixel x val
        int numPixelsX = 20-padding*2;

        //range of where a branch may move
        int branchCell = numPixelsX/numBranches;

        int currentCell = 0+padding;
        for(int i = -1; i<2; i++){
            drawShrubBranch(g,center,20,currentCell,currentCell+branchCell,18);
            currentCell+=branchCell;
        }

        File f = new File("tree/test.png");

        ImageIO.write(b,"png",f);
    }

    public static void drawShrubBranch(Graphics2D g, int startx, int starty, int minEndX,int maxEndX, int height){
        g.setColor(Color.BLACK);
        int x1 = startx;
        int y1 = starty;
        int x2 = (int) (Math.random()*(maxEndX-minEndX)+minEndX);
        int y2 = 20-height;
        int curveFactorX = (int) (Math.random()*(6-3)+3);
        int curveFactorY = (int) (Math.random()*(10-6)+6);
        int ctrlx;
        if(x2<startx){
            ctrlx=x2-curveFactorX;
        }else{
            ctrlx=x2+curveFactorX;
        }
        int ctrly = 20-curveFactorY;

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
