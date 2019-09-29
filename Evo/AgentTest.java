import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class AgentTest.
 *
 * @author  Aaron Fink
 * @version September 29, 2019
 */
public class AgentTest {
    /**
     * Default constructor for test class AgentTest
     */
    public AgentTest() {
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
        Vector position = new Vector(200, 400);
        Agent agent = new Agent(position, 10, 1, 200, 150, 0);
        assertEquals(agent.position.x, 200, 0.01);
        assertEquals(agent.position.y, 400, 0.01);
        assertEquals(agent.radius, 10, 0.01);
        assertEquals(agent.energy, 1, 0.01);
        assertEquals(agent.red, 200, 0.01);
        assertEquals(agent.green, 150, 0.01);
        assertEquals(agent.blue, 0, 0.01);
    }
    
    @Test
    public void shouldBeRemoved() {
        Vector position = new Vector(200, 400);
        Agent agent = new Agent(position, 10, 1, 200, 150, 0);
        assertFalse(agent.shouldBeRemoved());
        agent.eaten = true;
        assertTrue(agent.shouldBeRemoved());
        agent.energy = -0.5;
        assertTrue(agent.shouldBeRemoved());
        agent.eaten = false;
        assertTrue(agent.shouldBeRemoved());
    }
    
    @Test
    public void withinRange() {
        Vector position = new Vector(200, 400);
        Agent agent = new Agent(position, 15, 0.09, 0, 200, 0);
        Vector center = new Vector(180, 400);
        assertTrue(agent.withinRange(center, 10));
        assertFalse(agent.withinRange(center, 3));
    }
    
    @Test
    public void getEnergyValue() {
        Vector position = new Vector(200, 400);
        Agent agent = new Agent(position, 15, 0.09, 0, 200, 0);
        assertEquals(agent.getEnergyValue(), 0.09, 0.01);
    }
    
    @Test
    public void setEaten() {
        Vector position = new Vector(200, 400);
        Agent agent = new Agent(position, 0.09, 15, 0, 200, 0);
        assertFalse(agent.eaten);
        agent.setEaten();
        assertTrue(agent.eaten);
    }
}
