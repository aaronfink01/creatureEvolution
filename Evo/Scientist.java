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
        for(int foodDensity = 1; foodDensity < 50; foodDensity++) {
            experiment((double)foodDensity);
        }
    }
    
    public void experiment(double foodDensity) throws Exception {
        Manager manager = new Manager(foodDensity);
        UpdateHandler handler = new UpdateHandler(manager, false);
        //DataHandler.printDataLabels();
        int currentRunFrames = 0;
        while(true) {
            handler.run();
            currentRunFrames++;
            if(currentRunFrames == 1000000) {
                double averageAntRadius = averageAntRadius(manager);
                System.out.println(Integer.toString((int)foodDensity) + "," + Double.toString(averageAntRadius));
                break;
            } else if(currentRunFrames % 1000 == 0) {
                //System.out.println(100 * currentRunFrames / 1000000.0);
            }
            int antCount = countAnts(manager);
            if(antCount > 100 || antCount < 2) {
                manager = new Manager(foodDensity);
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
