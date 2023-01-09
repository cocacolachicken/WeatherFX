import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.*;

/**
 * This class represents the transformations being applied to the Canvas. Transformations are currently limited
 * to rotate(deg), scale(factor) and translate(x, y).  As such, it only performs 2D transformations.
 *
 * push() must be called before a set transformations are called and pop() can be called to return to the
 * previous state of tranformations. The order in which transformations are applied also matter.
 *
 *
 * @author: Adam Drenth
 * @version: 12.12.2021
 */
public class Matrix {
    private static Matrix matrix;
    private int numPush = 0;
    private Canvas canvas;

    // Constructor
    public Matrix(Canvas c) {
        matrix = this;
        canvas = c;
    }

    // Modified Singleton method to ensure no duplicate Matrix objects can be created.
    public static Matrix getMatrix() {
        if(matrix == null) {
            System.err.println("Invalid getMatrix() call. It has not been initialized()");
            Platform.exit();
        }
        return matrix;
    }

    /**
     * Removes all transformations that have been applied to the Canvas
     */
    public void reset() {
        for(int i = 0; i < numPush; i++) {
            canvas.getGraphicsContext2D().restore();
        }
        numPush = 0;
    }

    /**
     * Saves the current state of the transformations. A program can return to this state by calling pop()
     */
    public void push() {
        numPush++;
        canvas.getGraphicsContext2D().save();
    }

    /**
     * Restores the previous state of Canvas before push() was called.
     */
    public void pop() {
        numPush--;
        canvas.getGraphicsContext2D().restore();
    }

    /**
     * Applies a 2D translation that moves all points drawn afterward over by (x,y) pixels
     * @param x The horizontal shift
     * @param y The vertical shift
     */
    public void translate(double x, double y) {
        if(numPush == 0) { push(); }

        GraphicsContext pen = canvas.getGraphicsContext2D();
        pen.translate(x, y);
    }

    /**
     * Applies scaling to the Canvas where all points are scaled from the current position of the
     * origin (0,0).
     * @param ratio the scalar multiplying factor. >1 will enlarge, 0 < ratio < 1 will shrink.
     *              Negative reflects.
     */
    public void scale(double ratio) {
        if(numPush == 0) { push(); }

        GraphicsContext pen = canvas.getGraphicsContext2D();
        pen.scale(ratio, ratio);
    }

    /**
     * Applies rotation to the Canvas where all points are rotated about the current position of the
     * origin (0,0).
     * @param degrees the angle to be rotated in degrees, where positive is clockwise.
     */
    public void rotate(double degrees) {
        if(numPush == 0) { push(); }

        GraphicsContext pen = canvas.getGraphicsContext2D();
        pen.rotate(degrees);
    }

    /**
     * Advanced method to allow for JavaFX complex transformations to be applied, including Shears.
     * @param t A transformation matrix to be applied to the Canvas
     */
    public void applyTransform(Affine t) {
        if(numPush == 0) { push(); }

        GraphicsContext pen = canvas.getGraphicsContext2D();
        pen.setTransform(t);
    }
}
