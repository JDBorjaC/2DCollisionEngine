package core.shapes;

import core.shapes.CircleShape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import utils.MathUtils;
import utils.Vector2D;

public class RectangleShape extends Shape {

    public int width, height;

    public RectangleShape(int width, int height, Color color) {
        super(color);
        this.width = width;
        this.height = height;
    }

    public RectangleShape(int length, Color color) {
        super(color);
        this.width = length;
        this.height = length;
    }

    public boolean intersectsWithRectangleShape(
            Vector2D position,
            RectangleShape other,
            Vector2D otherPosition
    ) {
        Rectangle rect = new Rectangle(
                (int) position.x, (int) position.y,
                width, height
        );
        Rectangle otherRect = new Rectangle(
                (int) otherPosition.x, (int) otherPosition.y,
                other.width, other.height
        );
        return rect.intersects(otherRect);
    }

    public boolean containsCircle(
            Vector2D position,
            CircleShape circle,
            Vector2D circleCenter
    ) {
        // Calcula los límites del rectángulo
        float left = position.x;
        float right = position.x + width;
        float top = position.y;
        float bottom = position.y + height;

        // Comprueba si el círculo está dentro de los límites del rectángulo
        return (circleCenter.x - circle.radius > left)
                && (circleCenter.x + circle.radius < right)
                && (circleCenter.y - circle.radius > top)
                && (circleCenter.y + circle.radius < bottom);
    }

    @Override
    public boolean intersects(Vector2D position, Shape other,
            Vector2D otherPosition
    ) {

        if (other instanceof RectangleShape otherRect) {
            return intersectsWithRectangleShape(
                    position, otherRect, otherPosition
            );
        } else if (other instanceof CircleShape circle) {
            return containsCircle(
                    position, circle, otherPosition
            );
        }

        throw new UnsupportedOperationException("Trying to collide with unsupported Shape");
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(width, height);
    }

    @Override
    public Rectangle getRect(Vector2D position) {
        return new Rectangle(
                (int) position.x, (int) position.y,
                width, height
        );
    }

    @Override
    public void draw(Graphics g, Vector2D position) {
        g.setColor(color);
        g.drawRect((int) position.x, (int) position.y, width, height);
    }
}
