package pl.put.poznan.json.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JSONSelectFilterTest {
    JSONProcessor Processor;
    private String basic = "{\"name\":\"John Doe\",\"age\":30,\"city\":\"New York\"}";
    private String nested = "{\"name\":\"John Doe\",\"age\":30,\"address\":{\"street\":\"123 Main St\",\"city\":\"New York\",\"country\":\"USA\"}}";

    private String veryNested = "{\"level1\":{\"level2\":{\"level3\":{\"level4\":{\"level5\":{\"level6\":{\"level7\":{\"value\":\"Hello, nested JSON!\"}}}}}}}}";

    @BeforeEach
    public void setUp(){
        this.Processor = mock(DefaultJSONProcessor.class);
        when(Processor.processJSON(anyString())).thenAnswer(i -> i.getArguments()[0]);
        Processor = new JSONSelectFilter(Processor);
    }

    @Test
    void TestNoKeys(){
        assertEquals(Processor.processJSON(basic, Collections.emptySet()), "{}");
    }

    @Test
    void TestAllKeys(){
        HashSet<String> keys = new HashSet<>();
        keys.addAll(Arrays.asList("name", "age", "city"));

        assertEquals(Processor.processJSON(basic, keys), basic);
    }

    @Test
    void FilterOneNested(){
        String ans = "{\"name\":\"John Doe\",\"age\":30,\"address\":{\"city\":\"New York\",\"country\":\"USA\"}}";

        HashSet<String> keys = new HashSet<>();
        keys.addAll(Arrays.asList("name", "age", "address", "city", "country"));

        assertEquals(Processor.processJSON(nested, keys), ans);
    }

    @Test
    void FilterSubTree(){
        String ans = "{\"name\":\"John Doe\",\"age\":30}";

        HashSet<String> keys = new HashSet<>();
        keys.addAll(Arrays.asList("name", "age"));

        assertEquals(Processor.processJSON(nested, keys), ans);
    }

    @Test
    void FilterVeryNested(){
        String ans = "{\"level1\":{\"level2\":{\"level3\":{\"level4\":{\"level5\":{\"level6\":{\"level7\":{}}}}}}}}";

        HashSet<String> keys = new HashSet<>();
        keys.addAll(Arrays.asList("level1", "level2", "level3", "level4", "level5", "level6", "level7"));

        assertEquals(Processor.processJSON(veryNested, keys), ans);
    }
}