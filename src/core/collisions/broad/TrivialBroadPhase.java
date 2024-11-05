
package core.collisions.broad;

import core.entities.Entity;
import java.util.List;
import core.shapes.WorldBounds;


public class TrivialBroadPhase extends BroadPhase{

    /*
    Trivial Collision Detection: ask for every single pair of shapes
    */
    @Override
    public void detectCollisions(WorldBounds worldBounds, List<Entity> entities) {
        narrowPhase.detectCollisions(worldBounds, entities);
    }
}
