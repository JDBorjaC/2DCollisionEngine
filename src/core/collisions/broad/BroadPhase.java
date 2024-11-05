/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.collisions.broad;

import core.collisions.narrow.NarrowPhase;
import core.entities.Entity;
import core.shapes.WorldBounds;
import java.util.List;

public abstract class BroadPhase {
    
    protected NarrowPhase narrowPhase;

    public BroadPhase() {
    }

    public void setNarrowPhase(NarrowPhase narrowPhase) {
        this.narrowPhase = narrowPhase;
    }

    /**
     * Process entities within a broad phase to find potential collisions.
     */
    public abstract void detectCollisions(WorldBounds worldBounds, List<Entity> entities);
}
