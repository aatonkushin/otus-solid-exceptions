package org.example;

import org.example.exception.PropertyNotFoundException;

public class RotateAdapter implements Rotatable {
    UObject obj;

    public RotateAdapter(UObject obj) {
        this.obj = obj;
    }

    @Override
    public int getDirection() {
        return (int) getProperty("direction", obj);
    }

    @Override
    public int getAngularVelocity() {
        return (int) getProperty("angularVelocity", obj);
    }

    @Override
    public void setDirection(int newVal) {
        obj.setProperty("direction", newVal);
    }

    @Override
    public int getDirectionsNumber() {
        return (int) getProperty("directionsNumber", obj);
    }

    private Object getProperty(String propertyName, UObject obj) {
        Object retVal = obj.getProperty(propertyName);

        if (retVal == null)
            throw new PropertyNotFoundException(propertyName);

        return retVal;
    }
}
