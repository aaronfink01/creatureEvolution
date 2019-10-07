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
    Vector position;
    double radius;
    double energy;
    double maxEnergy;
    boolean eaten = false;
    int red, green, blue;
    double generation;
    
    public Agent(Vector p, double r, double e, int red, int green, int blue, double g) {
        this.position = p;
        this.radius = r;
        this.energy = e;
        this.maxEnergy = Math.pow(r, 2) / 2500;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.generation = g;
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
