package org.example.exception;

public class CanNotMoveException extends RuntimeException{
    public CanNotMoveException(){
        super("Невозможно переместить объект с нулевой скоростью");
    }
}
