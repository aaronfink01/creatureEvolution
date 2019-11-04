import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

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
        double energy = 0.9;
        double generation = 1.6;
        Ant ant = new Ant(position, direction, msm, msd, rsm, rsd, radius, energy, generation);
        assertEquals(ant.position.x, 0.0, 0.01);
        assertEquals(ant.position.y, 0.0, 0.01);
        assertEquals(ant.direction, 315.0, 0.01);
        assertEquals(ant.movementSpeedMean, 4.0, 0.01);
        assertEquals(ant.movementSpeedDeviation, 1.0, 0.01);
        assertEquals(ant.rotationSpeedMean, -1.0, 0.01);
        assertEquals(ant.rotationSpeedDeviation, 3.0, 0.01);
        assertEquals(ant.radius, 15.0, 0.01);
        assertEquals(ant.energy, 0.9, 0.01);
        assertEquals(ant.generation, 1.6, 0.01);
    }
    
    @Test
    public void eat() {
        ArrayList<Agent> agents = new ArrayList<Agent>();
        Vector position = new Vector(200, 200);
        Ant ant = new Ant(position, 0, 10, 3, -2, 5, 15, 0.9, 1);
        agents.add(ant);
        Food foodSucceed = new Food(new Vector(190, 185), 0.0144);
        Food foodFail = new Food(new Vector(220, 235), 0.1);
        agents.add(foodSucceed);
        agents.add(foodFail);
        ant.eat(agents);
        assertTrue(foodSucceed.eaten);
        assertFalse(foodFail.eaten);
    }
    
    @Test
    public void constrainEnergy() {
        Vector position = new Vector(200, 200);
        Ant ant = new Ant(position, 0, 10, 3, -2, 5, 15, 0.09, 1);
        assertEquals(ant.energy, 0.09, 0.01);
        ant.energy = 0.05;
        ant.constrainEnergy();
        assertEquals(ant.energy, 0.05, 0.01);
        ant.energy = -0.5;
        ant.constrainEnergy();
        assertEquals(ant.energy, -0.5, 0.01);
        ant.energy = 0.15;
        ant.constrainEnergy();
        assertEquals(ant.energy, 0.09, 0.01);
    }
    
    @Test
    public void constrainToScreen() {
        Vector position = new Vector(0, 0);
        Ant ant = new Ant(position, 0, 10, 3, 5, 2, 15, 0.9, 1);
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

    @Test
    public void testAttemptToReproduce()
    {
    }
}

