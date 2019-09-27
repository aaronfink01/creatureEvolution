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
    
    public Vector rotate(double angle) {
        double targetAngle = getRotation() + angle;
        double mag = getMag();
        double resultX = mag * Math.cos(Math.toRadians(targetAngle));
        double resultY = mag * Math.sin(Math.toRadians(targetAngle));
        return new Vector(resultX, resultY);
    }
    
    public double getRotation() {
        if(x > 0 && y == 0) {
            return 0.0;
        }
        if(x < 0 && y == 0) {
            return 180.0;
        }
        if(x == 0 && y > 0) {
            return 90.0;
        }
        if(x == 0 && y < 0) {
            return 270.0;
        }
        double baseRotation = Math.toDegrees(Math.atan(y / x));
        if(x > 0 && y > 0) {
            return baseRotation;
        }
        if(x < 0 && y > 0) {
            return 180 + baseRotation;
        }
        if(x < 0 && y < 0) {
            return 180 + baseRotation;
        }
        if(x > 0 && y < 0) {
            return 360 + baseRotation;
        }
        return baseRotation; // This is here just to satisfy the compiler;
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
