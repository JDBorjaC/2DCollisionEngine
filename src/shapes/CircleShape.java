package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import utils.MathUtils;
import utils.Vector2D;

public class CircleShape extends Shape {

    float radius;

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
        Stolen from
        https://youtu.be/_xj8FyG-aac?si=tqihBCJMaTNgNDBj
        Find the circle point that's the closest to the rectangle,
        Check if it's within the radius
         */

        Vector2D rightBottomEdge = rectPosition.add(new Vector2D(rect.width, rect.height));
        Vector2D closestPoint = MathUtils.clamp(position, rectPosition, rightBottomEdge);
        return closestPoint.distanceTo(position) <= radius;

        //Diabolic way to do it :)
        //return MathUtils.clamp(position, rectPosition, rectPosition.add(new Vector2D(rect.width, rect.height))).distanceTo(position) <= circle.radius;
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

        return this.logNotImplemented(other);
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
