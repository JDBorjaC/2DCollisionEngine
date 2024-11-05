
package core.entities;

import java.awt.Graphics;
import core.shapes.Shape;
import utils.Vector2D;

public class RigidBody extends Entity{
    
    public RigidBody(Vector2D position, float mass, Shape collisionShape) {
        super(position, mass, collisionShape);
    }
    public RigidBody(Vector2D position,Vector2D velocity, float mass, Shape collisionShape) {
        super(position, velocity, mass, collisionShape);
    }
    
    @Override
    public void draw(Graphics g){
        collisionShape.draw(g, position);
    }
    
}
