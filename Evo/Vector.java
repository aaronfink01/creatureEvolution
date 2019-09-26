import java.lang.Math;

/**
 * Write a description of class Vector here.
 *
 * @author Aaron Fink
 * @version September 25, 2019
 */
public class Vector {
    public double x;
    public double y;

    /**
     * Constructor for objects of class Vector
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public void add(Vector other) {
        x += other.x;
        y += other.y;
    }
    
    public void sub(Vector other) {
        x -= other.x;
        y -= other.y;
    }
    
    public void mult(double scalar) {
        x *= scalar;
        y *= scalar;
    }
    
    public void div(double scalar) {
        x /= scalar;
        y /= scalar;
    }
    
    public void normalize() {
        double mag = getMag();
        x /= mag;
        y /= mag;
    }
    
    public double getMag() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    
    public double dist(Vector other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }
    
    public boolean equals(Vector other) {
        return x == other.x && y == other.y;
    }
    
    public Vector copy() {
        return new Vector(x, y);
    }
}
