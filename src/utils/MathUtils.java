
package utils;

public final class MathUtils {
    
    //Mustn't be instantiated
    private MathUtils(){
    }
    
    public static float clamp(float value, float min, float max){
        return Math.max(min, Math.min(max, value));
    }
    
    public static Vector2D clamp(Vector2D vector, Vector2D min, Vector2D max){
        float clampedX = clamp(vector.x, min.x, max.x);
        float clampedY = clamp(vector.y, min.y, max.y);
        return new Vector2D(clampedX, clampedY);
    }
}
