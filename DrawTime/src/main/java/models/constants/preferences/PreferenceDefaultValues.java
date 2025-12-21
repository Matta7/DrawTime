package models.constants.preferences;

import models.objects.PreferenceValue;

import static models.constants.preferences.PreferencesNameConstants.*;

public enum PreferenceDefaultValues {

    PARAM_ROOT_DIRECTORY_PATH_PREF_DEFAULT_VALUE(PARAM_ROOT_DIRECTORY_PATH_PREF, ""),
    PARAM_TIME_PER_IMAGE_PREF_DEFAULT_VALUE(PARAM_TIME_PER_IMAGE_PREF, 5);

    private final String preferenceName;
    private final PreferenceValue defaultValue;

    PreferenceDefaultValues(String preferenceName, Object defaultValue) {
        this.preferenceName = preferenceName;
        this.defaultValue = new PreferenceValue(defaultValue);
    }

    public String getPreferenceName() {
        return preferenceName;
    }

    public PreferenceValue getDefaultValue() {
        return defaultValue;
    }

    /**
     * Get a default value using its name
     *
     * @param preferenceName Name of the preference
     * @return The default value or null if not found
     */
    public static PreferenceValue getDefaultValue(String preferenceName) {
        for (PreferenceDefaultValues p : PreferenceDefaultValues.values()) {
            if (p.getPreferenceName().equals(preferenceName)) {
                return p.getDefaultValue();
            }
        }
        return null;
    }
}
