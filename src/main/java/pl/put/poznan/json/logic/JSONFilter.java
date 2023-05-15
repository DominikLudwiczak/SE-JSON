package pl.put.poznan.json.logic;

class JSONFilter extends JSONDecorator {
    public JSONFilter(JSONTools jsonTools) {
        super(jsonTools);
    }

    @Override
    public String processJSON(String jsonData) {
        // Delegate processing to the underlying component
        String processedJSON = super.processJSON(jsonData);

        // Add JSON filtering logic here
        String filteredJSON = "<filtered JSON>";

        return filteredJSON;
    }
}