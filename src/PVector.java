import javafx.geometry.Point2D;

/**
 * Vector class is useful for doing physics, physical simulations and simple games. This class focuses on 2D vectors only.
 * All angles are computed in degrees
 * Vector math included is:
 *      Vectors as components (x, y) or standard vector (magnitude, angle)
 *      Vector addition and subtraction
 *      Scalar only operations: multiplication, division
 *      Finding the unit vector: unit() or normalize()
 *      Static methods: angleBetween, magnitudeDiff
 *
 * @author: Adam Drenth
 * @version: 12.12.2021
 */
public class PVector {
    private Point2D components;     // Represents the x and y components

    public PVector(double xComponent, double yComponent) {
        components = new Point2D(xComponent, yComponent);
    }

    /**
     * get() allows for a copy of the PVector
     * @return a deep copy of the PVector
     */
    public PVector get() { return new PVector(components.getX(), components.getY()); }

    // Standard getters and setters
    public double getXComp() { return components.getX(); }
    public double getYComp() { return components.getY(); }
    public double getMagnitude() {
        return Math.sqrt(Math.pow(components.getX(), 2) + Math.pow(components.getX(), 2));
    }
    public double getAngle() {
        double radAngle = Math.atan2(components.getY(), components.getX());
        return Math.toDegrees(radAngle);
    }

    /**
     * Special constructor for making PVectors using their angle and magnitude
     * @param degrees   Angle of the vector from the origin and the positive x-axis.  Measured in degrees
     * @param magnitude The distance from the origin
     * @return A PVector with the correct x/y components
     */
    public static PVector PVectorUsingAngle(double degrees, double magnitude) {
        double radians = Math.toRadians(degrees);
        double xComponent = magnitude*Math.cos(radians);
        double yComponent = magnitude*Math.sin(radians);
        return new PVector(xComponent, yComponent);
    }

    /**
     * Vector addition using components. This does not alter the original vector.
     * @param other The other PVector to be added to the current vector.
     * @return A new PVector to allow for chaining of methods
     */
    public PVector add(PVector other) {
        double xComponent = components.getX() + other.components.getX();
        double yComponent = components.getY() + other.components.getY();
        return new PVector(xComponent, yComponent);
    }

    /**
     * Vector subtraction using components. This does not alter the original vector.
     * @param other The other PVector to be subtracted from the current vector.
     * @return A new PVector to allow for chaining of methods
     */
    public PVector sub(PVector other) {
        double xComponent = components.getX() - other.components.getX();
        double yComponent = components.getY() - other.components.getY();
        return new PVector(xComponent, yComponent);
    }

    /**
     * Scalar multiplication of a PVector. This does not alter the original vector.
     * @param scalar The amount to alter the magnitude of the vector by
     * @return A new PVector to allow for chaining of methods
     */
    public PVector mult(double scalar) {
        double angle = Math.toRadians(getAngle());
        double mag = getMagnitude() * scalar;
        return PVectorUsingAngle(angle, mag);
    }

    /**
     * Scalar division of a PVector. This does not alter the original vector.
     * @param scalar The amount to alter the magnitude of the vector by
     * @return A new PVector to allow for chaining of methods
     */
    public PVector div(double scalar) {
        double angle = Math.toRadians(getAngle());
        double mag = getMagnitude() / scalar;
        return PVectorUsingAngle(angle, mag);
    }

    /**
     * Helper math method for determining the angle between two PVectors
     * @param a First PVector
     * @param b Second PVector
     * @return The angle in degrees
     */
    public static double angleBetween(PVector a, PVector b) {
        double angleA = a.getAngle();
        double angleB = b.getAngle();
        return Math.toDegrees(angleA-angleB);
    }

    /**
     * Helper math method for determining the difference of magnitude between two PVectors
     * @param a First PVector
     * @param b Second PVector
     * @return The result of a.mag - b.mag
     */
    public static double magnitudeDiff(PVector a, PVector b) {
        double magA = a.getMagnitude();
        double magB = b.getMagnitude();
        return magA - magB;
    }

    /**
     * Creates a vector with the same angle as the current PVector but a magnitude of 1
     * @return A new PVector to allow for chaining of methods
     */
    public PVector unit() {
        return PVectorUsingAngle(getAngle(), 1.0);
    }

    /**
     * Creates a vector with the same angle as the current PVector but a magnitude of 1
     * @return A new PVector to allow for chaining of methods
     */
    public PVector normalize() {
        return unit();
    }

}
