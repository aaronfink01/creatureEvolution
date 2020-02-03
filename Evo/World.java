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
    double foodDensity = 1;
        
    public World() {
        agents = new ArrayList<Agent>();
        addRandomAnts(10);
        addRandomFoods(50);
        //addRandomTurtles(10);
    }
    
    public World(double foodDensity) {
        agents = new ArrayList<Agent>();
        addRandomAnts(10);
        addRandomFoods(50);
        //addRandomTurtles(10);
    }
    
    public void display(GraphicsContext gc) {
        for(Agent agent : agents) {
            agent.display(gc);
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
            addRandomFoods((int)foodDensity);
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
    
    public void addRandomFoods(int foodCount) {
        double evm = 0.1 / foodDensity;
        double evd = 0.3 / foodDensity;
        
        for(int i = 0; i < foodCount; i++) {
            Food newFood = Food.initializeRandom(evm, evd);
            agents.add(newFood);
        }
    }
    
    public void addRandomTurtles(int turtleCount) {
        // Input layer: 3 neurons
        // Single hidden layer: 10 neurons
        // Output layer: 2 neurons
        double[][][] weightsDeviation = {{{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}}, {{1, 1}, {1, 1}, {1, 1}, {1, 1}, {1, 1}, {1, 1}, {1, 1}, {1, 1}, {1, 1}, {1, 1}}};
        double[][] biasesDeviation = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1}};
        
        for(int i = 0; i < turtleCount; i++) {
            Turtle newTurtle = Turtle.initializeRandom(weightsDeviation, biasesDeviation);
            agents.add(newTurtle);
        }
    }
}
