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
    }
}
