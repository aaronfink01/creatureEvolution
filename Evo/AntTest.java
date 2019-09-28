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
    
    @Test
    public void constrainToScreen() {
        Vector position = new Vector(0, 0);
        Ant ant = new Ant(position, 0, 10, 3, 5, 2, 15);
        ant.constrainToScreen();
        assertEquals(position.x, 15, 0.01);
        assertEquals(position.y, 15, 0.01);
        
        position.x = 600;
        position.y = 0;
        ant.constrainToScreen();
        assertEquals(position.x, 585, 0.01);
        assertEquals(position.y, 15, 0.01);
        
        position.x = 600;
        position.y = 600;
        ant.constrainToScreen();
        assertEquals(position.x, 585, 0.01);
        assertEquals(position.y, 585, 0.01);
        
        position.x = 0;
        position.y = 600;
        ant.constrainToScreen();
        assertEquals(position.x, 15, 0.01);
        assertEquals(position.y, 585, 0.01);
        
        position.x = 300;
        position.y = 300;
        ant.constrainToScreen();
        assertEquals(position.x, 300, 0.01);
        assertEquals(position.y, 300, 0.01);
    }
    
    @Test
    public void isDead() {
        Vector position = new Vector(300, 300);
        Ant ant = new Ant(position, 0, 10, 3, 5, 2, 15);
        assertFalse(ant.isDead());
        ant.energy = -0.1;
        assertTrue(ant.isDead());
    }
    
    @Test
    public void initializeRandom() {
        double msmm = 3.0;
        double msmd = 1.0;
        double msdm = 1.0;
        double msdd = 0.5;
        double rsmm = 0.0;
        double rsmd = 5.0;
        double rsdm = 7.0;
        double rsdd = 5.0;
        double rm = 15.0;
        double rd = 5.0;
        Ant ant = Ant.initializeRandom(msmm, msmd, msdm, msdd, rsmm, rsmd, rsdm, rsdd, rm, rd);
        assertNotNull(ant);
    }
}
