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
    
    @Test
    public void processInputs() {
        //double[][][] weights = {{{3, 1}, {4, -3}, {-2, -1}}, {{1, 2, 2}, {4, -1, 4}}};
        double[][][] weights = {{{3, 4, -2}, {1, -3, -1}}, {{1, 4}, {2, -1}, {2, 4}}};
        double[][] biases = {{2, -1, 3}, {-3, 5}};
        Brain testBrain = new Brain(weights, biases);
        
        double[] inputs = {0.8, -0.3};
        double[] outputs = testBrain.processInputs(inputs);
        assertEquals(outputs.length, 2);
        assertEquals(outputs[0], 0.8304123469, 0.000001);
        assertEquals(outputs[1], 0.9999883482, 0.000001);
    }
}
