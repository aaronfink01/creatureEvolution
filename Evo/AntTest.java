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
        double direction = 315.0;
        double msm = 4.0;
        double msd = 1.0;
        double rsm = -1.0;
        double rsd = 3.0;
        double radius = 15.0;
        Ant ant = new Ant(position, direction, msm, msd, rsm, rsd, radius);
        assertEquals(ant.position.x, 0.0, 0.01);
        assertEquals(ant.position.y, 0.0, 0.01);
        assertEquals(ant.direction, 315.0, 0.01);
        assertEquals(ant.movementSpeedMean, 4.0, 0.01);
        assertEquals(ant.movementSpeedDeviation, 1.0, 0.01);
        assertEquals(ant.rotationSpeedMean, -1.0, 0.01);
        assertEquals(ant.rotationSpeedDeviation, 3.0, 0.01);
        assertEquals(ant.radius, 15.0, 0.01);
    }
}
