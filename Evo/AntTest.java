import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class AntTest.
 *
 * @author Aaron Fink
 * @version September 24, 2019
 */
public class AntTest {
    /**
     * Default constructor for test class AntTest
     */
    public AntTest() {
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
        Vector position = new Vector(0.0, 0.0);
        Ant ant = new Ant(position);
        assertEquals(ant.getPosition().x, 0.0, 0.01);
        assertEquals(ant.getPosition().y, 0.0, 0.01);
    }
}
