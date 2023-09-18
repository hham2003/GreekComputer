
//-------------------------------------------------------------------------
/**
 *  Model of the gears on a Greek Computer puzzle
 *
 *  @author Henry Ham
 *  @version (2021.12.30)
 */
public class GreekComputer
{
    //~ Fields ................................................................

    private int[][] baseGear;
    private int[][] firstGear;
    private int[][] secondGear;
    private int[][] thirdGear;
    private int[][] fourthGear;
    private int[][] currentState;
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Initializes a newly created GreekComputer object.
     */
    public GreekComputer()
    {
        super();
        /*# Do any work to initialize your class here. */
        baseGear = new int[][]{{8, 4, 4, 11}, {3, 4, 5, 11}, {4, 6, 6, 14},
            {12, 6, 7, 11}, {2, 3, 8, 14}, {5, 3, 9, 11}, {10, 14, 10, 14},
            {7, 14, 11, 14}, {16, 21, 12, 11}, {8, 21, 13, 14}, {7, 9, 14, 11},
            {8, 9, 15, 14}};
        firstGear = new int[][]{{9, 6, 12, 9}, {0, 0, 3, 0}, {12, 2, 6, 7},
            {0, 13, 0, 14}, {6, 9, 14, 11}, {0, 0, 12, 0}, {10, 17, 3, 8},
            {0, 19, 8, 0}, {10, 3, 9, 16}, {0, 12, 0, 2}, {1, 3, 9, 7},
            {0, 26, 20, 0}};
        secondGear = new int[][]{{0, 10, 15, 9}, {0, 0, 4, 7}, {0, 8, 9, 13},
            {0, 0, 18, 21}, {0, 22, 11, 17}, {0, 0, 26, 4}, {0, 16, 14, 5},
            {0, 0, 1, 0}, {0, 9, 12, 7}, {0, 0, 0, 8}, {0, 5, 21, 9},
            {0, 0, 6, 13}};
        thirdGear = new int[][]{{0, 0, 15, 6}, {0, 0, 0, 0}, {0, 0, 0, 11},
            {0, 0, 14, 11}, {0, 0, 0, 6}, {0, 0, 9, 11}, {0, 0, 0, 0},
            {0, 0, 12, 6}, {0, 0, 0, 17}, {0, 0, 4, 7}, {0, 0, 0, 3}, 
            {0, 0, 7, 0}};
        fourthGear = new int[][]{{0, 0, 0, 10}, {0, 0, 0, 0}, {0, 0, 0, 7},
            {0, 0, 0, 0}, {0, 0, 0, 15}, {0, 0, 0, 0}, {0, 0, 0, 8},
            {0, 0, 0, 0}, {0, 0, 0, 3}, {0, 0, 0, 0}, {0, 0, 0, 6}, 
            {0, 0, 0, 0}};
        currentState = new int[12][4];
    }

    public static void main(String[] args) {
        GreekComputer comp = new GreekComputer();
        comp.printSolution();
    }
    //~ Methods ...............................................................
    /**
     * Print the solution to the puzzle
     */
    public void printSolution()
    {
        this.findSolution();
        for (int i = 0; i < 12; i++)
        {
            System.out.println();
            int first = currentState[i][0];
            int second = currentState[i][1];
            int third = currentState[i][2];
            int fourth = currentState[i][3];
            System.out.print(first + " ");
            System.out.print(second + " ");
            System.out.print(third + " ");
            System.out.print(fourth + " ");
        }
    }
    /**
     * Check every combination of the gears until all columns add to 42
     * 
     * @return Solution to the puzzle
     */
    public int[][] findSolution()
    {
        boolean gotIt = false;
        for (int i = 0; i < 12; i++)
        {
            if (gotIt)
            {
                break;
            }
            this.rotateFirstGear();
            for (int j = 0; j < 12; j++)
            {
                if (gotIt)
                {
                    break;
                }
                this.rotateSecondGear();
                for (int k = 0; k < 12; k++)
                {
                    if (gotIt)
                    {
                        break;
                    }
                    this.rotateThirdGear();
                    for (int l = 0; l < 12; l++)
                    {
                        if (gotIt)
                        {
                            break;
                        }
                        this.rotateFourthGear();
                        this.getCurrentState();
                        if (this.checkSums())
                        {
                            gotIt = true;
                        }
                    }
                }
            }
        }
        return currentState;
    }
    /**
     * Get the current state of the puzzle
     * 
     * @return 2D integer array
     */
    public int[][] getCurrentState()
    {
        for (int i = 0; i < 12; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if (fourthGear[i][j] > 0)
                {
                    currentState[i][j] = fourthGear[i][j];
                }
                else if (thirdGear[i][j] > 0)
                {
                    currentState[i][j] = thirdGear[i][j];
                }
                else if (secondGear[i][j] > 0)
                {
                    currentState[i][j] = secondGear[i][j];
                }
                else if (firstGear[i][j] > 0)
                {
                    currentState[i][j] = firstGear[i][j];
                }
                else
                {
                    currentState[i][j] = baseGear[i][j];
                }
            }
        }
        return currentState;
    }
    /**
     * "Rotate" the first gear one spot counter-clockwise
     */
    public void rotateFirstGear()
    {
        int[] temp = firstGear[0];
        for (int i = 0; i < 11; i++)
        {
            firstGear[i] = firstGear[i + 1];
        }
        firstGear[11] = temp;
    }
    /**
     * "Rotate" the second gear one spot counter-clockwise
     */
    public void rotateSecondGear()
    {
        int[] temp = secondGear[0];
        for (int i = 0; i < 11; i++)
        {
            secondGear[i] = secondGear[i + 1];
        }
        secondGear[11] = temp;
    }
    /**
     * "Rotate" the third gear one spot counter-clockwise
     */
    public void rotateThirdGear()
    {
        int[] temp = thirdGear[0];
        for (int i = 0; i < 11; i++)
        {
            thirdGear[i] = thirdGear[i + 1];
        }
        thirdGear[11] = temp;
    }
    /**
     * "Rotate" the fourth gear one spot counter-clockwise
     */
    public void rotateFourthGear()
    {
        int[] temp = fourthGear[0];
        for (int i = 0; i < 11; i++)
        {
            fourthGear[i] = fourthGear[i + 1];
        }
        fourthGear[11] = temp;
    }
    /**
     * Check the sums of all columns
     * 
     * @return Boolean value
     */
    public boolean checkSums()
    {
        boolean gotIt = true;
        for (int i = 0; i < 12; i++)
        {
            int sum = currentState[i][0] + currentState[i][1] +
                currentState[i][2] + currentState[i][3];
            if (sum != 42)
            {
                gotIt = false;
                break;
            }
        }
        return gotIt;
    }
    /**
     * Get the base gear
     * 
     * @return 2D integer array
     */
    public int[][] getBaseGear()
    {
        return baseGear;
    }
    /**
     * Get the current orientation of the first gear
     * 
     * @return 2D integer array
     */
    public int[][] getFirstGear()
    {
        return firstGear;
    }
    /**
     * Get the current orientation of the second gear
     * 
     * @return 2D integer array
     */
    public int[][] getSecondGear()
    {
        return secondGear;
    }
    /**
     * Get the current orientation of the third gear
     * 
     * @return 2D integer array
     */
    public int[][] getThirdGear()
    {
        return thirdGear;
    }
    /**
     * Get the current orientation of the fourth gear
     * 
     * @return 2D integer array
     */
    public int[][] getFourthGear()
    {
        return fourthGear;
    }
}

