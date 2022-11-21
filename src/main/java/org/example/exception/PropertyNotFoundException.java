package org.example.exception;

public class PropertyNotFoundException extends RuntimeException {
    public PropertyNotFoundException(String propertyName) {
        super("Невозможно прочитать значение свойства " + propertyName);
    }
}
