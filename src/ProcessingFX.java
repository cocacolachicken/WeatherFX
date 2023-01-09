import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The root class that organizes all the relationships between JavaFX, the Keyboard, the Mouse, the Screen and the ability
 * to draw on the window.
 *
 * @author: Adam Drenth
 * @version: 12.12.2021
 */
public abstract class ProcessingFX extends Application {
    // These variables are accessible to the MyExampleApp class
    protected Window window;    // Maintains the status and control of the window itself, including size and title
    protected Matrix matrix;    // Tracks and manages transformations on the Convas
    protected Mouse mouse;      // Tracks and maintains the status of the mouse
    protected Keyboard key;     // Tracks and maintains the status of the keyboard

    // These variables are hidden for all other classes and objects.
    private Scene scene;     // Root node of the window
    private Canvas canvas;   // Main component where all elements are drawn
    private AnchorPane canvasPane;
    private AnimationTimer drawLoop; // Animated loop that calls the draw() function
    private int frameRate = -1; // in milliseconds

    // Defaults
    private final int DEFAULT_WIDTH = 600;
    private final int DEFAULT_HEIGHT = 600;
    private final double DEFAULT_RATE = 1.0/24;


    /**
     * The "Main method" for all JavaFX programs.  It is launched after the JavaFX components are initialized.
     * This method is used to attached all the required components to the application window and it works like Russian
     * dolls.
     *
     * Stage represents the application window. It holds everything and have the overall visual size
     * Scene is everything is current present in the window.  Everything MUST be attached to the scene to be visible
     * Group root organzes everything to be attached to the Scene.  Everything MUST be attached to the root or something
     *     else that is ultimately attached to the root to be visible
     *
     * Stage <- Scene <- root <- {Everything else} <- ...
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        // Create and stack everything together: scene <- canvasPane <- canvas
        AnchorPane root = new AnchorPane();
        canvasPane = new AnchorPane();
        canvas = new Canvas(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        canvasPane.getChildren().add(canvas);
        scene = new Scene(canvasPane, DEFAULT_WIDTH, DEFAULT_HEIGHT );

        // Setup and Initialize the objects that watch the state of the application (window, keyboard, mouse)
        window = new Window(stage, scene, canvasPane, canvas);
        mouse = new Mouse(this, scene);
        key = new Keyboard(this, scene);

        // Add the scene to the scene and make the application visible
        stage.setScene(scene);
        stage.show();

        // Starts the Processing-like or P5.js Processes where setup() is called once and draw() repeatedly afterwards
        startProcessingProcess();
    }

    /**
     * The proper way to shut down a JavaFX application. It ensures all required shut down methods are called.
     */
    protected void exit() {
        Platform.exit();
    }

    /**
     * Emulates P5.js or Processing Functionality by calling setup() at the start.  draw() is called over and over again
     */
    private void startProcessingProcess() {
        matrix = new Matrix(canvas);    // Matrix is watches and modifies the Canvas with transformations.

        // Call setup()
        GraphicsContext pen = canvas.getGraphicsContext2D();
        setup(pen);
        matrix.reset();

        drawLoop = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if(frameRate == -1) {
                    drawMethodCall();
                } else if(now - lastUpdate >= frameRate*1_000_000) {
                    drawMethodCall();
                    lastUpdate = now;
                }
            }
        };
        /*
        // Call draw() an infinite number of times
        drawLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frames = new KeyFrame(
                Duration.seconds(DEFAULT_RATE),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        drawMethodCall();
                    }
                }
        );
        // Add KeyFrame to the loop and start the animation
        drawLoop.getKeyFrames().add(frames);

         */
        drawLoop.start();
    }

    private void drawMethodCall() {
        // Emulation of a draw method being called
        GraphicsContext pen = canvas.getGraphicsContext2D();
        draw(pen);
        matrix.reset();
    }

    public void setRate(double newRate) {
        /*
        KeyFrame frames = new KeyFrame(
                Duration.seconds(newRate),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        drawMethodCall();
                    }
                }
        );
        // Add KeyFrame to the loop and start the animation
        drawLoop.getKeyFrames().clear();
        drawLoop.getKeyFrames().add(frames);
        drawLoop.play();

         */
        if(newRate <= 0 ) {
            frameRate = -1;
        } else {
            frameRate = (int) (1000 / newRate);
        }
    }

    /**
     * The setup method MUST be implemented in your application.  It passes along an object that allows you to draw
     * directly on the canvas. It is called once and only at the beginning.
     * @param pen An object used for drawing on the canvas
     */
    protected abstract void setup(GraphicsContext pen);

    /**
     * The setup method MUST be implemented in your appplication. It passes along an object that allows you to draw
     * directly on the canvas. It is called after setup() and it is called repeatedly and infinitely at a
     * regular interval.
     * @param pen An object used for drawing on the canvas
     */
    protected abstract void draw(GraphicsContext pen);

    /**
     * Optional Methods to be overloaded for mouse events
     */
    public void mouseClicked() {}   // Occurs when mouse is Pressed & Released in a specific time frame
    public void mousePressed() {}   // Occurs when a mouse button is pressed
    public void mouseReleased() {}  // Occurs when a mouse button is released
    public void mouseDragged() {}   // Occurs when the mouse is pressed and it moves
    public void mouseMoved() {}     // Occurs when the mouse is moving within the window
    public void mouseWheel() {}     // Occurs when the the mouse is scrolling

    /**
     * Optional Methods to be overloaded for keyboard events
     */
    public void keyTyped() {}       // Occurs when a key is pressed & released in a specific time frame. DOES NOT INCLUDE SPECIAL KEYS (e.g. arrows, etc.)
    public void keyPressed() {}     // Occurs when any key is pressed. It can happen repeatedly while being pressed.
    public void keyReleased() {}    // Occurs when any key is released.

    /**
     * Optional Methods to be overloaded for window events
     */
    public void windowChanged() {}  // Occurs if the stage is resized (main application window)
}
