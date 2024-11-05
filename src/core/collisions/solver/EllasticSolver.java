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

    public void resolve(Entity a, Entity b) {
        Vector2D cn = getCollisionNormal(a, b);
        float overlap = overlapDist(a, b);

        resolveOverlapping(a, b, cn, overlap);
        resolveAsParticles(a, b, cn);
    }

    public void resolveAsParticles(Entity a, Entity b, Vector2D collisionNormal) {
        /*
        float va = a.velocity.length();
        float vb = b.velocity.length();
        float ma = a.mass;
        float mb = b.mass;
        
        float factor = -2*ma*mb/(ma+mb);
        
        Vector2D impulse = new Vector2D(vb-va, b.position.distanceTo(a.position)).times(factor);
        */

        if (a instanceof RigidBody) {
            a.velocity = a.velocity.reflect();
            //a.adjustVelocity(impulse.times(-1/ a.mass));
        }

        if (b instanceof RigidBody) {
            b.velocity = b.velocity.reflect();
            //b.adjustVelocity(impulse.times(1/ b.mass));
        }

    }

    public void resolveOverlapping(Entity a, Entity b, Vector2D collisionNormal, float overlap) {
        if (overlap <= 0) {
            return;
        }

        float halfOverlap = 0.5f * overlap;

        a.adjustPosition(collisionNormal.times(-halfOverlap));
        b.adjustPosition(collisionNormal.times(halfOverlap));
    }

    public Vector2D getCollisionNormal(Entity a, Entity b) {
        if (a.collisionShape instanceof CircleShape && b.collisionShape instanceof CircleShape) {
            Vector2D collisionNormal = b.position.subtract(a.position).normalize();
            return collisionNormal;
        }
        else if (a instanceof WorldBounds wb) {
            if (b.collisionShape instanceof CircleShape) {
                CircleShape c = (CircleShape) b.collisionShape;
                RectangleShape rect = (RectangleShape) wb.collisionShape;
                return MathUtils.closestPoint(c, b.position, rect, wb.position).normalize();
            }
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
