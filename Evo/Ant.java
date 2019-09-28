import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.*;

/**
 * Write a description of class Ant here.
 *
 * @author Aaron Fink
 * @version September 24, 2019
 */
public class Ant implements Creature {
    // Simulation variables
    public Vector position;
    public double direction;
    public double energy;
    public boolean eaten;
    
    // Lifetime traits
    public double movementSpeedMean;
    public double movementSpeedDeviation;
    public double rotationSpeedMean;
    public double rotationSpeedDeviation;
    public double radius;
    
    // Utilities
    public Random randomizer;
    

    /**
     * Constructor for objects of class Ant
     */
    public Ant(Vector p, double d, double msm, double msd, double rsm, double rsd, double r) {
        position = p;
        direction = d;
        energy = 1.0;
        eaten = false;
        this.movementSpeedMean = msm;
        this.movementSpeedDeviation = msd;
        this.rotationSpeedMean = rsm;
        this.rotationSpeedDeviation = rsd;
        this.radius = r;
        randomizer = new Random();
    }
    
    public void update(ArrayList<Agent> agents) {
        move();
        eat(agents);
        
        constrainEnergy();
    }
    
    public void move() {
        double movement = randomizer.nextGaussian() * movementSpeedDeviation + movementSpeedMean;
        double rotation = randomizer.nextGaussian() * rotationSpeedDeviation + rotationSpeedMean;
        Vector motion = new Vector(movement, 0.0).rotate(direction);
        position.add(motion);
        constrainToScreen();
        direction += rotation;
        energy -= movement * radius * radius / 1000000;
    }
    
    public void eat(ArrayList<Agent> agents) {
        for(Agent agent : agents) {
            if(agent instanceof Food) {
                if(agent.withinRange(position, radius)) {
                    energy += agent.getEnergyValue();
                    agent.setEaten();
                }
            }
        }
    }
    
    public void constrainEnergy() {
        if(energy > 1.0) {
            energy = 1.0;
        }
    }
    
    public void constrainToScreen() {
        if(position.x < radius) {
            position.x = radius;
        }
        if(position.x > 600 - radius) {
            position.x = 600 - radius;
        }
        if(position.y < radius) {
            position.y = radius;
        }
        if(position.y > 600 - radius) {
            position.y = 600 - radius;
        }
    }
    
    public void display(GraphicsContext gc) {
        if(energy > 1.0) {
            // Ants should never have more than full energy, but rounding errors may cause them to
            gc.setFill(Color.rgb(200, 0, 0, 1));
        } else {
            gc.setFill(Color.rgb(200, 0, 0, energy));
        }
        gc.fillOval(position.x - radius, position.y - radius, 2 * radius, 2 * radius);
    }
    
    public boolean shouldBeRemoved() {
        return energy <= 0.0 || eaten;
    }
    
    public boolean withinRange(Vector center, double distance) {
        return position.dist(center) < distance + radius;
    }
    
    public double getEnergyValue() {
        return energy;
    }
    
    public void setEaten() {
        eaten = true;
    }
    
    /**
     * Creates a randomized ant as part of a population.
     * @param msmm the mean movementSpeedMean for initialized ants
     * @param msmd the size of a standard deviatation of movementSpeedMean for initialized ants
     * @param msdm the mean movementSpeedDeviation for initialized ants
     * @param msdd the size of a standard deviation of movementSpeedDeviation for intialized ants
     * @param rsmm the mean rotationSpeedMean for initialized ants
     * @param rsmd the size of a standard deviatation of rotationSpeedMean for initialized ants
     * @param rsdm the mean rotationSpeedDeviation for initialized ants
     * @param rsdd the size of a standard deviation of rotationSpeedDeviation for intialized ants
     * @param rm the mean radius for initialized ants
     * @param rd the size of a standard deviation of radius for initialized ants
     */
    public static Ant initializeRandom(double msmm, double msmd, double msdm, double msdd, double rsmm, double rsmd, double rsdm, double rsdd, double rm, double rd)  {
        Random randomizer = new Random();
        double direction = randomizer.nextDouble() * 360 - 180;
        double msm = randomizer.nextGaussian() * msmd + msmm;
        double msd = randomizer.nextGaussian() * msdd + msdm;
        double rsm = randomizer.nextGaussian() * rsmd + rsmm;
        double rsd = randomizer.nextGaussian() * rsdd + rsdm;
        double radius = randomizer.nextGaussian() * rd + rm;
        double x = randomizer.nextDouble() * (600 - 2 * radius) + radius;
        double y = randomizer.nextDouble() * (600 - 2 * radius) + radius;
        Vector position = new Vector(x, y);
        return new Ant(position, direction, msm, msd, rsm, rsd, radius);
    }
}
