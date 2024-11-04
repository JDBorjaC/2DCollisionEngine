
package entities;

import java.awt.Graphics2D;
import shapes.Shape;

public class RigidBody extends Entity{
    
    public Shape collisionShape;
    public Shape shape;
    
    public RigidBody(float mass, Shape collisionShape, Shape shape) {
        super(mass);
        this.collisionShape = collisionShape;
        this.shape = shape;
    }
    
    public void draw(Graphics2D g2d){
        shape.draw(g2d, position);
    }
}
