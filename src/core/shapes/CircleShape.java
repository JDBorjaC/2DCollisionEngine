package core.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import utils.MathUtils;
import utils.Vector2D;

public class CircleShape extends Shape {

    public float radius;

    public CircleShape(float radius, Color color) {
        super(color);
        this.radius = radius;
    }
    
    public boolean intersectsWithCircle(
            Vector2D position, 
            CircleShape circle,
            Vector2D circlePosition
    ) {
        return position.distanceTo(circlePosition) <= (radius + circle.radius);
    }
    
    public boolean intersectsWithRectangle(
            Vector2D position,
            RectangleShape rect,
            Vector2D rectPosition
    ) {
        /*
        If the distance from the circle's position to the
        closest point in the rectangle isn't bigger than the 
        radius, then there's an intersection
        */
        return MathUtils.closestPoint(
                this, position, rect, rectPosition
        ).distanceTo(position) <= radius;
    }

    @Override
    public boolean intersects(Vector2D position, Shape other, Vector2D otherPosition) {

        if (other instanceof RectangleShape otherRect) {
            return intersectsWithRectangle(
                    position, otherRect, otherPosition
            );
        } else if (other instanceof CircleShape circle) {
            return intersectsWithCircle(
                    position, circle, otherPosition
            );
        }

        throw new UnsupportedOperationException("Trying to collide with unsupported Shape");
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(2 * (int) radius, 2 * (int) radius);
    }

    @Override
    public Rectangle getRect(Vector2D position) {
        return new Rectangle(
                (int) position.x, (int) position.y,
                2 * (int) radius, 2 * (int) radius
        );
    }

    @Override
    public void draw(Graphics g, Vector2D position) {
        g.setColor(color);
        g.fillOval(
                (int) (position.x - radius),
                (int) (position.y - radius),
                (int) (radius*2),
                (int) (radius*2)
        );
    }

}
