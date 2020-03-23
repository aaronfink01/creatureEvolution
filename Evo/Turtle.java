
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import java.util.*;

/**
 * Write a description of class Turtle here.
 *
 * @author Aaron Fink
 * @version January 5, 2020
 */
public class Turtle extends Agent {
    // Simulation variables (not included in superclass Agent)
    public double direction;
    public int framesSinceReproduction = 0;
    
    // Lifetime traits
    public Brain brain;
    
    public boolean justSawFood = false;
    public ArrayList<Turtle> children = new ArrayList<Turtle>();
    
    public Turtle(Vector p, double d, double[][][] w, double[][] b, double e, double g) {
        // position, radius, energy, red, green, blue, generation
        super(p, 15, e, 0, 0, 200, g);
        
        this.direction = d;
        
        // weights, biases
        this.brain = new Brain(w, b);
        // brain should currently expect to have four inputs neurons and two output neurons
    }
    
    public Turtle(Vector p, double d, Brain b, double e, double g) {
        // position, radius, energy, red, green, blue, generation
        super(p, 15, e, 0, 0, 200, g);
        
        this.direction = d;
        this.brain = b;
    }
    
    public void display(GraphicsContext gc, ArrayList<Agent> agents) {
        double[] brainInputs = calculateBrainInputs(agents);
        displayVision(gc, brainInputs);
        if(brainInputs[1] == 1) {
            gc.setFill(Color.rgb(255, 0, 0, Math.max(Math.min(energy, maxEnergy), 0) / maxEnergy));
            gc.fillOval(position.x - radius, position.y - radius, 2 * radius, 2 * radius);
        } else {
            gc.setFill(Color.rgb(0, 0, 200, Math.max(Math.min(energy, maxEnergy), 0) / maxEnergy));
            gc.fillOval(position.x - radius, position.y - radius, 2 * radius, 2 * radius);
        }
        gc.save();
        gc.translate(position.x, position.y);
        gc.rotate(direction);
        gc.fillRect(10, -5, 10, 10);
        gc.restore();
    }
    
    public void displayVision(GraphicsContext gc, double[] brainInputs) {
        double distance = -600 * Math.log(1 / brainInputs[2] - 1);
        Vector rayEnd = new Vector(distance, 0).rotate(direction);
        gc.setStroke(Color.rgb(255, 0, 0));
        gc.strokeLine(position.x, position.y, position.x + rayEnd.x, position.y + rayEnd.y);
    }
    
    public void update(ArrayList<Agent> agents) {
        double[] brainInputs = calculateBrainInputs(agents); // 4: results of vision
        //System.out.println(Double.toString(brainInputs[0]) + ", " + Double.toString(brainInputs[1]) + ", " + Double.toString(brainInputs[2]));
        try {
            if(whatDoesBrainInputsSee(brainInputs) == Class.forName("Food")) {
                justSawFood = true;
            }
        } catch(ClassNotFoundException e) {
            System.out.println("The Food class doesn't exist.");
        }
        double[] brainOutputs = brain.processInputs(brainInputs); // 2: movementSpeed, rotationSpeed
        if (brainInputs[1] == 1) {
            System.out.println("I see food!");
        }
        System.out.println(Double.toString(Math.round(brainOutputs[0] * 100.0) / 100.0) + ", " + Double.toString(Math.round(brainOutputs[1] * 100.0) / 100.0));
        
        move(brainOutputs);
        eat(agents);
        try {
            attemptToReproduce(agents);
        } catch(BrainMismatchException e) {
            System.out.println(e);
        }
        
        constrainEnergy();
        framesSinceReproduction++;
    }
    
    public Class whatDoesBrainInputsSee(double[] brainInputs) {
        if(brainInputs[0] == 1) {
            try {
                return Class.forName("Turtle");
            } catch(ClassNotFoundException e) {
                System.out.println("Where did the turtles go?");
            }
        } else if(brainInputs[1] == 1) {
            try {
                return Class.forName("Food");
            } catch(ClassNotFoundException e) {
                System.out.println("Where did the foods go?");
            }
        }
        return null;
    }
    
    public double[] calculateBrainInputs(ArrayList<Agent> agents) {
        // BrainInputs contains three doubles, each ranging from 0 to 1
        // The first double is 1 if this turtle sees another turtle, 0 otherwise
        // The second double is 1 if this turtle sees a food, 0 otherwise
        // The third double is the distance to the thing this turtle sees put through a sigmoid curve
        double[] brainInputs = new double[3]; // turtle?, food?, distance
        Vector rayPosition = position.copy();
        while(true) {
            double[] result = checkRayResult(rayPosition, agents);
            if(result != null) {
                brainInputs[0] = result[0];
                brainInputs[1] = result[1];
                double rayLength = Vector.sub(rayPosition, position).getMag();
                brainInputs[2] = 1 / (1 + Math.exp(-rayLength / 600));
                break;
            }
            rayPosition.add(new Vector(1, 0).rotate(direction));
        }
        return brainInputs;
    }
    
    public double[] checkRayResult(Vector rayPosition, ArrayList<Agent> agents) {
        if(rayPosition.x < 0 || rayPosition.x > 600 || rayPosition.y < 0 || rayPosition.y > 600) {
            return new double[] {0, 0};
        }
        for(Agent agent : agents) {
            if(agent == this) {
                continue;
            }
            if(agent.overlap(rayPosition, 0)) {
                if(agent instanceof Turtle) {
                    return new double[] {1, 0};
                } else if(agent instanceof Food) {
                    return new double[] {0, 1};
                } else {
                    return new double[] {0, 0}; // Abitrary. Shouldn't really happen. Otherwise, value should be assigned.
                }
            }
        }
        return null;
    }
    
    public void move(double[] brainOutputs) {
        Vector movement = new Vector(5 * (brainOutputs[0] + 0.5), 0).rotate(direction);
        position.add(movement);
        constrainToScreen();
        
        double rotation = 5 * brainOutputs[1];
        direction += rotation;
        
        energy -= movement.getMag() * 225 / 10000000;
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
    
    public void attemptToReproduce(ArrayList<Agent> agents) throws BrainMismatchException {
        for(Agent agent : agents) {
            if(agent instanceof Turtle && agent != this) {
                Turtle turtle = (Turtle)agent;
                if(turtle.overlap(position, radius) && turtle.readyToReproduce((Turtle)agent)) {
                    Turtle baby = Turtle.simulateReproduction(this, turtle);
                    agents.add(baby);
                    break;
                }
            }
        }
    }
    
    public boolean readyToReproduce(Turtle mate) {
        if(children.contains(mate)) {
            return false;
        }
        if(framesSinceReproduction < 500) {
            return false;
        }
        if(energy < maxEnergy * 1 / 3) {
            return false;
        }
        return true;
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
    
    public static Turtle initializeRandom(double[][][] weightsDeviation, double[][] biasesDeviation) {
        Random randomizer = new Random();
        double direction = randomizer.nextDouble() * 360 - 180;
        double x = randomizer.nextDouble() * (600 - 30) + 30;
        double y = randomizer.nextDouble() * (600 - 30) + 30;
        Vector position = new Vector(x, y);
        Brain brain = Brain.initializeRandom(weightsDeviation, biasesDeviation);
        return new Turtle(position, direction, brain, 0.9, 1);
    }
    
    public static Turtle simulateReproduction(Turtle firstParent, Turtle secondParent) throws BrainMismatchException {
        Brain brain = Brain.simulateReproduction(firstParent.brain, secondParent.brain);
        
        Random randomizer = new Random();
        Vector position = Vector.div(Vector.add(firstParent.position, secondParent.position), 2);
        double direction = (firstParent.direction + secondParent.direction) / 2;
        
        // this calculates the energy given to the child by each parent
        // PARENTS LOSE THAT MUCH ENERGY!!!
        double energyFromFirstParent = firstParent.getEnergyValue() / 2;
        double energyFromSecondParent = secondParent.getEnergyValue() / 2;
        double energy = energyFromFirstParent + energyFromSecondParent;
        firstParent.reduceEnergy(energyFromFirstParent + firstParent.maxEnergy / 5.0);
        secondParent.reduceEnergy(energyFromSecondParent + secondParent.maxEnergy / 5.0);
        
        // calculate the child's generation
        double firstParentGeneration = firstParent.getGeneration();
        double secondParentGeneration = secondParent.getGeneration();
        double generation = 1 + (firstParentGeneration + secondParentGeneration) / 2;
        
        // Add the child to the parents' children list
        Turtle child = new Turtle(position, direction, brain, energy, generation);
        firstParent.children.add(child);
        secondParent.children.add(child);
        return child;
    }
}
