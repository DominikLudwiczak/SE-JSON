package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Scanner;

/**
 * The JSONComparator class is responsible for comparing two JSON files and finding their differences.
 * It extends the {@link JSONDecorator} class and adds comparing functionality.
 */
public class JSONComparator extends JSONDecorator {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean isDifferent = false;

    public JSONComparator(JSONProcessor jsonProcessor) {
        super(jsonProcessor);
    }

    /**
     * Processes the JSON data by comparing it with the content of the second JSON file entered by the user.
     * The differences between the two JSON files are printed to the console.
     *
     * @param jsonData The JSON data to be processed.
     * @return The processed JSON data.
     */
    @Override
    public String processJSON(String jsonData) {
        System.out.println("Enter the content of the second JSON file:");
        StringBuilder json = new StringBuilder();
        String line;

        while (!(line = scanner.nextLine()).isEmpty()) {
            json.append(line).append("\n");
        }
        json.setLength(json.length() - 1);
        String jsonData2 = json.toString();
        /* Validate Entered JSON file

        JSONValidator valid = new JSONValidator();

        try{
            valid.validate(jsonData2);
        }
        catch (InvalidJSONException e) {
            throw new RuntimeException(e);
        }
        */

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode file1Node = objectMapper.readTree(jsonData);
            JsonNode file2Node = objectMapper.readTree(jsonData2);

            /*
            Call compareJSONNodes twice with different node as first
            each time to make sure it covers all nodes from both files
             */
            compareJSONNodes(file1Node, file2Node, "");
            compareJSONNodes(file2Node, file1Node, "");
            if (!isDifferent) {
                System.out.println("Entered JSON files are the same :o\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        isDifferent = false;
        return jsonData;
    }

    /**
     * Recursively compares the nodes of the two JSON files and prints the differences found.
     *
     * @param node1 The current node from the first JSON file.
     * @param node2 The current node from the second JSON file.
     * @param path  The path of the current node in the JSON structure.
     */
    private static void compareJSONNodes(JsonNode node1, JsonNode node2, String path) {
        if (node1.equals(node2)) {
            return;
        }

        if (!node1.isContainerNode() || !node2.isContainerNode()) {
            isDifferent = true;
            System.out.println("Difference at path: " + path);
            System.out.println(path + ": " + node1);
            System.out.println(path + ": " + node2);
            System.out.println();
            return;
        }

        ObjectNode objectNode1 = (ObjectNode) node1;
        ObjectNode objectNode2 = (ObjectNode) node2;

        objectNode1.fieldNames().forEachRemaining(fieldName -> {
            String fieldPath = path.isEmpty() ? fieldName : path + "." + fieldName;
            JsonNode childNode1 = objectNode1.get(fieldName);
            JsonNode childNode2 = objectNode2.get(fieldName);
            compareJSONNodes(childNode1, childNode2, fieldPath);
        });
    }
}
