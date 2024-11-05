/*
Determines how the entities colliding are affected.
This entities must be passed from a Narrow Phase Detector.
 */
package core.collisions.solver;

import core.entities.Entity;
import core.entities.RigidBody;
import core.shapes.CircleShape;
import core.shapes.RectangleShape;
import core.shapes.Shape;
import core.shapes.WorldBounds;
import utils.MathUtils;
import utils.Vector2D;

public class EllasticSolver {

    public void resolveBounds(WorldBounds wb, Entity b) {
        if (b.collisionShape instanceof CircleShape circle) {
            
            
            //Rectangle edges
            float left = wb.position.x;
            float right = left + wb.outline.width;
            float top = wb.position.y;
            float bottom = top + wb.outline.height;
            
            //Circle properties
            Vector2D pos = b.position;
            Vector2D vel = b.velocity;
            float r = circle.radius;
            
            
            if (pos.x - r < left) {
                pos.x = left + r; 
                vel.x *= -1;
            } else if (pos.x + r > right) {
                pos.x = right - r;
                vel.x *= -1;
            }

            if (pos.y - r < top) {
                pos.y = top + r;
                vel.y *= -1;
            } else if (pos.y + r > bottom) {
                pos.y = bottom - r;
                vel.y *= -1;
            }
            
        }
    }

    public void resolve(Entity a, Entity b) {
        Vector2D cn = getCollisionNormal(a, b);
        float overlap = overlapDist(a, b);

        resolveOverlapping(a, b, cn, overlap);
        resolveAsParticles(a, b, cn);
    }

    public void resolveAsParticles(Entity a, Entity b, Vector2D collisionNormal) {
        Vector2D relativeVelocity = b.velocity.subtract(a.velocity);
        float velocityNormal = relativeVelocity.dot(collisionNormal);

        if (velocityNormal > 0) {
            return;
        }

        float impulseLength = 2f * velocityNormal / (1 / a.mass + 1 / b.mass);
        Vector2D impulse = collisionNormal.times(impulseLength);
        if (a instanceof RigidBody) {
            a.adjustVelocity(impulse.times(1f / a.mass));
        }

        if (b instanceof RigidBody) {
            b.adjustVelocity(impulse.times(-1f / b.mass));
        }

    }

    public void resolveOverlapping(Entity a, Entity b, Vector2D collisionNormal, float overlap) {
        if (overlap <= 0) {
            return;
        }

        float halfOverlap = overlap / 2f;

        a.adjustPosition(collisionNormal.times(-halfOverlap));
        b.adjustPosition(collisionNormal.times(halfOverlap));
    }

    public Vector2D getCollisionNormal(Entity a, Entity b) {
        if (a.collisionShape instanceof CircleShape && b.collisionShape instanceof CircleShape) {
            Vector2D collisionNormal = b.position.subtract(a.position).normalize();
            return collisionNormal;
        }

        throw new UnsupportedOperationException(
                "Something didn't go as planned "
                + "during collisionNormal calculation between "
                + a.getClass().getSimpleName()
                + " and " + b.getClass().getSimpleName()
                + "!!!!!"
        );
    }

    public float overlapDist(Entity a, Entity b) {
        Shape acShape = a.collisionShape;
        Shape bcShape = b.collisionShape;

        Vector2D aPos = a.position;
        Vector2D bPos = b.position;

        /*
        Circle-Circle case
        The difference between the sum of the radiuses and
        the distance between the circles
         */
        if (acShape instanceof CircleShape aC
                && bcShape instanceof CircleShape bC) {

            float overlap = (aC.radius + bC.radius) - (aPos.distanceTo(bPos));
            return overlap > 0 ? overlap : 0;

        } /*
        Rectangle-Circle case
        The difference between the radius of the circle
        and the distance to the closest point in the 
        rectangle.
         */ else if (acShape instanceof RectangleShape aR
                && bcShape instanceof CircleShape bC) {

            Vector2D closestPoint = MathUtils.closestPoint(bC, bPos, aR, aPos);
            float distanceToCircle = closestPoint.distanceTo(aPos);
            float overlap = bC.radius - distanceToCircle;
            return overlap > 0 ? overlap : 0;

        }

        throw new UnsupportedOperationException(
                "Something didn't go as planned "
                + "during overlap distance calculation between "
                + a.getClass().getSimpleName()
                + " and " + b.getClass().getSimpleName()
                + "!!!!!"
        );
    }
}
