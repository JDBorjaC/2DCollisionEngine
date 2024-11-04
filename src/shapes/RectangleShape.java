package shapes;

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

    public boolean intersectsWithCircle(
            Vector2D position,
            CircleShape circle,
            Vector2D circleCenter
    ) {
        /*
        Stolen from
        https://youtu.be/_xj8FyG-aac?si=tqihBCJMaTNgNDBj
        Find the circle point that's the closest to the rectangle,
        Check if it's within the radius
         */

        Vector2D rightBottomEdge = position.add(new Vector2D(width, height));
        Vector2D closestPoint = MathUtils.clamp(circleCenter, position, rightBottomEdge);
        return closestPoint.distanceTo(circleCenter) <= circle.radius;

        //Diabolic way to do it :)
        //return MathUtils.clamp(circleCenter, position, position.add(new Vector2D(width, height))).distanceTo(circleCenter) <= circle.radius;
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
            return intersectsWithCircle(
                    position, circle, otherPosition
            );
        }

        return this.logNotImplemented(other);
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(width, height);
    }
    @Override
    public Rectangle getRect(Vector2D position) {
        return new Rectangle(
                (int)position.x, (int)position.y, 
                width, height
        );
    }

    @Override
    public void draw(Graphics g, Vector2D position) {
        g.setColor(color);
        g.fillRect((int) position.x, (int) position.y, width, height);
    }
}
