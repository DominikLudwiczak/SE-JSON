package pl.put.poznan.json.logic;

/**
 * This is a simple class with a single static method that logs some messages
 * using SLF4J.
 */
public class InvalidJSONException extends Exception {
    public InvalidJSONException(String message) {
        super(message);
    }
}
