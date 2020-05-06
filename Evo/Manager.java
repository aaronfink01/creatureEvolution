import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import java.util.Timer; 
import java.util.TimerTask; 
import javafx.scene.paint.Color;
import java.util.*;

/**
 * Write a description of JavaFX class View here.
 *
 * @author Aaron Fink
 * @version September 24, 2019
 */
public class Manager extends Application {
    World world;
    GraphicsContext gc;
    Canvas canvas;

    /** BEWARE: Using multiple threads will cause the order that random numbers are accessed to
     * be unpredictable, which will make the random seed not guarantee fixed results.
     */
    public static Random randomizer;
    long randomSeed = 2; // The same seed will get the same simulation result every time we run.
    /** If this is true, we are NOT being random. We are getting fixed results. */
    boolean useRandomSeed = false; // If this is true, we are NOT being random.
    // We are getting fixed results. 

    /**
     * JavaFX calls this constructor when we press "Run JavaFX Application" in BlueJ.
     * (probably because it is the default.)
     * We have no idea how to run this thing from Terminal.
     */
    public Manager() throws Exception {
        randomizer = new Random();
        if (useRandomSeed) {
            randomizer.setSeed(randomSeed);
        }
        super.init();
        world = new World();
    }

    /** 
     * We only manually create a Manager (using BlueJ interface)
     * when we want to run without visuals. At which point we call one of these
     * Manager constructors.
     * 
     * This constructor is not called from anywhere in our code.
     * JavaFX calls the other constructor (above).
     */
    public Manager(double foodSpread, double foodQuantity) throws Exception {
        randomizer = new Random();
        if(useRandomSeed) {
            randomizer.setSeed(randomSeed);
        }
        super.init();
        world = new World(foodSpread, foodQuantity);
    }

    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage) throws Exception {
        canvas = new Canvas(600, 600);
        gc = canvas.getGraphicsContext2D();
        Pane root = new Pane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Evolution Simulator");
        stage.show();

        Timer timer = new Timer();
        TimerTask task = new UpdateHandler(this, true, world.agents);
        // see   https://docs.oracle.com/javase/7/docs/api/java/util/Timer.html
        // especially  
        // https://docs.oracle.com/javase/7/docs/api/java/util/Timer.html#schedule(java.util.TimerTask,%20long,%20long)
        // all of the method calls expect to be told pause times in milliseconds. grrr.
         // Shedules the UpdateHandler to run frames with a *10* millisecond delay in between.
         // The *0* says the first frame should be run with no delay.
        timer.schedule(task, 0, 20);
        
        DataHandler.printDataLabels();
    }

    /**
     * Run the simulation without taking the time for visuals.
     * This function has the significant drawback that while it
     * is simulating no other code can execute.
     * (What other code would want to execute anyway? We don't have a GUI do we?)
     * See startNoVisualsThreaded( ) below
     * @param framesToSimulate the number of frames of simulation.
     * Because of how the internal loop works, any negative number will 
     * lead to an infinite simulation.
     */
    public void startNoVisuals(int framesToSimulate) {
        UpdateHandler handler = new UpdateHandler(this, false, world.agents);
        DataHandler.printDataLabels();
        int framesSimulated = 0;
        while(!(framesSimulated == framesToSimulate)) {
            handler.run();
            framesSimulated++;
        }
    }

    /**
     * Run the simulation without taking the time for visuals.
     * This function has the significant improvement that 
     * while it is simulating, other code can execute.
     * See startNoVisuals( ) above which has busy loop 
     * (fast but greedy re threads (what other threads???))
     * 
     * @param framesToSimulate the number of frames of simulation.
     * Because of how the internal loop works, any negative number 
     * will lead to an infinite simulation.
     * 
     * BEWARE: Using multiple threads will cause the order that random numbers are accessed to
     * be unpredictable, which will make the random seed not guarantee fixed results.
     */
    public void startNoVisualsThreaded(int framesToSimulate) throws InterruptedException {
        UpdateHandler handler = new UpdateHandler(this, false, world.agents);
        DataHandler.printDataLabels();
        int framesSimulated = 0;
        while(!(framesSimulated == framesToSimulate)) {
            handler.run();
            framesSimulated++;
            // "sleep(long millis, int nanos) that can be used to pause the execution of current thread for 
            // specified milliseconds and nanoseconds. The allowed nano second value is between 0 and 999999."
            // -- https://www.journaldev.com/1020/thread-sleep-java
            // ps: throwing because other threads (from who?) could interrupt
            // A nanosecond is one billionth of a second.
            Thread.sleep(0, 1);
        }
    }
    
    public void displayWorld(ArrayList<Agent> agents) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        world.display(gc, agents);
    }

    public void updateWorld() {
        world.update();
    }

    public void outputData() {
        world.outputData();
    }
}
