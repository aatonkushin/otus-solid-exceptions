package org.example;

public class LogCommand implements Command {

    Command cmd;
    Exception ex;

    public LogCommand(Command cmd, Exception ex) {
        this.cmd = cmd;
        this.ex = ex;
    }

    @Override
    public void execute() {
        System.out.println(cmd.getClass().toString() + ", " + ex.getMessage());
    }
}
