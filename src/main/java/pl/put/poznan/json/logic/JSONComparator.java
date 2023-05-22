package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Scanner;

/**
 * The JSONComparator class is responsible for comparing two JSON files and
 * finding their differences.
 * It extends the {@link JSONDecorator} class and adds comparing functionality.
 */
public class JSONComparator extends JSONDecorator {
    private static boolean isDifferent = false;

    private String message;

    public JSONComparator(JSONProcessor jsonProcessor) {
        super(jsonProcessor);
    }

    /**
     * Ask user for the second JSON file and call CompareJSON method
     * 
     * @param jsonData the JSON data to process
     * @return unchanged jsonData
     */
    @Override
    public String processJSON(String jsonData) {
        System.out.println("Enter the content of the second JSON file:");

        Scanner scanner = new Scanner(System.in);
        String jsonData2 = "__invalid__";
        String line;

        while (!JSONValidator.validate(jsonData2)) {

            StringBuilder json = new StringBuilder();

            System.out.println("Paste valid JSON, and then press enter:\n");
            while (!(line = scanner.nextLine()).isEmpty()) {
                json.append(line).append("\n");
            }
            System.out.println("json: " + json);
            json.setLength(json.length() - 1);
            jsonData2 = json.toString();
        }
        return compareJSON(jsonData, jsonData2);
    }

    /**
     * Call CompareJSON method
     * 
     * @param jsonData  the JSON data to process
     * @param jsonData2 second JSON data to process
     * @return unchanged jsonData
     */
    @Override
    public String processJSON(String jsonData, String jsonData2) {
        return compareJSON(jsonData, jsonData2);
    }

    /**
     * Processes the JSON data by comparing it with the content of the second JSON
     * file.
     * The differences between the two JSON files are printed to the console.
     *
     * @param jsonData  The JSON data to be processed.
     * @param jsonData2 Second JSON data to be compared.
     * @return message containing info about differences between files.
     */
    public String compareJSON(String jsonData, String jsonData2) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode file1Node = objectMapper.readTree(jsonData);
            JsonNode file2Node = objectMapper.readTree(jsonData2);

            /*
             * Call compareJSONNodes twice with different node as first
             * each time to make sure it covers all nodes from both files
             */
            message = "";
            compareJSONNodes(file1Node, file2Node, "");
            compareJSONNodes(file2Node, file1Node, "");
            if (!isDifferent) {
                System.out.println("Entered JSON files are the same :o\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        isDifferent = false;
        return message;
    }

    /**
     * Recursively compares the nodes of the two JSON files and prints the
     * differences found.
     *
     * @param node1 The current node from the first JSON file.
     * @param node2 The current node from the second JSON file.
     * @param path  The path of the current node in the JSON structure.
     * @return message containing info about differences between files.
     */
    private String compareJSONNodes(JsonNode node1, JsonNode node2, String path) {
        if (node1.equals(node2)) {
            return message;
        }

        if (!node1.isContainerNode() || !node2.isContainerNode()) {
            isDifferent = true;
            message += "Difference at path: " + path + "\n";
            message += path + ": " + node1 + "\n";
            message += path + ": " + node2 + "\n";
            return message;
        }

        ObjectNode objectNode1 = (ObjectNode) node1;
        ObjectNode objectNode2 = (ObjectNode) node2;

        objectNode1.fieldNames().forEachRemaining(fieldName -> {
            String fieldPath = path.isEmpty() ? fieldName : path + "." + fieldName;
            JsonNode childNode1 = objectNode1.get(fieldName);
            JsonNode childNode2 = objectNode2.get(fieldName);
            compareJSONNodes(childNode1, childNode2, fieldPath);
        });
        return message;
    }
}
