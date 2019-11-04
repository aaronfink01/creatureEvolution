import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.*;

/**
 * Write a description of class Ant here.
 *
 * @author Aaron Fink
 * @version September 24, 2019
 */
public class Ant extends Agent {
    // Simulation variables (not included in superclass Agent)
    public double direction;
    public int framesSinceReproduction = 0;
    
    // Lifetime traits
    public double movementSpeedMean;
    public double movementSpeedDeviation;
    public double rotationSpeedMean;
    public double rotationSpeedDeviation;
    
    // Utilities
    public Random randomizer;

    /**
     * Constructor for objects of class Ant
     */
    public Ant(Vector p, double d, double msm, double msd, double rsm, double rsd, double r, double e, double g) {
        super(p, r, e, 200, 0, 0, g);
        
        this.direction = d;
        
        this.movementSpeedMean = msm;
        this.movementSpeedDeviation = msd;
        this.rotationSpeedMean = rsm;
        this.rotationSpeedDeviation = rsd;
        randomizer = new Random();
    }
    
    public void update(ArrayList<Agent> agents) {
        move();
        eat(agents);
        if(readyToReproduce()) {
            attemptToReproduce(agents);
        }
        
        constrainEnergy();
        framesSinceReproduction++;
    }
    
    public void move() {
        double movement = randomizer.nextGaussian() * movementSpeedDeviation + movementSpeedMean;
        double rotation = randomizer.nextGaussian() * rotationSpeedDeviation + rotationSpeedMean;
        Vector motion = new Vector(movement, 0.0).rotate(direction);
        position.add(motion);
        constrainToScreen();
        direction += rotation;
        energy -= movement * radius * radius / 10000000;
    }
    
    public void eat(ArrayList<Agent> agents) {
        for(Agent agent : agents) {
            if(agent instanceof Food) {
                Food food = (Food)agent;
                if(food.overlap(position, radius)) {
                    energy += food.getEnergyValue();
                    food.setEaten();
                }
            }
        }
    }
    
    public void attemptToReproduce(ArrayList<Agent> agents) {
        for(Agent agent : agents) {
            if(agent instanceof Ant && agent != this) {
                Ant ant = (Ant)agent;
                if(ant.overlap(position, radius) && ant.readyToReproduce()) {
                    Ant baby = Ant.simulateReproduction(this, ant);
                    agents.add(baby);
                    break;
                }
            }
        }
    }
    
    public boolean readyToReproduce() {
        return framesSinceReproduction > 500;
    }
    
    public void resetFramesSinceReproduction() {
        framesSinceReproduction = 0;
    }
    
    public void constrainEnergy() {
        if(energy > maxEnergy) {
            energy = maxEnergy;
        }
    }
    
    public void reduceEnergy(double lostEnergy) {
        energy -= lostEnergy;
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
        return new Ant(position, direction, msm, msd, rsm, rsd, radius, Math.pow(radius, 2) / 250, 1);
    }
    
    public static Ant simulateReproduction(Ant firstParent, Ant secondParent) {
        firstParent.resetFramesSinceReproduction();
        secondParent.resetFramesSinceReproduction();
        
        Random randomizer = new Random();
        Vector position = Vector.div(Vector.add(firstParent.position, secondParent.position), 2);
        double direction = (firstParent.direction + secondParent.direction) / 2;
        double msmAverage = (firstParent.movementSpeedMean + secondParent.movementSpeedMean) / 2;
        double msm = randomizer.nextGaussian() * msmAverage / 10 + msmAverage;
        double msdAverage = (firstParent.movementSpeedDeviation + secondParent.movementSpeedDeviation) / 2;
        double msd = randomizer.nextGaussian() * msdAverage / 10 + msdAverage;
        double rsmAverage = (firstParent.rotationSpeedMean + secondParent.rotationSpeedMean) / 2;
        double rsm = randomizer.nextGaussian() * rsmAverage / 10 + rsmAverage;
        double rsdAverage = (firstParent.rotationSpeedDeviation + secondParent.rotationSpeedDeviation) / 2;
        double rsd = randomizer.nextGaussian() * rsdAverage / 10 + rsdAverage;
        double radiusAverage = (firstParent.radius + secondParent.radius) / 2;
        double radius = randomizer.nextGaussian() * radiusAverage / 10 + radiusAverage;
        
        double energyFromFirstParent = firstParent.getEnergyValue() / 2;
        double energyFromSecondParent = secondParent.getEnergyValue() / 2;
        double energy = energyFromFirstParent + energyFromSecondParent;
        firstParent.reduceEnergy(energyFromFirstParent);
        secondParent.reduceEnergy(energyFromSecondParent);
        
        double firstParentGeneration = firstParent.getGeneration();
        double secondParentGeneration = secondParent.getGeneration();
        double generation = 1 + (firstParentGeneration + secondParentGeneration) / 2;
        
        return new Ant(position, direction, msm, msd, rsm, rsd, radius, energy, generation);
    }
}
