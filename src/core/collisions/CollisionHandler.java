package core.collisions;

import core.collisions.broad.BVH;
import core.collisions.broad.BroadPhase;
import core.collisions.broad.KDTree;
import core.collisions.broad.SweepAndPrune;
import core.collisions.broad.TrivialBroadPhase;
import core.collisions.broad.UniformGrid;
import core.collisions.narrow.NarrowPhase;
import core.entities.Entity;
import core.shapes.WorldBounds;
import java.util.List;

public class CollisionHandler {

    public enum BroadPhaseTechnique {
        TRIVIAL,
        SWEEP_AND_PRUNE,
        UNIFORM_GRID,
        KD_TREES,
        BVH
    }

    private BroadPhase broadPhase;

    public CollisionHandler(BroadPhaseTechnique bpt) {
        broadPhase = createBroadPhaseDetector(bpt);
        broadPhase.setNarrowPhase(new NarrowPhase());
    }

    private BroadPhase createBroadPhaseDetector(BroadPhaseTechnique bpt) {
        return switch (bpt) {
            case TRIVIAL -> new TrivialBroadPhase();
            case SWEEP_AND_PRUNE -> new SweepAndPrune();
            case UNIFORM_GRID -> new UniformGrid();
            case KD_TREES -> new KDTree();
            case BVH -> new BVH();
        };
    }

    public void handleCollisions(WorldBounds wb, List<Entity> entities) {
        broadPhase.detectCollisions(wb, entities);
    }
}
