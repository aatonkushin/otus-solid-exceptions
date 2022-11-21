package org.example;

/**
 * Интерфейс для движения объекта
 */
public interface Movable {
    Vector getPosition();
    Vector getVelocity();
    void setPosition(Vector newValue);
}
