package trees;

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

    private void createTrunk(){

    }

    private void createBranches(int thickness, int numBranches){

    }
}
