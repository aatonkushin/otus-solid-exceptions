package org.example;

import org.example.exception.CanNotRotateException;

public class Rotate implements Command {
    Rotatable r;

    public Rotate(Rotatable r) {
        this.r = r;
    }

    public void execute() {
        if (r.getAngularVelocity() == 0) {
            throw new CanNotRotateException();
        }

        r.setDirection(
                (r.getDirection() + r.getAngularVelocity()) % r.getDirectionsNumber()
        );

        System.out.println("Rotate: " + r.getAngularVelocity() + ", " + r.getDirection());
    }
}
