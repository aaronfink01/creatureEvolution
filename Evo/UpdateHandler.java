import java.util.TimerTask; 

/**
 * Write a description of class UpdateHandler here.
 *
 * @author Aaron Fink
 * @version September 26, 2019
 */
public class UpdateHandler extends TimerTask {
    Manager manager;
    
    /**
     * Constructor for objects of class UpdateHandler
     */
    public UpdateHandler(Manager manager) {
        this.manager = manager;
    }
    
    public void run() {
        manager.displayWorld();
        manager.updateWorld();
        manager.outputData();
    }
}
