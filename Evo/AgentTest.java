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
        
        Agent myAgent = new Agent(
            position, // new position
            10, // new radius
            1, // new energy
            200, // new red
            150, // new green
            0, // new blue
            1 // new generation
        );
        
        assertEquals(myAgent.position.x, 200, 0.01);
        assertEquals(myAgent.position.y, 400, 0.01);
        assertEquals(myAgent.radius, 10, 0.01);
        assertEquals(myAgent.energy, 1, 0.01);
        assertEquals(myAgent.red, 200, 0.01);
        assertEquals(myAgent.green, 150, 0.01);
        assertEquals(myAgent.blue, 0, 0.01);
        assertEquals(myAgent.generation, 1, 0.01);
    }
    
    @Test
    public void shouldBeRemoved() {
        Vector position = new Vector(200, 400);
        
        Agent myAgent = new Agent(
            position, // new position
            10, // new radius
            1, // new energy
            200, // new red
            150, // new green
            0, // new blue
            1 // new generation
        );
        
        assertFalse(myAgent.shouldBeRemoved());
        myAgent.eaten = true;
        assertTrue(myAgent.shouldBeRemoved());
        myAgent.energy = -0.5;
        assertTrue(myAgent.shouldBeRemoved());
        myAgent.eaten = false;
        assertTrue(myAgent.shouldBeRemoved());
    }
    
    @Test
    public void overlap() {
        Vector position = new Vector(200, 400);
        
        Agent myAgent = new Agent(
            position, // new position
            15, // new radius
            0.09, // new energy
            0, // new red
            200, // new green
            0, // new blue
            1 // new generation
        );
            
        
        Vector centerOtherAgent = new Vector(180, 400);
        assertTrue(myAgent.overlap(centerOtherAgent, 10));
        assertFalse(myAgent.overlap(centerOtherAgent, 3));
        //agent.overlap(null, 10); We thought of this!
        
        // junit 5 supposedly
        // https://www.baeldung.com/junit-assert-exception
        //assertThrows(NullPointerException.class, () -> {agent.overlap(null, 10);});
    }
    
    // from https://www.baeldung.com/junit-assert-exception
    @Test(expected = NullPointerException.class)
    public void overlapNull() {
        Vector position = new Vector(200, 400);
        
        Agent myAgent = new Agent(
            position, // new position
            15, // new radius
            0.09, // new energy
            0, // new red
            200, // new green
            0, // new blue
            1 // new generation
        );
        
        myAgent.overlap(null, 10);
    }
    
    @Test
    public void getEnergyValue() {
        Vector position = new Vector(200, 400);
        
        Agent myAgent = new Agent(
            position, // new position
            15, // new radius
            0.09, // new energy
            0, // new red
            200, // new green
            0, // new blue
            1 // new generation
        );
        
        assertEquals(myAgent.getEnergyValue(), 0.09, 0.01);
    }
    
    @Test
    public void getGeneration() {
        Vector position = new Vector(200, 400);
        
        Agent myAgent = new Agent(
            position, // new position
            15, // new radius
            0.09, // new energy
            0, // new red
            200, // new green
            0, // new blue
            2.4 // new generation
        );
        
        assertEquals(myAgent.getGeneration(), 2.4, 0.01);
    }
    
    @Test
    public void setEaten() {
        Vector position = new Vector(200, 400);
        
        Agent myAgent = new Agent(
            position, // new position
            15, // new radius
            0.09, // new energy
            0, // new red
            200, // new green
            0, // new blue
            2.4 // new generation
        );
        
        assertFalse(myAgent.eaten);
        myAgent.setEaten();
        assertTrue(myAgent.eaten);
    }
}
