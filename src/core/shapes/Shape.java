package core.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
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
    
}
