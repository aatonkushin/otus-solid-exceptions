package org.example;

import org.example.exception.PropertyNotFoundException;

public class MovableAdapter implements Movable {
    UObject obj;

    public MovableAdapter(UObject o) {
        this.obj = o;
    }

    @Override
    public Vector getPosition() {
        return (Vector) getProperty("position", obj);
    }

    @Override
    public Vector getVelocity() {
        return (Vector) getProperty("velocity", obj);
    }

    @Override
    public void setPosition(Vector newValue) {
        obj.setProperty("position", newValue);
    }

    private Object getProperty(String propertyName, UObject obj) {
        Object retVal = obj.getProperty(propertyName);

        if (retVal == null)
            throw new PropertyNotFoundException(propertyName);

        return retVal;
    }
}
