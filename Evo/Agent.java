import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import java.util.*;

/**
 * Write a description of class Agent here.
 *
 * @author Aaron Fink
 * @version September 28, 2019
 */
public class Agent {
    Vector position = null;
    double radius = 10.0;
    double energy = 10.0;
    double maxEnergy = 20.0;
    boolean eaten = false;
    int red = 0, green = 0, blue = 0;
    double generation = 1;
    double energyFactor = 2500; // trying to keep energy within bounds (energy per area? area per energy?
    
    public Agent(Vector newPosition, double newRadius, double newEnergy, 
                int newRed, int newGreen, int newBlue, double newGeneration) {
        this.position = newPosition; // <== beware null!!
        this.radius = newRadius;
        this.energy = newEnergy;
        this.maxEnergy = Math.pow(newRadius, 2) / 2500;
        this.red = newRed;
        this.green = newGreen;
        this.blue = newBlue;
        this.generation = newGeneration;
    }
    
    public void update(ArrayList<Agent> agents) {}
    
    public void display(GraphicsContext gc) {
        gc.setFill(Color.rgb(red, green, blue, Math.max(Math.min(energy, maxEnergy), 0) / maxEnergy));
        gc.fillOval(position.x - radius, position.y - radius, 2 * radius, 2 * radius);
    }
    
    public boolean shouldBeRemoved() {
        return (energy < 0) || (eaten);
    }
    
    /**
     * is there any overlap between this agent and another (boundaries overlapping)
     */
    public boolean overlap(Vector otherAgentPosition, double otherAgentRadius) {
        // If otherAgentPosition is null we won't catch that,
        // but instead let the exception bubble up and follow the stack trace.
        return position.dist(otherAgentPosition) < (radius + otherAgentRadius);
    }
    
    public double getEnergyValue() {
        return energy;
    }
    
    public double getGeneration() {
        return generation;
    }
    
    public void setEaten() {
        eaten = true;
    }
}
