import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class WorldTest.
 *
 * @author  Aaron Fink
 * @version September 23, 2019
 */
public class WorldTest {
    /**
     * Default constructor for test class WorldTest
     */
    public WorldTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
    }
    
    @Test
    public void creation() {
        World world = new World();
        assertNotNull(world.agents);
    }
}
