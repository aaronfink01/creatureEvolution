import javafx.scene.canvas.GraphicsContext;
import java.util.*;

/**
 * Write a description of interface Agent here.
 *
 * @author Aaron Fink
 * @version September 24, 2019
 */
public interface Agent {
    public void update(ArrayList<Agent> agents);
    public void display(GraphicsContext gc);
    public boolean shouldBeRemoved();
    public boolean withinRange(Vector center, double distance);
    public double getEnergyValue();
    public void setEaten();
}
