package pl.put.poznan.json.logic;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONValidator {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Validates the specified JSON string.
     *
     * @param jsonString the JSON string to validate
     * @throws InvalidJSONException    if the JSON string is invalid
     * @throws JsonMappingException    if the JSON string is invalid
     * @throws JsonProcessingException if the JSON string is invalid
     */
    public static void validate(String jsonString)
            throws InvalidJSONException, JsonMappingException, JsonProcessingException {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            // JSON is valid
        } catch (JsonParseException e) {
            // JSON is invalid
            throw new InvalidJSONException("Invalid JSON: " + e.getMessage());
        }
    }
}
