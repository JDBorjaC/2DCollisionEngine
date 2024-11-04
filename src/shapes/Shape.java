package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Vector2D;

public abstract class Shape {

    public Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public abstract boolean intersects(Vector2D position, Shape other, Vector2D otherPosition);

    public abstract Rectangle getRect();
    public abstract Rectangle getRect(Vector2D position);

    public abstract void draw(Graphics g, Vector2D position);

    protected boolean logNotImplemented(Shape other) {
        Logger.getLogger(
                RectangleShape.class.getName()
        ).log(
                Level.WARNING,
                "Trying to collide with a non-implemented Shape: {0}",
                other.getClass().getSimpleName()
        );
        return false;
    }
}
