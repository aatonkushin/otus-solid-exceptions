package org.example;

public class Vector {
    private final int x;
    private final int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Vector plus(Vector position, Vector velocity){
        return new Vector(
                position.getX()+ velocity.getX(),
                position.getY() + velocity.getY()
        );
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
