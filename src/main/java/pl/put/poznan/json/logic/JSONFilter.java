package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

class JSONFilter extends JSONDecorator {
    public JSONFilter(JSONTools jsonTools) {
        super(jsonTools);
    }

    @Override
    public String processJSON(String jsonData) {
        Set<String> keysToFilter = getKeysFromUserInput();

        // Read the JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonData  );

            // Filter out the specified keys
            JsonNode filteredNode = filterKeys(rootNode, keysToFilter);

            // Write the filtered JSON to a file
            return filteredNode.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static Set<String> getKeysFromUserInput() {
        Set<String> keysToFilter = new HashSet<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the keys to filter (separated by commas):");
        String input = scanner.nextLine();

        String[] keys = input.split(",");
        for (String key : keys) {
            keysToFilter.add(key.trim());
        }

        scanner.close();
        return keysToFilter;
    }

    private static JsonNode filterKeys(JsonNode node, Set<String> keysToFilter) {
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;
            keysToFilter.forEach(objectNode::remove);
        } else if (node.isArray()) {
            node.forEach(childNode -> filterKeys(childNode, keysToFilter));
        }

        return node;
    }
}

