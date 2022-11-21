package org.example;

import org.example.exception.LogCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class ExceptionHandler {
    Queue<Command> queue;
    Map<String, Map<String, ExceptionAction>> map;

    public ExceptionHandler(Queue<Command> queue) {
        this.queue = queue;
        map = new HashMap<>();
    }

    public void setup(String commandClassName, String exceptionClassName, ExceptionAction exceptionAction) {
        Map<String, ExceptionAction> innerMap = map.get(commandClassName);

        if (innerMap == null) {
            innerMap = new HashMap<>();
            innerMap.put(exceptionClassName, exceptionAction);

            map.put(commandClassName, innerMap);
        } else {
            innerMap.put(exceptionClassName, exceptionAction);
        }
    }

    public void handle(Command command, Exception ex) {
        String commandClassName = command.getClass().getName();
        String exceptionClassName = ex.getClass().getName();

        Map<String, ExceptionAction> innerMap = map.get(commandClassName);

        if (innerMap != null) {
            ExceptionAction exceptionAction = innerMap.get(exceptionClassName);

            if (exceptionAction != null) {
                exceptionAction.execute(command, ex);
                return;
            }
        }

        // Если обработчик для команды и исключения не задан, то пишем в лог
        System.out.println("Обработчик не назначен");
        LogCommand log = new LogCommand(command, ex);
        queue.add(log);
    }
}

