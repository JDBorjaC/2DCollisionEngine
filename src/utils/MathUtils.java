
package utils;

import core.shapes.CircleShape;
import core.shapes.RectangleShape;

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
    
    public static Vector2D closestPoint(CircleShape circle, Vector2D cPos, RectangleShape rect, Vector2D rPos){
        /*
        Stolen from
        https://youtu.be/_xj8FyG-aac?si=tqihBCJMaTNgNDBj
        Find the circle point that's the closest to the rectangle,
        Check if it's within the radius
         */

        Vector2D rightBottomEdge = rPos.add(new Vector2D(rect.width, rect.height));
        Vector2D closestPoint = MathUtils.clamp(cPos, rPos, rightBottomEdge);
        return closestPoint;
    }
}
