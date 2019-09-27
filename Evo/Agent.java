import javafx.scene.canvas.GraphicsContext;

/**
 * Write a description of interface Agent here.
 *
 * @author Aaron Fink
 * @version September 24, 2019
 */
public interface Agent {
    public void update();
    public void display(GraphicsContext gc);
    public boolean isDead();
}
