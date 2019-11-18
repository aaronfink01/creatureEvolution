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
    
    public Manager() throws Exception {
        super.init();
        world = new World();
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
        TimerTask task = new UpdateHandler(this);
        timer.schedule(task, 0, 10);
        
        DataHandler.printDataLabels();
    }
    
    public void displayWorld() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        world.display(gc);
    }
    
    public void updateWorld() {
        world.update();
    }
    
    public void outputData() {
        world.outputData();
    }
}
