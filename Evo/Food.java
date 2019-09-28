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
public class Food implements Agent {
    Vector position;
    double energyValue;
    double radius;
    boolean eaten;
    
    public Food(Vector position, double energyValue) {
        this.position = position;
        this.energyValue = energyValue;
        radius = Math.sqrt(energyValue) * 50;
        eaten = false;
    }
    
    public void update(ArrayList<Agent> agents) {
    }
    
    public void display(GraphicsContext gc) {
        gc.setFill(Color.rgb(0, 200, 0));
        gc.fillOval(position.x - radius, position.y - radius, 2 * radius, 2 * radius);
    }
    
    public boolean shouldBeRemoved() {
        return eaten;
    }
    
    public boolean withinRange(Vector center, double distance) {
        return position.dist(center) < distance + radius;
    }
    
    public double getEnergyValue() {
        return energyValue;
    }
    
    public void setEaten() {
        eaten = true;
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
        double energyValue = randomizer.nextGaussian() * evd + evm;
        return new Food(position, energyValue);
    }
}
