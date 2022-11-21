package org.example.exception;

public class CanNotRotateException extends RuntimeException{
    public CanNotRotateException() {
        super("Невозможно повернуть объект с нулевой скоростью");
    }
}
