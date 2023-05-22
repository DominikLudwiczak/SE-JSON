/**
 * The JSONMinifier class is a decorator that performs JSON minification by removing unnecessary whitespaces and newlines
 * from the JSON data.
 */
package pl.put.poznan.json.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONMinifier extends JSONDecorator {

    /**
     * Constructs a new JSONMinifier object with the specified JSONProcessor
     * instance.
     *
     * @param jsonProcessor The JSONProcessor instance to be decorated.
     */
    public JSONMinifier(JSONProcessor jsonProcessor) {
        super(jsonProcessor);
    }

    /**
     * Processes the jsonData into minified JSON data.
     *
     * @param jsonData The JSON data to be processed.
     * @return The processed JSON data with whitespaces and newlines removed.
     */
    @Override
    public String processJSON(String jsonData) {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode;
        try {
            rootNode = objectMapper.readTree(jsonData);
            return rootNode.toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
