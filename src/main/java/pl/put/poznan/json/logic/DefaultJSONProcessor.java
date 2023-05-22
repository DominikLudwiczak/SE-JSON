package pl.put.poznan.json.logic;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Set;

public class DefaultJSONProcessor implements JSONProcessor {

    @Override
    public String processJSON(String jsonData) {

        try {
            JSONValidator.validate(jsonData);
        } catch (JsonProcessingException | InvalidJSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        // Implement your JSON processing logic here
        return jsonData;
    }

    @Override
    public String processJSON(String jsonData, String jsonData2) {

        try {
            JSONValidator.validate(jsonData);
        } catch (JsonProcessingException | InvalidJSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return jsonData;
    }

    @Override
    public String processJSON(String jsonData, Set<String> keys) {

        try {
            JSONValidator.validate(jsonData);
        } catch (JsonProcessingException | InvalidJSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return jsonData;
    }
}
