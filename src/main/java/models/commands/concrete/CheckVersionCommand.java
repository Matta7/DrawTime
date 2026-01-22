package models.commands.concrete;

import controllers.Controller;
import models.commands.AbstractCommand;
import models.constants.project.EProperties;
import models.services.APIRequestService;
import models.services.PropertyService;

import java.io.IOException;

public class CheckVersionCommand extends AbstractCommand {

    @Override
    public void execute() {
        try {
            PropertyService propertyService = new PropertyService();
            String currentVersion = String.format("v%s", propertyService.getProperty(EProperties.APPLICATION_VERSION));

            APIRequestService apiRequestService = new APIRequestService();
            String latestVersion = apiRequestService.getLatestReleaseVersion();

            if (!latestVersion.equals(currentVersion)) {
                Controller.getInstance().openNewVersionDialog();
            }
        } catch (IOException _) {
            // Nothing to do
        }
    }
}
