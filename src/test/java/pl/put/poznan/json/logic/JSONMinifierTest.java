package pl.put.poznan.json.logic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JSONMinifierTest {
    JSONProcessor jsonProcessor;
    JSONMinifier minifier;

    @BeforeEach
    void setUp() {
        this.minifier = new JSONMinifier(jsonProcessor);
    }

    @Test
    public void testMinifyEmptyJsonObject() {
        String jsonData = "{}";
        String minifiedData = minifier.processJSON(jsonData);

        assertEquals("{}", minifiedData);
    }

    @Test
    public void testMinifyJsonObjectWithSpacesAndNewlines() {
        String jsonData = "{\n   \"name\": \"John\",\n   \"age\": 30,\n   \"city\": \"New York\"\n}";
        String minifiedData = minifier.processJSON(jsonData);

        assertEquals("{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}", minifiedData);
    }

    @Test
    public void testMinifyJsonArrayWithSpacesAndNewlines() {
        String jsonData = "[\n   1,\n   2,\n   3\n]";
        String minifiedData = minifier.processJSON(jsonData);

        assertEquals("[1,2,3]", minifiedData);
    }

    @Test
    public void testMinifyComplexJsonObject() {
        String jsonData = "{\n   \"name\": \"John\",\n   \"age\": 30,\n   \"address\": {\n      \"street\": \"123 ABC Street\",\n      \"city\": \"New York\"\n   },\n   \"hobbies\": [\n      \"reading\",\n      \"traveling\",\n      \"photography\"\n   ]\n}";
        String minifiedData = minifier.processJSON(jsonData);

        assertEquals(
                "{\"name\":\"John\",\"age\":30,\"address\":{\"street\":\"123 ABC Street\",\"city\":\"New York\"},\"hobbies\":[\"reading\",\"traveling\",\"photography\"]}",
                minifiedData);
    }

    @Test
    public void testMinifyInvalidJsonData() {
        String invalidData = "Invalid JSON";
        String minifiedData = minifier.processJSON(invalidData);

        assertNull(minifiedData);
    }

}