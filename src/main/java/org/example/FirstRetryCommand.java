package org.example;

public class FirstRetryCommand implements Command {
    Command command;
    public FirstRetryCommand(Command cmd) {
        this.command = cmd;
    }

    @Override
    public void execute() {
        System.out.println("FirstRetryCommand");
        this.command.execute();
    }
}
