import student.micro.*;
import static org.assertj.core.api.Assertions.*;

// -------------------------------------------------------------------------
/**
 *  Test class for GreekComputer class
 *
 *  @author Henry Ham
 *  @version (2021.12.30)
 */
public class GreekComputerTest
    extends TestCase
{
    //~ Fields ................................................................
    private GreekComputer model;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new GreekComputerTest test object.
     */
    public GreekComputerTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        /*# Insert your own setup code here */
        model = new GreekComputer();
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    public void testGears()
    {
        int[][] base = model.getBaseGear();
        int test = base[6][2];
        
        assertThat(test).isEqualTo(10);
    }
    
    public void testGetCurrentState()
    {
        int[][] state = model.getCurrentState();
        int test = state[0][0];
        int test2 = state[1][3];
        int test3 = state[11][1];
        
        assertThat(test).isEqualTo(9);
        assertThat(test2).isEqualTo(7);
        assertThat(test3).isEqualTo(26);
    }
    
    public void testRotateFirstGear()
    {
        model.rotateFirstGear();
        int[][] first = model.getFirstGear();
        int test = first[11][0];
        
        assertThat(test).isEqualTo(9);
    }
}
