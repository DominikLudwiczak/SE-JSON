/**
 * The JSONMinifier class is a decorator that performs JSON minification by removing unnecessary whitespaces and newlines
 * from the JSON data.
 */
package pl.put.poznan.json.logic;

public class JSONMinifier extends JSONDecorator {

    /**
     * Constructs a new JSONMinifier object with the specified JSONProcessor instance.
     *
     * @param jsonProcessor The JSONProcessor instance to be decorated.
     */
    public JSONMinifier(JSONProcessor jsonProcessor) {
        super(jsonProcessor);
    }

    /**
     * Processes the JSON data by removing whitespaces and newlines.
     *
     * @param jsonData The JSON data to be processed.
     * @return The processed JSON data with whitespaces and newlines removed.
     */
    @Override
    public String processJSON(String jsonData) {

        jsonData = super.processJSON(jsonData);
        jsonData = jsonData.replaceAll("\\s+", "");
        jsonData = jsonData.replaceAll("\\r|\\n", "");

        return jsonData;
    }
}
