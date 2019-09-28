import java.util.*;
import javafx.scene.canvas.GraphicsContext;

/**
 * Write a description of class World here.
 *
 * @author Aaron Fink
 * @version September 24
 */
public class World {
    ArrayList<Agent> agents;
    
    /**
     * Constructor for objects of class World
     */
    public World() {
        agents = new ArrayList<Agent>();
        addRandomAnts(10);
        addRandomFoods(50);
    }
    
    public void display(GraphicsContext gc) {
        for(Agent agent : agents) {
            agent.display(gc);
        }
    }
    
    public void update() {
        for(int i = agents.size() - 1; i > -1; i--) {
            Agent agent = agents.get(i);
            agent.update(agents);
            if(agent.shouldBeRemoved()) {
                agents.remove(i);
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
             agents.add(newAnt);
        }
    }
    
    public void addRandomFoods(int foodCount) {
        double evm = 0.1;
        double evd = 0.3;
        
        for(int i = 0; i < foodCount; i++) {
            Food newFood = Food.initializeRandom(evm, evd);
            agents.add(newFood);
        }
    }
}
