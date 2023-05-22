package pl.put.poznan.json.logic;

import java.util.Set;

/**
 * This is a simple interface with a single method that processes JSON strings.
 */
public interface JSONProcessor {
    String processJSON(String jsonData);
    String processJSON(String jsonData, String jsonData2);
    String processJSON(String jsonData, Set<String> keys);
}