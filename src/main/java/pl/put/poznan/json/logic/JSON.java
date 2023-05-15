package pl.put.poznan.json.logic;

/**
 * This is a class that represents a JSON object.
 */
public class JSON {

    private final String[] JSON_data;

    /**
     * This is a constructor for the JSON class.
     * 
     * @param JSON_input This is the JSON object that is going to be stored in the
     *                   class.
     */
    public JSON(String[] JSON_input) {
        this.JSON_data = JSON_input;
    }

    public String transform(String text) {
        return text.toUpperCase();
    }
}
