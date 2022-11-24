package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BaseSchemaTest {

    Validator validator;
    MapSchema schema;

    @BeforeEach
    void setUp() {
        validator = new Validator();
        schema = validator.map();

        Map<String, BaseSchema> shapeMapSchemas = new HashMap<>();
        shapeMapSchemas.put("name", validator.string().required());
        shapeMapSchemas.put("age", validator.number().positive());
        schema.shape(shapeMapSchemas);
    }

    @Test
    void testIsValid_ShouldTrue_WhenShapeInputMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Kolya");
        map.put("age", 100);
        assertTrue(schema.isValid(map));
    }

    @Test
    void testIsValid_ShouldTrue_WhenShapeInputMapWithNull() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Maya");
        map.put("age", null);
        assertTrue(schema.isValid(map));
    }

    @Test
    void testIsValid_ShouldFalse_WhenShapeInputMapWithEmptyAndNull() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "");
        map.put("age", null);
        assertFalse(schema.isValid(map));
    }

    @Test
    void testIsValid_ShouldFalse_WhenShapeInputMapWithNegativeNumber() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Valya");
        map.put("age", -5);
        assertFalse(schema.isValid(map));
    }
}