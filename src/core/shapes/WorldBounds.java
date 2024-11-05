
package core.shapes;

import core.entities.Entity;
import java.awt.Graphics;
import utils.Vector2D;


public class WorldBounds extends Entity{

    public Vector2D position;
    public RectangleShape outline;


    public WorldBounds(Vector2D position, RectangleShape outline) {
        super(position, 1, outline);
        this.position = position;
        this.outline = outline;
    }
    
    public boolean contains(Entity e){
        return outline.intersects(position, e.collisionShape, e.position);
    }

    public void draw(Graphics g) {
        outline.draw(g, position);
    }
    
}
