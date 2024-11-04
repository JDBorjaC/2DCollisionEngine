
package entities;

import utils.Vector2D;

public abstract class Entity {
    public Vector2D position;
    public Vector2D velocity;
    public Vector2D acceleration;
    public float mass;
    
    public Entity(float mass){
        this.mass = mass;
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.acceleration = new Vector2D();
    }
    
    public Entity(float mass, Vector2D position){
        this.mass = mass;
        this.position = position;
        this.velocity = new Vector2D();
        this.acceleration = new Vector2D();
    }
    public Entity(float mass, Vector2D position, Vector2D velocity){
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = new Vector2D();
    }
    public Entity(float mass, Vector2D position, Vector2D velocity, Vector2D acceleration){
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
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
    
    
}
