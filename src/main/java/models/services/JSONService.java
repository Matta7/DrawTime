package models.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JSONService {
    private JsonObject json;

    public JSONService() {}

    public JSONService(String json) {
        loadJSON(json);
    }

    /**
     * Load a String JSON
     *
     * @param json JSON to load as a String
     */
    public void loadJSON(String json) {
        this.json = new Gson().fromJson(json, JsonObject.class);
    }

    /**
     * Get a field's value
     *
     * @param fieldName Name of the field
     * @return Field value
     */
    public String getFieldValueAsString(String fieldName) {
        return this.json == null ? "" : this.json.get(fieldName).getAsString();
    }
}
