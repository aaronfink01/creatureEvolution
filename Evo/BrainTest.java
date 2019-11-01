

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BrainTest.
 *
 * @author  Aaron Fink
 * @version October 31, 2019
 */
public class BrainTest {
    /**
     * Default constructor for test class BrainTest
     */
    public BrainTest() {
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
        double[][][] weights = {{{0.3, 0.7, 0.8}, {0.1, 0.2, 0.6}}, {{0.5}, {0.2}, {0.7}}};
        double[][] biases = {{0.3, -0.2, 0.1}, {-0.6}};
        Brain testBrain = new Brain(weights, biases);
        
        // test weights
        assertEquals(testBrain.weights[0][0][0], 0.3, 0.01);
        assertEquals(testBrain.weights[0][0][1], 0.7, 0.01);
        assertEquals(testBrain.weights[0][0][2], 0.8, 0.01);
        assertEquals(testBrain.weights[0][1][0], 0.1, 0.01);
        assertEquals(testBrain.weights[0][1][1], 0.2, 0.01);
        assertEquals(testBrain.weights[0][1][2], 0.6, 0.01);
        assertEquals(testBrain.weights[1][0][0], 0.5, 0.01);
        assertEquals(testBrain.weights[1][1][0], 0.2, 0.01);
        assertEquals(testBrain.weights[1][2][0], 0.7, 0.01);
        
        // test biases
        assertEquals(testBrain.biases[0][0], 0.3, 0.01);
        assertEquals(testBrain.biases[0][1], -0.2, 0.01);
        assertEquals(testBrain.biases[0][2], 0.1, 0.01);
        assertEquals(testBrain.biases[1][0], -0.6, 0.01);
    }
}
