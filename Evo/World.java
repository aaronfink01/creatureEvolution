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
        
        // This setup is temporary
        agents.add(new Ant(new Vector(200.0, 100.0), 315.0, 1.0));
        agents.add(new Ant(new Vector(435.0, 250.0), 270.0, 0.5));
    }
    
    public void display(GraphicsContext gc) {
        for(Agent agent : agents) {
            agent.display(gc);
        }
    }
    
    public void update() {
        for(Agent agent : agents) {
            agent.update();
        }
    }
}
