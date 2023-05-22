package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Specification of {@link JSONFilter} class, filters out all keys except
 * provided ones
 */
public class JSONSelectFilter extends JSONFilter {

    /**
     * Constructs a new JSONFilter object with the specified JSONProcessor instance.
     *
     * @param jsonProcessor the JSONProcessor instance to decorate
     */
    public JSONSelectFilter(JSONProcessor jsonProcessor) {
        super(jsonProcessor);
    }

    /**
     * Asks user for keys and calls processJSON method
     *
     * @param jsonData the JSON data to process
     * @return the filtered JSON data as a string
     */
    @Override
    public String processJSON(String jsonData) {
        Set<String> keys = getKeysFromUserInput();
        return filterJSON(jsonData, keys);
    }

    /**
     * Calls processJSON method
     *
     * @param jsonData the JSON data to process
     * @param keys     keys not to be filtered out
     * @return the filtered JSON data as a string
     */
    @Override
    public String processJSON(String jsonData, Set<String> keys) {
        return filterJSON(jsonData, keys);
    }

    /**
     * Creates set of all keys from jsonData except provided ones and
     * calls filterJSON method from parent class
     *
     * @param jsonData the JSON data to process
     * @param keys     keys to be filtered out
     * @return the filtered JSON data as a string
     */
    @Override
    public String filterJSON(String jsonData, Set<String> keys) {
        Set<String> keysToRemove = new HashSet<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonData);
            extractKeyNames(rootNode, keysToRemove);
            keysToRemove.removeAll(keys);

            return super.filterJSON(jsonData, keysToRemove);
        } catch (Exception e) {
            FileLogger.logger.error(e.getStackTrace().toString());
            System.out.println("Error while filtering JSON file");
        }
        return "";
    }

    /**
     * Method to retrieve all keys from JSON file
     *
     * @param node     JSON file as JsonNode
     * @param keyNames Set to which keys will be extracted
     */
    private void extractKeyNames(JsonNode node, Set<String> keyNames) {
        Iterator<String> fieldNames = node.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            keyNames.add(fieldName);

            JsonNode fieldValue = node.get(fieldName);
            if (fieldValue.isObject()) {
                extractKeyNames(fieldValue, keyNames);
            }
        }
    }
}
