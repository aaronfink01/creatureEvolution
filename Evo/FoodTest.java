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
        double energyValue = 0.1;
        Food food = new Food(position, energyValue);
        assertEquals(food.position.x, 200, 0.01);
        assertEquals(food.position.y, 100, 0.01);
    }
    
    @Test
    public void shouldBeRemoved() {
        Vector position = new Vector(300, 300);
        double energyValue = 0.1;
        Food food = new Food(position, energyValue);
        assertFalse(food.shouldBeRemoved());
        food.eaten = true;
        assertTrue(food.shouldBeRemoved());
    }
    
    @Test
    public void withinRange() {
        Vector position = new Vector(200, 400);
        Food food = new Food(position, 0.09);
        Vector center = new Vector(180, 400);
        assertTrue(food.withinRange(center, 10));
        assertFalse(food.withinRange(center, 3));
    }
    
    @Test
    public void setEaten() {
        Food food = new Food(new Vector(300, 300), 0.05);
        assertFalse(food.eaten);
        food.setEaten();
        assertTrue(food.eaten);
    }
}
