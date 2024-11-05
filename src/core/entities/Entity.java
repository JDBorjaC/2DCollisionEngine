
package core.entities;

import java.awt.Graphics;
import core.shapes.Shape;
import utils.Vector2D;

public abstract class Entity {
    public Vector2D position;
    public Vector2D velocity;
    public Vector2D acceleration;
    public float mass;
    public Shape collisionShape;

    public Entity(float mass, Shape collisionShape) {
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.acceleration = new Vector2D();
        this.mass = mass;
        this.collisionShape = collisionShape;
    }

    public Entity(Vector2D position, float mass, Shape collisionShape) {
        this.position = position;
        this.velocity = new Vector2D();
        this.acceleration = new Vector2D();
        this.mass = mass;
        this.collisionShape = collisionShape;
    }

    public Entity(Vector2D position, Vector2D velocity, float mass, Shape collisionShape) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new Vector2D();
        this.mass = mass;
        this.collisionShape = collisionShape;
    }

    public Entity(Vector2D position, Vector2D velocity, Vector2D acceleration, float mass, Shape collisionShape) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.mass = mass;
        this.collisionShape = collisionShape;
    }
    
    
    
    public void applyForce(Vector2D force) {
        Vector2D forceAcceleration = force.times(1/mass); //a = F/m;
        acceleration = acceleration.add(forceAcceleration);
    }
    
    public void applyImpulse(Vector2D impulse) {
        Vector2D impulseVelocity = impulse.times(1/mass); //v = J/m = (F*deltaTime)/m
        velocity = velocity.add(impulseVelocity);
    }
    
    public void update(float deltaTime) {
        position = position.add(velocity.times(deltaTime));
        velocity = velocity.add(acceleration.times(deltaTime));
    }

    public void adjustPosition(Vector2D offset) {
        this.position = position.add(offset);
    }
    
    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void adjustVelocity(Vector2D offset) {
        this.velocity = velocity.add(offset);
    }
    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }


    public void setAcceleration(Vector2D acceleration) {
        this.acceleration = acceleration;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public void setCollisionShape(Shape collisionShape) {
        this.collisionShape = collisionShape;
    }
    
    
    
    
    public abstract void draw(Graphics g);
    
    
    
}
