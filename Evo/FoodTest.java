import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class FoodTest.
 *
 * @author  Aaron Fink
 * @version September 27, 2019
 */
public class FoodTest {
    /**
     * Default constructor for test class FoodTest
     */
    public FoodTest() {
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
        Vector position = new Vector(200, 100);
        Food food = new Food(position);
        assertEquals(food.position.x, 200, 0.01);
        assertEquals(food.position.y, 100, 0.01);
    }
}
