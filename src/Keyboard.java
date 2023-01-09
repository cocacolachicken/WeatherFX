import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * This class represents the Keyboard while monitoring and connecting it to method calls defined by the
 * developer.  It keeps track of keyCode (non-standard keys like arrows) and keys. It is important to know
 * that either an keyCode or key occurs, but never both. Modifying keys are also tracked (shift, alt, ctrl,
 * cmd/window).
 *
 * This class does not emulate proper encapsulation on purpose
 *
 * @author: Adam Drenth
 * @version: 12.12.2021
 */
public class Keyboard {
    private static Keyboard theBoard;
    public boolean pressed = false;
    public KeyCode keyCode;
    public char key;
    public boolean altPressed = false;
    public boolean ctrlPressed = false;
    public boolean shiftPressed = false;
    public boolean cmdPressed = false;

    // Constructor
    public Keyboard(ProcessingFX pfx, Scene scene) {
        theBoard = this;
        init(pfx, scene);
    }

    // Modified Singleton method to ensure no duplicate Keyboards can be created.
    public static Keyboard getKeyboard() {
        if(theBoard == null) {
            System.err.println("Invalid getKeyboard() call. It has not been initialized()");
            Platform.exit();
        }
        return theBoard;
    }

    // Getters and Setters
    public boolean isPressed() { return getKeyboard().pressed; }
    public KeyCode getKeyCode() { return getKeyboard().keyCode; }
    public char getKey() { return getKeyboard().key; }

    /**
     * Initialization method for that ties P5.js/Processing-like events with JavaFX Keyboard events
     * @param pfx The main application for the library
     * @param s The main Scene that events will be attached to
     */
    private void init(ProcessingFX pfx, Scene s) {
        s.setOnKeyTyped(event -> {
            if(event.getCharacter().length() > 0) {
                key = event.getCharacter().charAt(0);
            }
            keyCode = event.getCode();
            updateOptionKeys(event);
            pfx.keyTyped();
        });
        s.setOnKeyPressed(event -> {
            if(event.getText().length() > 0) {
                key = event.getText().charAt(0);
            }
            keyCode = event.getCode();
            updateOptionKeys(event);
            getKeyboard().pressed = true;
            pfx.keyPressed();
        });
        s.setOnKeyReleased(event -> {
            getKeyboard().pressed = false;
            updateOptionKeys(event);
            pfx.keyReleased();
        });
    }

    private void updateOptionKeys(KeyEvent event) {
        altPressed = event.isAltDown();
        ctrlPressed = event.isControlDown();
        shiftPressed = event.isShiftDown();
        cmdPressed = event.isMetaDown();
    }
}
