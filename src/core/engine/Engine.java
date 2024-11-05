package core.engine;

import core.collisions.CollisionHandler;
import core.collisions.CollisionHandler.BroadPhaseTechnique;
import core.entities.Entity;
import java.util.List;
import core.shapes.WorldBounds;
import java.util.ArrayList;

public class Engine {
    
    public CollisionHandler collisionHandler;

    public List<Entity> entities;
    public WorldBounds bounds;

    public Engine(WorldBounds bounds, BroadPhaseTechnique tech) {
        this.bounds = bounds;
        this.entities = new ArrayList();
        this.collisionHandler = new CollisionHandler(tech);
    }

    public void step(float deltaTime) {
        
        // Find and solve collisions
        collisionHandler.handleCollisions(bounds, entities);
        
        
        //Update physics
        for (Entity entity : entities) {
            entity.update(deltaTime);
        }
    }
    
    public void add(Entity entity){
        if (!entities.contains(entity))
                entities.add(entity);
    }
}
