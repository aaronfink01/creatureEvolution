import java.util.*;

/**
 * Write a description of class DataHandler here.
 *
 * @author Aaron Fink
 * @version October 5, 2019
 */
public class DataHandler {
    public static void printDataLabels() {
        System.out.println("Data Number,Ants (count),Radius (pixels),Mean Movement Speed (pixels per frame),Movement Speed Deviation (pixels per frame),Mean Rotation Speed (radians per frame),Rotation Speed Deviation (radians per frame),Generation,Food (count)");
    }
    
    public static void outputData(ArrayList<Agent> agents, int frameCount, int saveDataEveryXthFrame) {
        // Output Time
        System.out.print(frameCount / saveDataEveryXthFrame);
        System.out.print(",");
        
        // Calculate Ant Traits
        int antCount = 0;
        double radius = 0;
        double movementSpeedMean = 0;
        double movementSpeedDeviation = 0;
        double rotationSpeedMean = 0;
        double rotationSpeedDeviation = 0;
        double generation = 0;
        
        for(Agent agent : agents) {
            if(agent instanceof Ant) {
                antCount++;
                Ant ant = (Ant)agent;
                radius += ant.radius;
                movementSpeedMean += ant.movementSpeedMean;
                movementSpeedDeviation += ant.movementSpeedDeviation;
                rotationSpeedMean += ant.rotationSpeedMean;
                rotationSpeedDeviation += ant.rotationSpeedDeviation;
                generation += ant.generation;
            }
        }
        
        radius /= antCount;
        movementSpeedMean /= antCount;
        movementSpeedDeviation /= antCount;
        rotationSpeedMean /= antCount;
        rotationSpeedDeviation /= antCount;
        generation /= antCount;
        
        // Output Ant Traits
        System.out.print(antCount);
        System.out.print(",");
        System.out.print(radius);
        System.out.print(",");
        System.out.print(movementSpeedMean);
        System.out.print(",");
        System.out.print(movementSpeedDeviation);
        System.out.print(",");
        System.out.print(rotationSpeedMean);
        System.out.print(",");
        System.out.print(rotationSpeedDeviation);
        System.out.print(",");
        System.out.print(generation);
        System.out.print(",");
        
        // Output Food Count
        System.out.print(agents.size() - antCount);
        System.out.print("\n");
    }
}
