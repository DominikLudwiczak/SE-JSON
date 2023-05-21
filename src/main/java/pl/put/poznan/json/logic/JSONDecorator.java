package pl.put.poznan.json.logic;

abstract class JSONDecorator implements JSONTools {
    protected static JSONTools jsonTools;

    public JSONDecorator(JSONTools jsonTools) {
        JSONDecorator.jsonTools = jsonTools;
    }

    @Override
    public String processJSON(String jsonData) {
        return jsonTools.processJSON(jsonData);
    }
}
