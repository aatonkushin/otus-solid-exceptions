package org.example;

/**
 * Универсальный объект
 */
public interface UObject {
    Object getProperty(String key);

    void setProperty(String key, Object newValue);
}
