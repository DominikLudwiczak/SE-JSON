package pl.put.poznan.json.logic;

import java.util.Set;

abstract class JSONDecorator implements JSONProcessor {
    protected JSONProcessor jsonProcessor;

    public JSONDecorator(JSONProcessor jsonProcessor) {
        this.jsonProcessor = jsonProcessor;
    }

    @Override
    public String processJSON(String jsonData) {
        return jsonProcessor.processJSON(jsonData);
    }

    @Override
    public String processJSON(String jsonData, String jsonData2) {
        return jsonProcessor.processJSON(jsonData);
    }

    @Override
    public String processJSON(String jsonData, Set<String> keys) {
        return jsonProcessor.processJSON(jsonData);
    }
}
