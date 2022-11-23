package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringSchemaTest {
    StringSchema schema;

    @BeforeEach
    void setUp() {
        schema = new Validator().string();
    }

    @Test
    void testIsValid_ShouldTrue_WhenNoRequiredInputText() {
        assertTrue(schema.isValid("text"));
    }

    @Test
    void testIsValid_ShouldTrue_WhenNoRequiredInputEmpty() {
        assertTrue(schema.isValid(""));
    }

    @Test
    void testIsValid_ShouldTrue_WhenNoRequiredInputNull() {
        assertTrue(schema.isValid(null));
    }

    @Test
    void testIsValid_ShouldTrue_WhenNoRequiredInputInteger() {
        assertTrue(schema.isValid(123));
    }

    @Test
    void testIsValid_ShouldTrue_WhenRequiredInputText() {
        schema.required();
        assertTrue(schema.isValid("text"));
    }

    @Test
    void testIsValid_ShouldFalse_WhenRequiredInputEmpty() {
        schema.required();
        assertFalse(schema.isValid(""));
    }

    @Test
    void testIsValid_ShouldFalse_WhenRequiredInputNull() {
        schema.required();
        assertFalse(schema.isValid(null));
    }

    @Test
    void testIsValid_ShouldFalse_WhenRequiredInputInteger() {
        schema.required();
        assertFalse(schema.isValid(123));
    }

    @Test
    void testIsValid_ShouldTrue_WhenMinLengthInputText() {
        schema.required();
        schema.minLength(3);
        assertTrue(schema.isValid("text text"));
    }

    @Test
    void testIsValid_ShouldFalse_WhenMinLengthInputText() {
        schema.required();
        schema.minLength(300);
        assertFalse(schema.isValid("text text"));
    }

    @Test
    void testIsValid_ShouldTrue_WhenContainsInputText() {
        schema.required();
        schema.contains("text");
        assertTrue(schema.isValid("text text"));
    }

    @Test
    void testIsValid_ShouldFalse_WhenContainsInputText() {
        schema.required();
        schema.contains("texttexttext");
        assertFalse(schema.isValid("text text"));
    }

}