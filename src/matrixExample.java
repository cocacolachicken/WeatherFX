import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This is the Main Class for your JavaFX project. It automatically links into the JavaFX library and it is designed to
 * resemble P5.js and Processing in terms of functionality.
 */
public class matrixExample extends ProcessingFX {   // MUST 'extend ProcessingFX'
    public static void main(String[] args) {
        launch(args);
    }  /* DO NOT CHANGE THIS METHOD */

    /**
     * You must have the setup() function. It is called once and at the very beginning
     * @param pen An object used for drawing on the canvas
     */
    public void setup(GraphicsContext pen) {
    }

    int x = 0;
    public void draw(GraphicsContext pen) {
        // Resets the background to be yellow
        window.setBackground(Color.YELLOW);
        // Clears everything that has already been drawn
        // without this method, all drawings build up on each other.
        pen.clearRect(0, 0, window.getWidth(),window.getHeight());

        // Push to start applying transformations
        matrix.push();
            // Translate the origin to (50, 50) and rotate it by x degrees Clock-wise
            matrix.translate(200, 200);
            matrix.rotate(x);
            matrix.scale(1.0/Math.log(x));
            drawGrid(pen);
        matrix.pop(); // Restore to the default (0,0)

        // Update x by increasing it by 1
        x++;
    }

    public void drawGrid(GraphicsContext pen) {
        for(int i = 0; i < window.getWidth(); i+= 25) {
            pen.strokeLine(i, 0, i, window.getHeight());
        }
        for(int i = 0; i < window.getWidth(); i+= 25) {
            pen.strokeLine(0, i, window.getWidth(), i);
        }
    }
}
