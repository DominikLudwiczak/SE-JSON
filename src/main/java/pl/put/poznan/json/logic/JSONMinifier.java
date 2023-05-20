package pl.put.poznan.json.logic;

public class JSONMinifier extends JSONDecorator {

    public JSONMinifier(JSONTools jsonTools) {
        super(jsonTools);
    }

    @Override
    public String processJSON(String jsonData) {

        jsonData = super.processJSON(jsonData);
        jsonData = jsonData.replaceAll("\\s+", "");
        jsonData = jsonData.replaceAll("\\r|\\n", "");

        return jsonData;
    }
}
