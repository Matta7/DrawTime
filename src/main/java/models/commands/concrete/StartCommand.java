package models.commands.concrete;

import controllers.Controller;
import models.commands.AbstractCommand;
import models.objects.Parameters;


public class StartCommand extends AbstractCommand {

    @Override
    public void execute() {
        Controller controller = Controller.getInstance();
        Parameters parameters = controller.getParameters();

        if (parameters.isValid()) {
            controller.StartDrawing();
        } else {
            // TODO : open new frame or open dialog if invalid
        }
    }
}
