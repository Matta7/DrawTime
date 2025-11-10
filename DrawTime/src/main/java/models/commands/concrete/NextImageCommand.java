package models.commands.concrete;

import controllers.Controller;
import models.commands.AbstractCommand;

public class NextImageCommand extends AbstractCommand {
    @Override
    public void execute() {
        Controller controller = Controller.getInstance();
        controller.getImageService().nextImage();
        controller.getTimer().reset();
    }
}
