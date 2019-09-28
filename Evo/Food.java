import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.*;

/**
 * Write a description of class Food here.
 *
 * @author Aaron Fink
 * @version September 27, 2019
 */
public class Food implements Agent {
    Vector position;
    
    public Food(Vector position) {
        this.position = position;
    }
    
    public void update() {
        
    }
    
    public void display(GraphicsContext gc) {
        gc.setFill(Color.rgb(0, 200, 0));
        gc.fillOval(position.x - 5, position.y - 5, 10, 10);
    }
    
    public static Food initializeRandom() {
        Random randomizer = new Random();
        double x = randomizer.nextDouble() * 590 + 5;
        double y = randomizer.nextDouble() * 590 + 5;
        Vector position = new Vector(x, y);
        return new Food(position);
    }
}
