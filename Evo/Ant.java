
/**
 * Write a description of class Ant here.
 *
 * @author Aaron Fink
 * @version September 24, 2019
 */
public class Ant implements Creature {
    private Vector position;

    /**
     * Constructor for objects of class Ant
     */
    public Ant(Vector position) {
        this.position = position;
    }
    
    public void update() {
    }
    
    public Vector getPosition() {
        return position;
    }
}
