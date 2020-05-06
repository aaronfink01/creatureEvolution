import java.util.*;

/**
 * Write a description of class UpdateHandler here.
 *
 * @author Aaron Fink
 * @version September 26, 2019
 */
public class UpdateHandler extends TimerTask {
    Manager manager;
    boolean visualMode = true;
    ArrayList<Agent> agents;
    
    /**
     * Constructor for objects of class UpdateHandler
     */
    public UpdateHandler(Manager manager, boolean visualMode, ArrayList<Agent> agents) {
        this.manager = manager;
        this.visualMode = visualMode;
        this.agents = agents;
    }
    
    public void run() {
        if(visualMode) {
            manager.displayWorld(agents);
        }
        manager.updateWorld();
        //manager.outputData();
        
        /*
        float average = 0;
        float count = 0;
        for(Agent agent : manager.world.agents) {
            if(agent instanceof Turtle) {
                count++;
                average += ((float)((Turtle)agent).foodsEaten) / ((float)((Turtle)agent).framesAlive);
            }
        }
        average /= count;
        System.out.println(average);
        */
    }
}
