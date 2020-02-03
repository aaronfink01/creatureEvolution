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
    
    public void megaExperiment() throws Exception {
        System.out.println("Desnity,Radius");
        for(int foodSpread = 1; foodSpread < 50; foodSpread++) {
            experiment((double)foodSpread);
        }
    }
    
    
    
    /*
     * Because the experiment starts again when either
     *      1) There is only a single creature left (which cannot mutate further)
     *      or 2) There are over a hundred creatures (which will slow down the simulation dramatically)
     * the results of the experiment are inherently biased.
     * This may be a problem, or maybe not.
     */
    public void experiment(double foodSpread) throws Exception {
        double foodQuantity = 1.0; // Not experimenting with this right now.
        
        Manager manager = new Manager(foodSpread, foodQuantity);
        UpdateHandler handler = new UpdateHandler(manager, false);
        //DataHandler.printDataLabels();
        int currentRunFrames = 0;
        while(true) {
            handler.run();
            currentRunFrames++;
            if(currentRunFrames == 1000000) {
                double averageAntRadius = averageAntRadius(manager);
                System.out.println(Integer.toString((int)foodSpread) + "," + Double.toString(averageAntRadius));
                break;
            } else if(currentRunFrames % 1000 == 0) {
                //System.out.println(100 * currentRunFrames / 1000000.0);
            }
            int antCount = countAnts(manager);
            if(antCount > 100 || antCount < 2) {
                manager = new Manager(foodSpread, foodQuantity);
                handler = new UpdateHandler(manager, false);
                //DataHandler.printDataLabels();
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
    
    private double averageAntRadius(Manager manager) {
        double radiusSum = 0;
        int antCount = 0;
        for(Agent agent : manager.world.agents) {
            if(agent instanceof Ant) {
                radiusSum += agent.radius;
                antCount++;
            }
        }
        return radiusSum / antCount;
    }
}
