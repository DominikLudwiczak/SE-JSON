package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Filter;

/**
 * This class represents a decorator that filters specified keys from JSON data.
 * It extends the {@link JSONDecorator} class and adds filtering functionality.
 */
public class JSONFilter extends JSONDecorator {
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * Constructs a new JSONFilter object with the specified JSONProcessor instance.
     *
     * @param jsonProcessor the JSONProcessor instance to decorate
     */
    public JSONFilter(JSONProcessor jsonProcessor) {
        super(jsonProcessor);
    }

    /**
     * Asks user for keys to filter and calls processJSON method
     *
     * @param jsonData the JSON data to process
     * @return the filtered JSON data as a string
     */
    @Override
    public String processJSON(String jsonData) {
        Set<String> keys = getKeysFromUserInput();
        return FilterJSON(jsonData, keys);
    }

    /**
     * Calls processJSON method
     * @param jsonData the JSON data to process
     * @param keys keys to be filtered out
     * @return the filtered JSON data as a string
     */
    @Override
    public String processJSON(String jsonData, Set<String> keys){
        return FilterJSON(jsonData, keys);
    }

    /**
     * Processes the JSON data by filtering out the specified keys.
     *
     * @param jsonData the JSON data to process
     * @param keys keys to be filtered out
     * @return the filtered JSON data as a string
     */
    public String FilterJSON(String jsonData, Set<String> keys){
        // Read the JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonData);

            // Filter out the specified keys
            JsonNode filteredNode = filterKeys(rootNode, keys);

            // Write the filtered JSON to a file
            return filteredNode.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Prompts the user to enter the keys to filter and returns a set of filtered keys.
     *
     * @return a set of filtered keys entered by the user
     */
    private static Set<String> getKeysFromUserInput() {
        Set<String> keysToFilter = new HashSet<>();

        System.out.println("Enter the keys to filter (separated by commas):");
        String input = scanner.nextLine().trim();

        String[] keys = input.split(",");
        for (String key : keys) {
            keysToFilter.add(key.trim());
        }

        return keysToFilter;
    }

    /**
     * Recursively filters the specified keys from the JSON node.
     *
     * @param node the JSON node to filter
     * @param keysToFilter the set of keys to filter
     * @return the filtered JSON node
     */
    private static JsonNode filterKeys(JsonNode node, Set<String> keysToFilter) {
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;
            Set<String> keysToRemove = new HashSet<>();

            objectNode.fields().forEachRemaining(entry -> {
                String key = entry.getKey();
                JsonNode value = entry.getValue();
                if (keysToFilter.contains(key)) {
                    keysToRemove.add(key);
                } else {
                    filterKeys(value, keysToFilter);
                }
            });

            keysToRemove.forEach(objectNode::remove);
        } else if (node.isArray()) {
            node.forEach(childNode -> filterKeys(childNode, keysToFilter));
        }

        return node;
    }
}
