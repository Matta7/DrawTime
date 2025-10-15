package models.commands.concrete;

import controllers.Controller;
import models.commands.AbstractCommand;
import models.objects.Parameters;


public class StartCommand extends AbstractCommand {

    @Override
    public void execute() {
        Parameters parameters = Controller.getInstance().getParameters();

        System.out.println(parameters.areValid());
        // TODO : open new frame or open dialog if invalid
    }
}
