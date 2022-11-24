package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapSchemaTest {

    MapSchema schema;

    @BeforeEach
    void setUp() {
        schema = new Validator().map();
    }

    @Test
    void testIsValid_ShouldTrue_WhenNoRequiredInputNull() {
        assertTrue(schema.isValid(null));
    }

    @Test
    void testIsValid_ShouldFalse_WhenRequiredInputNull() {
        schema.required();
        assertFalse(schema.isValid(null));
    }

    @Test
    void testIsValid_ShouldTrue_WhenRequiredInputEmptyMap() {
        Map<String, String> map = new HashMap<>();
        schema.required();
        assertTrue(schema.isValid(map));
    }

    @Test
    void testIsValid_ShouldTrue_WhenRequiredInputMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        schema.required();
        assertTrue(schema.isValid(map));
    }

    @Test
    void testIsValid_ShouldTrue_WhenMinSizeInputMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        schema.required();
        schema.sizeof(3);
        assertTrue(schema.isValid(map));
    }

    @Test
    void testIsValid_ShouldFalse_WhenMinSizeInputLessMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        schema.required();
        schema.sizeof(3);
        assertFalse(schema.isValid(map));
    }
}