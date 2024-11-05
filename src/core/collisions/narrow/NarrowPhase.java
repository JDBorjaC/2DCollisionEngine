package core.collisions.narrow;

import core.collisions.solver.EllasticSolver;
import core.entities.Entity;
import java.util.List;
import core.shapes.WorldBounds;

public class NarrowPhase {

    public boolean BOUNDARY_INTERPOLATION = true;
    public EllasticSolver solver;

    public NarrowPhase() {
        this.solver = new EllasticSolver();
    }

    public void detectCollisions(WorldBounds worldBounds, List<Entity> entities) {

        //Prevent entities from leaving the world
        /*
        for (Entity entity : entities) {
            if (!worldBounds.contains(entity)) {
                System.out.println("Ayooo");
                solver.resolve(worldBounds, entity);
            }
        }
*/

        for (int i = 0; i < entities.size() - 1; i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                checkAndResolveCollision(entities.get(i), entities.get(j));
            }
        }
    }
    
    private void checkAndResolveCollision(Entity e1, Entity e2) {
        if (e1.collisionShape.intersects(e1.position, e2.collisionShape, e2.position)) {
            solver.resolve(e1, e2);
        }
    }
}
