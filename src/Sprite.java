import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;

/**
 * This class represents a Sprite or animated character within a game.  It incorporates the ability
 * to handle movement, forces (acceleration, gravity) and resistance (wind resistance, friction).
 * The Sprite must be displayed using an image from a file (BMP, PNG, JPEG, GIF). It can detect
 * collisions with other Sprites and Shape objects.
 *
 * @author: Adam Drenth
 * @version: 14.12.2021
 */
public class Sprite {
    PVector position;
    PVector velocity;
    PVector acceleration;
    protected ImageView image;
    double mass;    // Must be a value greater than 0. This is required for friction forces

    // Construction. It resizes the image to fit within the width and height without
    // perserving the ratio.
    public Sprite(String imageFile, double width, double height) {
        Image pixels = new Image(imageFile);
        image = new ImageView(pixels);
        image.setFitWidth(width);
        image.setFitHeight(height);

        position = new PVector(0, 0);
        velocity = new PVector(0, 0);
        acceleration = new PVector(0, 0);
        mass = 1;
    }

    // Getters and Setters
    public PVector getPosition() { return position; }
    public void setPosition(PVector p) { position = p; }
    public void setVelocity(PVector s) { velocity = s; }
    public void setMass(double m) { mass = m; }

    /**
     * Applies a force the overall forces being applied. Once all the forces are applied, the
     * overall force is set to a zero vector.
     * @param force A PVector representing an additional force being applied to the Sprite
     */
    public void applyForce(PVector force) {
        force = force.mult(1.0/mass);
        acceleration = acceleration.add(force);
    }

    /**
     * Applies a force of friction the overall forces being applied. Once all the forces are
     * applied, the overall force is set to a zero vector. The force is applied in the opposite
     * direction of the current velocity.
     * @param mu the friction coefficient
     */
    public void applyFriction(double mu) {
        PVector friction = velocity.get();
        friction = friction.unit();
        friction = friction.mult(-1).mult(mu);

        acceleration = acceleration.add(friction);
    }

    /**
     * Applies all the forces to the Sprite and updates its position. Afterwards all forces are set
     * to zero.
     */
    public void update() {
        velocity = velocity.add(acceleration);
        position = position.add(velocity);
        acceleration = new PVector(0, 0);
    }

    /**
     * Draws the sprite on the canvas
     * @param pen An object used to draw on the Canvas
     */
    public void draw(GraphicsContext pen) {
        pen.drawImage(image.getImage(), position.getXComp(), position.getYComp());
    }

    /**
     * Applies all the forces to the Sprite and updates its position. Afterwards all forces are set
     * to zero. However boundaries are passed in.
     * @param xMin left-most boundary
     * @param xMax right-most boundary
     * @param yMin top-most boundary
     * @param yMax bottom-most boundary
     * @return true if one of the boundaries are hit
     */
    public boolean update(double xMin, double xMax, double yMin, double yMax) {
        update();

        boolean hitBoundary = false;

        if(position.getXComp() <= xMin) {
            hitBoundary = true;
        } else if(position.getXComp() + image.getFitWidth() >= xMax) {
            hitBoundary = true;
        }
        if(position.getYComp() <= yMin) {
            hitBoundary = true;
        } else if(position.getYComp() + image.getFitHeight() >= yMax) {
            hitBoundary = true;
        }
        return hitBoundary;
    }

    /**
     * Checks if the sprite has hit a horizontal boundary
     * @param xMin left-most boundary
     * @param xMax right-most boundary
     * @return true if one of the boundaries are hit
     */
    public boolean checkHorizontalBounds(double xMin, double xMax) {
        if(position.getXComp() <= xMin) {
            return true;
        } else if(position.getXComp() + image.getFitWidth() >= xMax) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the sprite has hit a vertical boundary
     * @param yMin top-most boundary
     * @param yMax bottom-most boundary
     * @return true if one of the boundaries are hit
     */
    public boolean checkVerticalBounds(double yMin, double yMax) {
        if(position.getYComp() <= yMin) {
            return true;
        } else if(position.getYComp() + image.getFitHeight() >= yMax) {
            return true;
        }
        return false;
    }

    /**
     * Checks whether there is an area of overlap with another Sprite.
     * @param other The other sprite to check if there is overlap with
     * @return true if there is some area of intersection between the two Sprites
     */
    public boolean checkCollision(Sprite other) {
        Bounds myBounds = image.getLayoutBounds();
        Bounds otherBounds = other.image.getLayoutBounds();
        return myBounds.intersects(otherBounds);
    }

    /**
     * Checks whether there is an area of overlap with another Shape object.
     * @param other The other shape object to check if there is overlap with
     * @return true if there is some area of intersection between the two Shapes
     */
    public boolean checkCollision(Shape other) {
        Bounds myBounds = image.getLayoutBounds();
        Bounds otherBounds = other.getLayoutBounds();
        return myBounds.intersects(otherBounds);
    }
}
