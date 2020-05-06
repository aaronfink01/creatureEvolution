import java.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

/**
 * Write a description of class World here.
 *
 * @author Aaron Fink
 * @version September 24
 */
public class World {
    ArrayList<Agent> agents;
    int frameCount = 0;
    int saveDataEveryXthFrame = 1000;  // was 100 until nov 2019
    double foodSpread = 1; // Doesn't effect the total food value present, but effects how spread out it is across the area.
    double foodQuantity = 5; // How much energy is added in the form of food.
    boolean turtleMode = true; // otherwise, use ants instead
    
    public World() {
        agents = new ArrayList<Agent>();
        addRandomFoods(30);
        if(turtleMode) {
            addRandomTurtles(7);
        } else {
            addRandomAnts(10);
        }
    }
    
    public World(double newFoodSpread, double newFoodQuantity) {
        foodSpread = newFoodSpread;
        foodQuantity = newFoodQuantity;
        
        agents = new ArrayList<Agent>();
        //addRandomFoods(50);
        if(turtleMode) {
            addRandomTurtles(7);
        } else {
            addRandomAnts(10);
        }
    }
    
    public void display(GraphicsContext gc, ArrayList<Agent> agents) {
        for(Agent agent : agents) {
            agent.display(gc, agents);
        }
        
        displayData(gc);
    }
    
    public void displayData(GraphicsContext gc) {
        double averageGeneration = 0;
        double animalCount = 0;
        for(Agent agent : agents) {
            if(agent instanceof Ant || agent instanceof Turtle) {
                averageGeneration += agent.generation;
                animalCount++;
            }
        }
        averageGeneration /= animalCount;
        Double roundedGeneration = Math.round(averageGeneration * 10) / 10.0;
        String generationText = "Generation: " + Double.toString(roundedGeneration);
        gc.setFill(Color.BLACK);
        gc.fillText(generationText, 10, 20);
        String animalCountText = "Creatures: " + Double.toString(animalCount);
        gc.fillText(animalCountText, 10, 40);
    }
    
    public void update() {
        // Handle agents
        for(int i = agents.size() - 1; i > -1; i--) {
            Agent agent = agents.get(i);
            agent.update(agents);
            if(agent.shouldBeRemoved()) {
                agents.remove(i);
            }
        }
        
        // Add more food
        if(frameCount % 50 == 0) {
            addRandomFoods((int)foodSpread);
        }
        
        frameCount++;
    }
    
    /**
     * default was to output every 100th frame
     * now this is variable, perhaps every 1000th for long runs
     */
    public void outputData() {
        if(frameCount % saveDataEveryXthFrame == 0) {
            DataHandler.outputData(agents, frameCount, saveDataEveryXthFrame);
        }
    }
    
    public void addRandomAnts(int antCount) {
        // Define the population
        double msmm = 3.0;
        double msmd = 1.0;
        double msdm = 1.0;
        double msdd = 0.5;
        double rsmm = 0.0;
        double rsmd = 5.0;
        double rsdm = 7.0;
        double rsdd = 5.0;
        double rm = 15.0;
        double rd = 5.0;
        
        // Initialize and add randomized ants
        for(int i = 0; i < antCount; i++) {
             Ant newAnt = Ant.initializeRandom(msmm, msmd, msdm, msdd, rsmm, rsmd, rsdm, rsdd, rm, rd);
             agents.add(newAnt);
        }
    }
    
    // foodCount might be taken from foodSpread, or it might be a hardcoded value
    //     like at the start of the simulation, when exactly 50 foods are always added.
    // foodSpread both increases the number of foods and decreases the values of foods proportionally,
    //     to keep total food quantity constant
    public void addRandomFoods(int foodCount) {
        double evm = foodQuantity * 0.1 / foodSpread;
        double evd = foodQuantity * 0.3 / foodSpread;
        
        for(int i = 0; i < foodCount; i++) {
            Food newFood = Food.initializeRandom(evm, evd);
            agents.add(newFood);
        }
    }
    
    public void addRandomTurtles(int turtleCount) {
        // Input layer: 3 neurons
        // Single hidden layer: 2 neurons
        // Output layer: 2 neurons
        double[][][] weightsDeviation = {{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, {{1, 1}, {1, 1}, {1, 1}}};
        double[][] biasesDeviation = {{0.25, 0.25, 0.25}, {0.25, 0.25}};
        
        for(int i = 0; i < turtleCount; i++) {
            Turtle newTurtle = Turtle.initializeRandom(weightsDeviation, biasesDeviation);
            agents.add(newTurtle);
            newTurtle.brain.printOut();
        }
    }
}
