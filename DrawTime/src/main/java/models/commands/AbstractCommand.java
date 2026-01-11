package models.commands;

public abstract class AbstractCommand implements Command {

    @Override
    public void undo() {
        // Not undoable by default
    }

    @Override
    public void redo() {
        // Not redoable by default
    }
}
