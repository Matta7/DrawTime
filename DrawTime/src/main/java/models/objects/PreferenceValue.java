package models.objects;

public record PreferenceValue(Object value) {

    /**
     * Get the value as integer
     *
     * @return Value as integer
     */
    public int asInt() {
        return Integer.parseInt(value.toString());
    }

    /**
     * Get the value as String
     *
     * @return Value as String
     */
    public String asString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && value.getClass().equals(obj.getClass()) && value.equals(obj);
    }

    @Override
    public String toString() {
        return this.asString();
    }
}
