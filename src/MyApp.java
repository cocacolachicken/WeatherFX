import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This is the Main Class for your JavaFX project. It automatically links into the JavaFX library and it is designed to
 * resemble P5.js and Processing in terms of functionality.
 *
 * This class has access to special hidden variables:
 *      mouse: A Mouse object that keeps track of the state of the mouse.
 *              mouse.x - Mouse's current x-position (double)
 *              mouse.y - Mouse's current y-position (double)
 *              mouse.px - Mouse's previous x-position (double)
 *              mouse.py - Mouse's previous y-position (double)
 *              mouse.pressed - True if Mouse button is pressed (boolean)
 *
 *      key: A Keyboard object that keeps track of the state of the keyboard.
 *              key.keyCode - The last/current key that was pressed if it was a keyCode (non-standard button, e.g. arrows)
 *              key.key - The last/current key that was presssed if it was a standard key (letters, numbers, whitespace)
 *
 *      window: A Window object that keeps track of the applications window, title, size and resizability
 *
 *      matrix: An object that allows you to transform your sketches using rotations(deg), translations(x, y)
 *              and scale(factor)
 *
 * REPLACE THIS HEADER WITH A PROPER HEADER DESCRIBING YOUR PROJECT
 *
 * @author: You! Its your Template so put your name here
 */
public class MyApp extends ProcessingFX {
    /* DO NOT DELETE THIS METHOD */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * setup() method is called once at the start of the program
     * @param pen An object used for drawing on the canvas
     */
    public void setup(GraphicsContext pen) {

    }

    /**
     * draw() method is called after setup(). It is called over and over at regular intervals
     * @param pen An object used for drawing on the canvas
     */
    public void draw(GraphicsContext pen) {

    }

    // Add additional event methods here:
    // mousePressed(), mouseReleased(), mouseClicked(), mouseDragged(), mouseMoved(), mouseWheel()
    // keyPressed(), keyReleased(), keyTyped()
    // windowChanged()
}
