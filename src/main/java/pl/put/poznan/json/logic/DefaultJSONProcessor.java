package pl.put.poznan.json.logic;

import com.fasterxml.jackson.core.JsonProcessingException;

public class DefaultJSONProcessor implements JSONProcessor {

    @Override
    public String processJSON(String jsonData) {

        try {
            JSONValidator.validate(jsonData);
        } catch (JsonProcessingException | InvalidJSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Implement your JSON processing logic here
        return jsonData;
    }
}
