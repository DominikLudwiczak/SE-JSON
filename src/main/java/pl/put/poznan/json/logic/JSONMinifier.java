package pl.put.poznan.json.logic;

public class JSONMinifier extends JSONDecorator {

    public JSONMinifier() {
        super(jsonTools);
    }

    @Override
    public String processJSON(String jsonData) {
        // Delegate processing to the underlying component
        String processedJSON = super.processJSON(jsonData);

        // Add JSON minification logic here
        String minifiedJSON = "<minified JSON>";

        return minifiedJSON;
    }
}
