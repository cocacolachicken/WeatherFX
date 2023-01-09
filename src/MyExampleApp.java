import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This is the Main Class for your JavaFX project. It automatically links into the JavaFX library and it is designed to
 * resemble P5.js and Processing in terms of functionality.
 */
public class MyExampleApp extends ProcessingFX {   // MUST 'extend ProcessingFX'
    // 'Global' variables that can be accessed from any method
    int x = 0;
    Color currFill = Color.RED;

    public static void main(String[] args) {
        launch(args);
    }  /* DO NOT CHANGE THIS METHOD */

    /**
     * You must have the setup() function. It is called once and at the very beginning
     * @param pen An object used for drawing on the canvas
     */
    public void setup(GraphicsContext pen) {
        // This draws a black background with a white rectangle and circle on it.
        // Note that the TOP-LEFT corner is (0, 0)
        pen.setFill(Color.BLACK);
        pen.fillRect(0, 0, window.getWidth(), window.getHeight());
        pen.setFill(Color.WHITE);
        pen.fillRect(100, 200, 50, 75);
        pen.fillOval(400, 400, 50, 50);
    }

    /**
     * You must have the draw() function. It is called after setup() and it is called repeatedly at
     * a constant interval of 0.01 seconds.
     * @param pen An object used for drawing on the canvas
     */
    public void draw(GraphicsContext pen) {
        // Resets the background to be yellow
        window.setBackground(Color.YELLOW);
        // Clears everything that has already been drawn
        // without this method, all drawings build up on each other.
        pen.clearRect(0, 0, window.getWidth(),window.getHeight());

        // Push to start applying transformations
        matrix.push();
            // Translate the origin to (50, 50) and rotate it by x degrees Clock-wise
            matrix.translate(50, 50);
            matrix.rotate(x);
            // If a key is pressed, the rectangle is blue instead of red
            if(key.isPressed()) {
                pen.setFill(Color.BLUE);
            } else {
                pen.setFill(Color.RED);
            }
            //Draw a rectangle with the current fill colour
            pen.fillRect(0, 0, 10, 60);
        matrix.pop(); // Restore to the default (0,0)
        matrix.push(); // Prep for more transformations
            // Translate the origin to the where the mouse is currently positioned and
            // rotate it Counter-Clockwise
            matrix.translate(mouse.x, mouse.y);
            matrix.rotate(-x);
            // Set the fill to the colour set by the mousePressed and mouseReleased events
            pen.setStroke(currFill);
            // Draw a rectangle using the current fill
            pen.strokeRect(0, 0, 10, 60);
        matrix.pop();

        // Update x by increasing it by 1
        x++;
    }

    // Mouse Event: Called when the mouse button is pressed
    public void mousePressed() {
        currFill = Color.PURPLE;    // Set global colour variable to purple
    }

    // Mouse Event: Called when the mouse button is released
    public void mouseReleased() {
        currFill = Color.RED;       // Set global colour variable to red
    }

    // Keyboard Event: Called when any key on the keyboard is pressed
    // This will be called repeatedly on some systems while the key is pressed
    public void keyPressed() {
        // Print out the key's value
        System.out.println(key.keyCode);
        System.out.println(key.key);
    }
}
