package models.commands.concrete;

import controllers.Controller;
import models.commands.AbstractCommand;

public class PauseTimerCommand extends AbstractCommand {
    @Override
    public void execute() {
        Controller controller = Controller.getInstance();
        controller.getTimer().pause();
    }
}
