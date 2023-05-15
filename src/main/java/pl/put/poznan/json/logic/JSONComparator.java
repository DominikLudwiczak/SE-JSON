package pl.put.poznan.json.logic;

class JSONComparator extends JSONDecorator {
    public JSONComparator(JSONTools jsonTools) {
        super(jsonTools);
    }

    @Override
    public String processJSON(String jsonData) {
        // Delegate processing to the underlying component
        String processedJSON = super.processJSON(jsonData);

        // Add JSON comparison logic here
        String comparedJSON = "<compared JSON>";

        return comparedJSON;
    }
}