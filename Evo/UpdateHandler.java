import java.util.TimerTask; 

/**
 * Write a description of class UpdateHandler here.
 *
 * @author Aaron Fink
 * @version September 26, 2019
 */
public class UpdateHandler extends TimerTask {
    Manager manager;
    boolean visualMode = true;
    
    /**
     * Constructor for objects of class UpdateHandler
     */
    public UpdateHandler(Manager manager, boolean visualMode) {
        this.manager = manager;
        this.visualMode = visualMode;
    }
    
    public void run() {
        if(visualMode) {
            manager.displayWorld();
        }
        manager.updateWorld();
        manager.outputData();
    }
}
