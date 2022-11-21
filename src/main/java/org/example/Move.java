package org.example;

import org.example.exception.CanNotMoveException;

/**
 * Операция движения
 */
public class Move implements Command {
    Movable m;

    public Move(Movable m) {
        this.m = m;
    }

    public void execute() {
        if (m.getVelocity().getX() == 0 && m.getVelocity().getY() == 0) {
            throw new CanNotMoveException();
        }

        m.setPosition(Vector.plus(m.getPosition(), m.getVelocity()));
        System.out.println("Move: " + m.getVelocity() + ", " + m.getVelocity());
    }
}
