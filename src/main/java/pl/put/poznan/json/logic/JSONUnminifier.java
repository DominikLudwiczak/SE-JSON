package pl.put.poznan.json.logic;

/**
 * This is a simple class with a single static method that validates JSON
 * strings.
 */
public class JSONUnminifier extends JSONDecorator {
    public JSONUnminifier(JSONProcessor jsonProcessor) {
        super(jsonProcessor);
    }

    @Override
    public String processJSON(String jsonData) {
        // Delegate processing to the underlying component
        String processedJSON = super.processJSON(jsonData);

        // Unminify the JSON
        String unminifiedJSON = unminifyJSON(processedJSON);

        return unminifiedJSON;
    }

    /**
     * Unminifies the specified JSON string.
     *
     * @param minifiedJSON the JSON string to unminify
     * @return the unminified JSON string
     */
    private String unminifyJSON(String minifiedJSON) {
        StringBuilder unminifiedJSONBuilder = new StringBuilder();
        int indentationLevel = 0;
        boolean insideString = false;

        for (int i = 0; i < minifiedJSON.length(); i++) {
            char currentChar = minifiedJSON.charAt(i);

            if (insideString) {
                unminifiedJSONBuilder.append(currentChar);
                if (currentChar == '"') {
                    // Check if the quote character is escaped
                    if (minifiedJSON.charAt(i - 1) != '\\') {
                        insideString = false;
                    }
                }
            } else {
                switch (currentChar) {
                    case '{':
                    case '[':
                        unminifiedJSONBuilder.append(currentChar);
                        unminifiedJSONBuilder.append('\n');
                        indentationLevel++;
                        appendIndentation(unminifiedJSONBuilder, indentationLevel);
                        break;
                    case '}':
                    case ']':
                        unminifiedJSONBuilder.append('\n');
                        indentationLevel--;
                        appendIndentation(unminifiedJSONBuilder, indentationLevel);
                        unminifiedJSONBuilder.append(currentChar);
                        break;
                    case ',':
                        unminifiedJSONBuilder.append(currentChar);
                        unminifiedJSONBuilder.append('\n');
                        appendIndentation(unminifiedJSONBuilder, indentationLevel);
                        break;
                    case ':':
                        unminifiedJSONBuilder.append(currentChar);
                        unminifiedJSONBuilder.append(' ');
                        break;
                    case '"':
                        unminifiedJSONBuilder.append(currentChar);
                        insideString = true;
                        break;
                    default:
                        if (!Character.isWhitespace(currentChar)) {
                            unminifiedJSONBuilder.append(currentChar);
                        }
                        break;
                }
            }
        }

        return unminifiedJSONBuilder.toString();
    }

    private void appendIndentation(StringBuilder stringBuilder, int indentationLevel) {
        stringBuilder.append("\t".repeat(Math.max(0, indentationLevel)));
    }
}