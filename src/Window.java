import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class represents the overall application window (Stage and Scene).
 *
 * Methods can be used to change the title, size, resizability and background color
 *
 * @author: Adam Drenth
 * @version: 12.12.2021
 */
public class Window {
    private static Window window;
    private static double width;
    private static double height;
    private Stage stage;
    private Scene scene;
    private AnchorPane root;
    private Canvas canvas;

    // Constructor
    public Window(Stage stage, Scene main, AnchorPane root, Canvas canvas) {
        window = this;
        this.stage = stage;
        scene = main;
        this.root = root;
        this.canvas = canvas;

        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());
        stage.setResizable(false);
    }

    // Modified Singleton method to ensure no duplicate Matrix objects can be created.
    public static Window getWindow() {
        if(window == null) {
            System.err.println("Invalid getWindow() call. It has not been initialized()");
            Platform.exit();
        }
        return window;
    }

    // Getters and Setters
    public double getWidth() { return stage.getWidth(); }
    public double getHeight() { return stage.getHeight(); }
    public void setWidth(double width) { stage.setWidth(width); }
    public void setHeight(double height) { stage.setHeight(height); }

    public void setResizable(boolean isResizable) { stage.setResizable(isResizable); }
    public void setTitle(String title) { stage.setTitle(title); }

    /**
     * Sets the background of the whole window
     * @param c the Color to be applied
     */
    public void setBackground(Color c) {
        scene.setFill(c);
    }

    public void setCanvasPos(double distLeft, double distTop, double distRight, double distBottom) {
        canvas.widthProperty().unbind();
        canvas.heightProperty().unbind();
        if(distLeft >= 0) { AnchorPane.setLeftAnchor(canvas, distLeft); }
        else { AnchorPane.setLeftAnchor(canvas, null); }

        if(distTop >= 0) { AnchorPane.setTopAnchor(canvas, distTop); }
        else { AnchorPane.setTopAnchor(canvas, null); }

        if(distRight >= 0) { AnchorPane.setRightAnchor(canvas, distRight); }
        else { AnchorPane.setRightAnchor(canvas, null); }

        if(distBottom >= 0) { AnchorPane.setBottomAnchor(canvas, distBottom); }
        else { AnchorPane.setBottomAnchor(canvas, null); }
    }

    public void add(Node node, double distLeft, double distTop, double distRight, double distBottom) {
        if(distLeft >= 0) { AnchorPane.setLeftAnchor(node, distLeft); }
        else { AnchorPane.setLeftAnchor(node, null); }

        if(distTop >= 0) { AnchorPane.setTopAnchor(node, distTop); }
        else { AnchorPane.setTopAnchor(node, null); }

        if(distRight >= 0) { AnchorPane.setRightAnchor(node, distRight); }
        else { AnchorPane.setRightAnchor(node, null); }

        if(distBottom >= 0) { AnchorPane.setBottomAnchor(node, distBottom); }
        else { AnchorPane.setBottomAnchor(node, null); }
    }

    /**
     * Initialization method for that ties P5.js/Processing-like window events with JavaFX Stage/Scene events
     * @param pfx The main application for the library
     * @param stage The main Stage that events will be attached to
     */
    private void init(ProcessingFX pfx, Stage stage) {
        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldSceneWidth, Number newSceneWidth) {
                width = newSceneWidth.doubleValue();
                pfx.windowChanged();
            }
        });
        stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                width = newSceneHeight.doubleValue();
                pfx.windowChanged();
            }
        });
    }
}
