package pl.put.poznan.json.logic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JSONMinifierTest {
    JSONProcessor processor;

    @BeforeEach
    public void setUp() {
        this.processor = mock(DefaultJSONProcessor.class);
        when(processor.processJSON(anyString())).thenAnswer(i -> i.getArguments()[0]);
        processor = new JSONMinifier(processor);
    }

    @Test
    public void testMinifyEmptyJsonObject() {
        String jsonData = "{}";
        String minifiedData = processor.processJSON(jsonData);

        assertEquals("{}", minifiedData);
    }

    @Test
    public void testMinifyJsonObjectWithSpacesAndNewlines() {
        String jsonData = "{\n   \"name\": \"John\",\n   \"age\": 30,\n   \"city\": \"New York\"\n}";
        String minifiedData = processor.processJSON(jsonData);

        assertEquals("{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}", minifiedData);
    }

    @Test
    public void testMinifyJsonArrayWithSpacesAndNewlines() {
        String jsonData = "[\n   1,\n   2,\n   3\n]";
        String minifiedData = processor.processJSON(jsonData);

        assertEquals("[1,2,3]", minifiedData);
    }

    @Test
    public void testMinifyComplexJsonObject() {
        String jsonData = "{\n   \"name\": \"John\",\n   \"age\": 30,\n   \"address\": {\n      \"street\": \"123 ABC Street\",\n      \"city\": \"New York\"\n   },\n   \"hobbies\": [\n      \"reading\",\n      \"traveling\",\n      \"photography\"\n   ]\n}";
        String minifiedData = processor.processJSON(jsonData);

        assertEquals(
                "{\"name\":\"John\",\"age\":30,\"address\":{\"street\":\"123 ABC Street\",\"city\":\"New York\"},\"hobbies\":[\"reading\",\"traveling\",\"photography\"]}",
                minifiedData);
    }

    @Test
    public void testMinifyInvalidJsonData() {
        String invalidData = "Invalid JSON";
        String minifiedData = processor.processJSON(invalidData);

        assertNull(minifiedData);
    }

}