// The Scientist is not meant to contain quality, reusable code.
// This class exists only for setting up experiments.

import java.util.*;

/**
 * Write a description of class Scientist here.
 *
 * @author Aaron Fink
 * @version November 21, 2019
 */
public class Scientist {
    /**
     * Constructor for objects of class Scientist
     */
    public Scientist() {
    }
    
    public void experiment() throws Exception {
        Manager manager = new Manager();
        UpdateHandler handler = new UpdateHandler(manager, false);
        DataHandler.printDataLabels();
        int currentRunFrames = 0;
        while(true) {
            handler.run();
            currentRunFrames++;
            if(currentRunFrames == 2000000) {
                break;
            }
            int antCount = countAnts(manager);
            if(antCount > 100 || antCount < 2) {
                System.out.print("\u000C");
                manager = new Manager();
                handler = new UpdateHandler(manager, false);
                DataHandler.printDataLabels();
                currentRunFrames = 0;
            }
        }
    }
    
    private int countAnts(Manager manager) {
        int ants = 0;
        for(Agent agent : manager.world.agents) {
            if(agent instanceof Ant) {
                ants++;
            }
        }
        return ants;
    }
}
