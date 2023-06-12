package pl.put.poznan.json.logic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JSONUnminifierTest {
    JSONProcessor processor;

    @BeforeEach
    public void setUp() {
        this.processor = mock(DefaultJSONProcessor.class);
        when(processor.processJSON(anyString())).thenAnswer(i -> i.getArguments()[0]);
        processor = new JSONUnminifier(processor);
    }

    @Test
    public void testUnminifyEmptyJsonObject() {
        String minifiedData = "{}";
        String unminifiedData = processor.processJSON(minifiedData);

        assertEquals("{\n\t\n}", unminifiedData);
    }

    @Test
    public void testUnminifySimpleJsonObject() {
        String minifiedData = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";
        String unminifiedData = processor.processJSON(minifiedData);

        assertEquals("{\n\t\"name\": \"John\",\n\t\"age\": 30,\n\t\"city\": \"New York\"\n}", unminifiedData);
    }

    @Test
    public void testUnminifyComplexJsonObject() {
        String minifiedData = "{\"name\":\"John\",\"age\":30,\"address\":{\"street\":\"123 ABC Street\",\"city\":\"New York\"},\"hobbies\":[\"reading\",\"traveling\",\"photography\"]}";
        String unminifiedData = processor.processJSON(minifiedData);

        assertEquals(
                "{\n\t\"name\": \"John\",\n\t\"age\": 30,\n\t\"address\": {\n\t\t\"street\": \"123 ABC Street\",\n\t\t\"city\": \"New York\"\n\t},\n\t\"hobbies\": [\n\t\t\"reading\",\n\t\t\"traveling\",\n\t\t\"photography\"\n\t]\n}",
                unminifiedData);
    }

    @Test
    public void testUnminifyNestedJsonArray() {
        String minifiedData = "[1,2,[3,4,[5,6,[7,8]]]]";
        String unminifiedData = processor.processJSON(minifiedData);

        assertEquals(
                "[\n\t1,\n\t2,\n\t[\n\t\t3,\n\t\t4,\n\t\t[\n\t\t\t5,\n\t\t\t6,\n\t\t\t[\n\t\t\t\t7,\n\t\t\t\t8\n\t\t\t]\n\t\t]\n\t]\n]",
                unminifiedData);
    }

    @Test
    public void testUnminifyInvalidJsonData() {
        String invalidData = "Invalid JSON";
        String unminifiedData = processor.processJSON(invalidData);

        assertNull(unminifiedData);
    }
}