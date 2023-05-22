package pl.put.poznan.json.logic;

import java.util.Set;

public class DefaultJSONProcessor implements JSONProcessor {

    @Override
    public String processJSON(String jsonData) {
        JSONValidator.validate(jsonData);
        return jsonData;
    }

    @Override
    public String processJSON(String jsonData, String jsonData2) {
        JSONValidator.validate(jsonData);
        return jsonData;
    }

    @Override
    public String processJSON(String jsonData, Set<String> keys) {
        JSONValidator.validate(jsonData);
        return jsonData;
    }
}
