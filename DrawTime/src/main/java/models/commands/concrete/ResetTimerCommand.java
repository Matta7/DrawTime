package models.commands.concrete;

import controllers.Controller;
import models.commands.AbstractCommand;

public class ResetTimerCommand extends AbstractCommand {
    @Override
    public void execute() {
        Controller controller = Controller.getInstance();
        controller.getTimer().reset();
    }
}
