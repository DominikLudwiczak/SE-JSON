package pl.put.poznan.json.logic;

public class JSONFormatter extends JSONDecorator {

    public JSONFormatter(JSONTools jsonTools) {
        super(jsonTools);
    }

    @Override
    public String processJSON(String jsonData) {
        // Delegate processing to the underlying component
        String processedJSON = super.processJSON(jsonData);

        // Add JSON formatting logic here
        String formattedJSON = "<formatted JSON>";

        return formattedJSON;
    }
}
