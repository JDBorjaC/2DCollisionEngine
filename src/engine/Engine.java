package engine;

import entities.RigidBody;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import rendering.Renderer;
import shapes.CircleShape;
import shapes.RectangleShape;
import utils.Vector2D;

public class Engine {

    public List<RigidBody> bodies;

    public Engine(Renderer renderer) {
        
        bodies = new ArrayList();
        RectangleShape rect = new RectangleShape(20, Color.BLUE);
        CircleShape circle = new CircleShape(20, Color.YELLOW);

        RigidBody cbody = new RigidBody(1, circle, circle);
        RigidBody rbody = new RigidBody(1, rect, rect);

        cbody.position = new Vector2D(200, 200);
        rbody.position = new Vector2D(400, 400);

        cbody.velocity = new Vector2D(1f, 3f);
        rbody.velocity = new Vector2D(-3.5f, -2f);
        
        bodies.add(cbody);
        bodies.add(rbody);
        renderer.bodies.add(cbody);
        renderer.bodies.add(rbody);
    }

    public void step(float deltaTime) {
        for (RigidBody body : bodies) {
            body.update(deltaTime);

            if (body.position.x < 0 || body.position.x > 800) {
                body.velocity = body.velocity.reflect();
            }

            if (body.position.y < 0 || body.position.y > 600) {
                body.velocity = body.velocity.reflect();
            }
        }
    }
}
