import java.util.*;
import javafx.scene.canvas.GraphicsContext;

/**
 * Write a description of class World here.
 *
 * @author Aaron Fink
 * @version September 24
 */
public class World {
    ArrayList<Creature> creatures;
    ArrayList<Food> foods;
    
    /**
     * Constructor for objects of class World
     */
    public World() {
        creatures = new ArrayList<Creature>();
        addRandomAnts(10);
        foods = new ArrayList<Food>();
        addRandomFoods(50);
    }
    
    public void display(GraphicsContext gc) {
        // Display the foods
        for(Food food : foods) {
            food.display(gc);
        }
        
        // Display the creatures
        for(Creature creature : creatures) {
            creature.display(gc);
        }
    }
    
    public void update() {
        // Deal with creatures
        for(int i = creatures.size() - 1; i > -1; i--) {
            Creature creature = creatures.get(i);
            creature.update();
            if(creature.isDead()) {
                creatures.remove(i);
            }
        }
    }
    
    public void addRandomAnts(int antCount) {
        // Define the population
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
        
        // Initialize and add randomized ants
        for(int i = 0; i < antCount; i++) {
             Ant newAnt = Ant.initializeRandom(msmm, msmd, msdm, msdd, rsmm, rsmd, rsdm, rsdd, rm, rd);
             creatures.add(newAnt);
        }
    }
    
    public void addRandomFoods(int foodCount) {
        for(int i = 0; i < foodCount; i++) {
            Food newFood = Food.initializeRandom();
            foods.add(newFood);
        }
    }
}
