package org.example;

public class SecondRetryCommand implements Command {
    Command cmd;

    public SecondRetryCommand(Command cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        System.out.println("SecondRetryCommand");
        cmd.execute();
    }
}
