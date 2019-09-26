import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class VectorTest.
 *
 * @author  Aaron Fink
 * @version September 25, 2019
 */
public class VectorTest {
    /**
     * Default constructor for test class VectorTest
     */
    public VectorTest() {
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
        Vector vector = new Vector(5.0, 3.0);
        assertEquals(vector.x, 5.0, 0.01);
        assertEquals(vector.y, 3.0, 0.01);
    }
    
    @Test
    public void arithmetic() {
        Vector vector = new Vector(-2.0, 7.0);
        Vector other = new Vector(-1.5, 3.0);
        vector.add(other);
        assertEquals(vector.x, -3.5, 0.01);
        assertEquals(vector.y, 10.0, 0.01);
        vector.mult(3.0);
        assertEquals(vector.x, -10.5, 0.01);
        assertEquals(vector.y, 30.0, 0.01);
        vector.sub(other);
        assertEquals(vector.x, -9.0, 0.01);
        assertEquals(vector.y, 27.0, 0.01);
        vector.div(4.5);
        assertEquals(vector.x, -2.0, 0.01);
        assertEquals(vector.y, 6.0, 0.01);
    }
    
    @Test
    public void normalize() {
        Vector vector = new Vector(3.0, 1.5);
        vector.normalize();
        assertEquals(vector.x, 0.89, 0.01);
        assertEquals(vector.y, 0.45, 0.01);
    }
    
    @Test
    public void getMag() {
        Vector vector = new Vector(7.0, -4.5);
        assertEquals(vector.getMag(), 8.32, 0.01);
    }
    
    @Test
    public void dist() {
        Vector vector = new Vector(-2.0, 7.0);
        Vector other = new Vector(-1.5, 3.0);
        assertEquals(vector.dist(other), 4.03, 0.01);
    }
    
    @Test
    public void equals() {
        Vector vector = new Vector(5.0, 3.0);
        Vector success = new Vector(5.0, 3.0);
        Vector failure = new Vector(4.0, 2.0);
        assertTrue(vector.equals(success));
        assertFalse(vector.equals(failure));
    }
    
    @Test
    public void copy() {
        Vector vector = new Vector(5.0, 3.0);
        Vector copy = vector.copy();
        Vector alteration = new Vector(1.0, 2.0);
        vector.add(alteration);
        assertEquals(copy.x, 5.0, 0.01);
        assertEquals(copy.y, 3.0, 0.01);
        assertFalse(vector.equals(copy));
    }
}
