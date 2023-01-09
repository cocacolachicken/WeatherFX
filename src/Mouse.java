import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

/**
 * This class represents the Mouse while monitoring and connecting it to method calls defined by the
 * developer.  It keeps track of mouse's position, previous position, button clicks (left, right, middle),
 * as well as the scrolling/dragging.
 *
 * This class does not emulate proper encapsulation on purpose
 *
 * @author: Adam Drenth
 * @version: 13.12.2021
 */
public class Mouse {
    private static Mouse theMouse;

    public boolean pressed = false;
    public double x = -1;
    public double y = -1;
    public double px = -1;
    public double py = -1;
    public double wheelX = -1;
    public double wheelY = -1;
    public boolean left = false;
    public boolean middle = false;
    public boolean right = false;

    // Constructor
    public Mouse(ProcessingFX pfx, Scene s) {
        theMouse = this;
        init(pfx, s);
    }

    // Modified Singleton method to ensure no duplicate Mouse objects can be created.
    public static Mouse getMouse() {
        if(theMouse == null) {
            System.err.println("Invalid getMouse() call. It has not been initialized()");
            Platform.exit();
        }
        return theMouse;
    }

    // Getters and Setters
    public boolean isPressed() { return getMouse().pressed; }
    public double getX() { return getMouse().x; }
    public double getY() { return getMouse().y; }
    public double getPrevX() { return getMouse().px; }
    public double getPrevY() { return getMouse().py; }
    public double getWheelX() { return getMouse().wheelX; }
    public double getWheelY() { return getMouse().wheelY; }

    /**
     * Initialization method for that ties P5.js/Processing-like mouse events with JavaFX Mouse events
     * @param pfx The main application for the library
     * @param s The main Scene that events will be attached to
     */
    public void init(ProcessingFX pfx, Scene s) {
        s.setOnMouseClicked(event -> {
            updateButtons(event);
            pfx.mouseClicked();
        });
        s.setOnMousePressed(event -> {
            updateButtons(event);
            getMouse().pressed = true;
            pfx.mousePressed();
        });
        s.setOnMouseReleased(event -> {
            updateButtons(event);
            getMouse().pressed = false;
            pfx.mouseReleased();
        });
        s.setOnScroll(event -> {
            getMouse().wheelX = event.getDeltaX();
            getMouse().wheelY = event.getDeltaY();
            pfx.mouseWheel();
        });
        s.setOnMouseMoved(event -> {
            updateButtons(event);
            getMouse().px = x;
            getMouse().py = y;
            getMouse().x = event.getX();
            getMouse().y = event.getY();
            pfx.mouseMoved();
        });
        s.setOnMouseDragged(event -> {
            updateButtons(event);
            getMouse().px = x;
            getMouse().py = y;
            getMouse().x = event.getX();
            getMouse().y = event.getY();
            pfx.mouseDragged();
        });
    }

    /**
     * Updates the state of all the mouse buttons
     * @param event The current mouse event
     */
    private void updateButtons(MouseEvent event) {
        right = event.isPrimaryButtonDown();
        middle = event.isMiddleButtonDown();
        left = event.isSecondaryButtonDown();
    }
}
