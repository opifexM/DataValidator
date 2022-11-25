package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BaseSchemaTest {

    private Validator validator;
    private MapSchema schema;

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
    void testIsValidShouldTrueWhenShapeInputMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Kolya");
        map.put("age", 100);
        assertTrue(schema.isValid(map));
    }

    @Test
    void testIsValidShouldTrueWhenShapeInputMapWithContain() {
        schema = validator.map();

        Map<String, BaseSchema> shapeMapSchemas = new HashMap<>();
        shapeMapSchemas.put("name", validator.string().required().contains("oly"));
        shapeMapSchemas.put("age", validator.number().positive());
        schema.shape(shapeMapSchemas);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "Kolya");
        map.put("age", 100);
        assertTrue(schema.isValid(map));
    }

    @Test
    void testIsValidShouldTrueWhenShapeInputMapWithNull() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Maya");
        map.put("age", null);
        assertTrue(schema.isValid(map));
    }

    @Test
    void testIsValidShouldFalseWhenShapeInputMapWithEmptyAndNull() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "");
        map.put("age", null);
        assertFalse(schema.isValid(map));
    }

    @Test
    void testIsValidShouldFalseWhenShapeInputMapWithNegativeNumber() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Valya");
        map.put("age", -5);
        assertFalse(schema.isValid(map));
    }
}
