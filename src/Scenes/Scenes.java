package Scenes;

import core.collisions.CollisionHandler.BroadPhaseTechnique;
import core.engine.Engine;
import core.entities.RigidBody;
import core.shapes.CircleShape;
import core.shapes.RectangleShape;
import core.shapes.WorldBounds;
import java.awt.Color;
import utils.Vector2D;

public class Scenes {

    public static Engine Scene1() {
        WorldBounds worldBounds = new WorldBounds(
                new Vector2D(100, 100),
                new RectangleShape(600, 400, Color.MAGENTA)
        );

        Engine engine = new Engine(worldBounds, BroadPhaseTechnique.TRIVIAL);

        RigidBody circle1 = new RigidBody(
                new Vector2D(150, 150), //position
                new Vector2D(2, 3), //velocity
                1, //mass
                new CircleShape(20, Color.BLUE)
        );    
        engine.add(circle1);
        
        RigidBody circle2 = new RigidBody(
                new Vector2D(400, 200), //position
                new Vector2D(-3, 2), //velocity
                1, //mass
                new CircleShape(30, Color.RED)
        );    
        engine.add(circle2);

        return engine;
    }
}
