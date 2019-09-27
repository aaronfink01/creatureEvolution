import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Write a description of class Ant here.
 *
 * @author Aaron Fink
 * @version September 24, 2019
 */
public class Ant implements Creature {
    public Vector position;
    public double direction;
    public double speed;

    /**
     * Constructor for objects of class Ant
     */
    public Ant(Vector position, double direction, double speed) {
        this.position = position;
        this.direction = direction;
        this.speed = speed;
    }
    
    public void update() {
        Vector movement = new Vector(speed, 0.0).rotate(direction);
        position.add(movement);
    }
    
    public void display(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillOval(position.x, position.y, 20, 20);
    }
}
