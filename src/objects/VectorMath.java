package objects;


public class VectorMath {


    public VectorMath() {
    }

    public static void copyVector(Vector2f v1, Vector2f v2){
        v1.setX(v2.getX());
        v1.setY(v2.getY());
    }

    public static void printVector(Vector2f v){
        System.out.println("x: " + v.getX() + " y: " + v.getY());
    }
}