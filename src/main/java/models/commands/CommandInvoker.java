package models.commands;

public class CommandInvoker {

    private static final CommandInvoker instance = new CommandInvoker();

    private CommandHistory commandHistory;

    private Command command;

    private CommandInvoker() {
        commandHistory = new CommandHistory();
    }

    public static CommandInvoker getInstance() {
        return instance;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        if (command != null) {
            command.execute();
            command = null;
        }
    }

    public void executeCommand(Command command) {
        this.command = command;
        executeCommand();
    }

    public void undoLastCommand() {

    }

    public void redo() {

    }
}
