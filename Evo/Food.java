import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.*;
import java.lang.*;

/**
 * Write a description of class Food here.
 *
 * @author Aaron Fink
 * @version September 27, 2019
 */
public class Food extends Agent {
    public Food(Vector position, double energy) {
        super(position, Math.sqrt(energy) * 50, energy, 0, 200, 0);
    }
    
    /**
     * Creates a randomized food
     * @param evm the mean energyValue for initialized foods
     * @param evd the size of a standard deviation of energyValue for initiailized foods
     */
    public static Food initializeRandom(double evm, double evd) {
        Random randomizer = new Random();
        double x = randomizer.nextDouble() * 590 + 5;
        double y = randomizer.nextDouble() * 590 + 5;
        Vector position = new Vector(x, y);
        double energy = randomizer.nextGaussian() * evd + evm;
        return new Food(position, energy);
    }
}
