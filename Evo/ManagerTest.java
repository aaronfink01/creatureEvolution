import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ViewTest.
 *
 * @author  Aaron Fink
 * @version September 24, 2019
 */
public class ManagerTest {
    /**
     * Default constructor for test class ViewTest
     */
    public ManagerTest() {
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
        try {
            Manager manager = new Manager();
            assertNotNull(manager.world);
        } catch(Exception e) {
            fail();
        }
    }   
}
