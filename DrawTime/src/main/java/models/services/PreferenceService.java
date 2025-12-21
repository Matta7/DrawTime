package models.services;

import models.constants.preferences.PreferenceDefaultValues;
import models.objects.PreferenceValue;

import java.util.prefs.Preferences;

public class PreferenceService {

    private final Preferences prefs;

    public PreferenceService() {
        prefs = Preferences.userRoot().node(this.getClass().getName());
    }

    /**
     * Get a preference value using its key
     *
     * @param preferenceKey Key of the preference
     * @return The value of the preference, or its default value
     */
    public PreferenceValue getValue(String preferenceKey) {
        PreferenceValue defaultValue = PreferenceDefaultValues.getDefaultValue(preferenceKey);
        String defaultValueString = defaultValue == null ? "" : defaultValue.toString();

        return new PreferenceValue(prefs.get(preferenceKey, defaultValueString));
    }

    /**
     * Save a preference value in preferences
     *
     * @param preferenceKey Key of the preference to save
     * @param value Value to save
     */
    public void setValue(String preferenceKey, Object value) {
        switch (value) {
            case Integer intValue:
                prefs.putInt(preferenceKey, intValue);
                break;
            case Float floatValue:
                prefs.putFloat(preferenceKey, floatValue);
                break;
            case Long longValue:
                prefs.putLong(preferenceKey, longValue);
                break;
            case Double doubleValue:
                prefs.putDouble(preferenceKey, doubleValue);
                break;
            case Boolean booleanValue:
                prefs.putBoolean(preferenceKey, booleanValue);
                break;
            default:
                prefs.put(preferenceKey, value.toString());
        }
    }
}
