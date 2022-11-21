package org.example;

import org.example.exception.LogCommand;
import org.example.exception.PropertyNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.Queue;

public class AppTest {
    @Test
    void queue() {
        Queue<Command> queue = new LinkedList<>();
        ExceptionHandler exceptionHandler = new ExceptionHandler(queue);

        queue.add(getMoveCommand());                // команда движения
        queue.add(getMoveCommandWithException());   // команда движения с исключением
        queue.add(getRotateCommand());              // команда поворота
        queue.add(getRotateCommandWithException()); // команда поворота с исключением

        exceptionHandler.setup(
                Move.class.getName(),
                PropertyNotFoundException.class.getName(),
                (cmd, ex) -> queue.add(new LogCommand(cmd, ex))
        );

        exceptionHandler.setup(
                Rotate.class.getName(),
                PropertyNotFoundException.class.getName(),
                (Command cmd, Exception ex) -> queue.add(new FirstRetryCommand(cmd))
        );

        exceptionHandler.setup(
                FirstRetryCommand.class.getName(),
                PropertyNotFoundException.class.getName(),
                (cmd, ex) -> queue.add(new SecondRetryCommand(cmd))
        );

        exceptionHandler.setup(
                SecondRetryCommand.class.getName(),
                PropertyNotFoundException.class.getName(),
                (cmd, ex) -> queue.add(new LogCommand(cmd, ex))
        );

        while (queue.size() > 0) {
            Command cmd = queue.poll();

            try {
                cmd.execute();
            } catch (Exception ex) {
                exceptionHandler.handle(cmd, ex);
            }
        }
    }

    private UObject getMock() {
        UObject uObjectMock = Mockito.mock(UObject.class);

        Mockito.doAnswer(invocation -> {
            String name = (String) invocation.getArguments()[0];
            Object value = invocation.getArguments()[1];
            Mockito.when(uObjectMock.getProperty(name)).thenReturn(value);
            return null;
        }).when(uObjectMock).setProperty(Mockito.anyString(), Mockito.any());

        return uObjectMock;
    }

    private Command getMoveCommand() {
        UObject uObjectMock = getMock();

        uObjectMock.setProperty("position", new Vector(12, 5));
        uObjectMock.setProperty("velocity", new Vector(-7, 3));

        MovableAdapter adapter = new MovableAdapter(uObjectMock);
        return new Move(adapter);
    }

    private Command getMoveCommandWithException() {
        UObject uObjectMock = getMock();
        uObjectMock.setProperty("velocity", new Vector(-7, 3));

        MovableAdapter adapter = new MovableAdapter(uObjectMock);
        return new Move(adapter);
    }

    private Command getRotateCommand() {
        UObject uObjectMock = getMock();

        uObjectMock.setProperty("directionsNumber", 8);
        uObjectMock.setProperty("direction", 1);
        uObjectMock.setProperty("angularVelocity", 12);

        RotateAdapter adapter = new RotateAdapter(uObjectMock);
        return new Rotate(adapter);
    }

    private Command getRotateCommandWithException() {
        UObject uObjectMock = getMock();

        uObjectMock.setProperty("direction", 1);
        uObjectMock.setProperty("angularVelocity", 12);

        RotateAdapter adapter = new RotateAdapter(uObjectMock);
        return new Rotate(adapter);
    }
}
