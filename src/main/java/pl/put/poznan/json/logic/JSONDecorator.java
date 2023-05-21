package pl.put.poznan.json.logic;

abstract class JSONDecorator implements JSONProcessor {
    protected JSONProcessor jsonProcessor;

    public JSONDecorator(JSONProcessor jsonProcessor) {
        this.jsonProcessor = jsonProcessor;
    }

    @Override
    public String processJSON(String jsonData) {
        return jsonProcessor.processJSON(jsonData);
    }
}
