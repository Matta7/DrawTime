package models.commands.concrete;

import controllers.Controller;
import models.commands.AbstractCommand;

public class PreviousImageCommand extends AbstractCommand {
    @Override
    public void execute() {
        Controller controller = Controller.getInstance();
        controller.getImageService().previousImage();
        controller.getTimer().reset();
    }
}
