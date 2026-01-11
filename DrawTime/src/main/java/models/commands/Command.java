package models.commands;

public interface Command {
    /**
     * Execute the command
     */
    void execute();

    /**
     * Undo the command
     */
    void undo();

    /**
     * Redo the command
     */
    void redo();
}
