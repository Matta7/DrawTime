package models.services;

import models.constants.project.EProperties;

import java.io.IOException;
import java.util.Properties;

public class PropertyService {

    private static final String PROPERTIES_FILE = "application.properties";

    private Properties properties;

    public PropertyService() {
        try {
            properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILE));
        } catch (IOException _) {
            properties = null;
        }
    }

    public String getProperty(String property) {
        if (properties != null) {
            return properties.getProperty(property, "");
        }
        return "";
    }

    public String getProperty(EProperties property) {
        if (properties != null) {
            return properties.getProperty(property.toString(), "");
        }
        return "";
    }
}
