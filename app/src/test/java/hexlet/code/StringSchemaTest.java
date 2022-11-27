package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {
    private StringSchema schema;

    @BeforeEach
    void setUp() {
        schema = new Validator().string();
    }

    @Test
    void testIsValidShouldTrueWhenNoRequiredInputText() {
        assertTrue(schema.isValid("text"));
    }

    @Test
    void testIsValidShouldTrueWhenNoRequiredInputEmpty() {
        assertTrue(schema.isValid(""));
    }

    @Test
    void testIsValidShouldTrueWhenNoRequiredInputNull() {
        assertTrue(schema.isValid(null));
    }

    @Test
    void testIsValidShouldFalseWhenNoRequiredInputInteger() {
        assertFalse(schema.isValid(123));
    }

    @Test
    void testIsValidShouldTrueWhenRequiredInputText() {
        schema.required();
        assertTrue(schema.isValid("text"));
    }

    @Test
    void testIsValidShouldFalseWhenRequiredInputEmpty() {
        schema.required();
        assertFalse(schema.isValid(""));
    }

    @Test
    void testIsValidShouldFalseWhenRequiredInputNull() {
        schema.required();
        assertFalse(schema.isValid(null));
    }

    @Test
    void testIsValidShouldFalseWhenRequiredInputInteger() {
        schema.required();
        assertFalse(schema.isValid(123));
    }

    @Test
    void testIsValidShouldTrueWhenMinLengthInputText() {
        schema.required();
        schema.minLength(3);
        assertTrue(schema.isValid("text text"));
    }

    @Test
    void testIsValidShouldTrueWhenMinLengthWithReturnInputText() {
        schema.required();
        assertTrue(schema.minLength(3).isValid("text text"));
    }

    @Test
    void testIsValidShouldTrueWhenRequiredWithReturnInputText() {
        assertTrue(schema.required().minLength(3).isValid("text text"));
    }

    @Test
    void testIsValidShouldFalseWhenMinLengthInputText() {
        schema.required();
        schema.minLength(300);
        assertFalse(schema.isValid("text text"));
    }

    @Test
    void testIsValidShouldTrueWhenContainsInputText() {
        schema.required();
        schema.contains("text");
        assertTrue(schema.isValid("text text"));
    }

    @Test
    void testIsValidShouldFalseWhenContainsInputText() {
        schema.required();
        schema.contains("texttexttext");
        assertFalse(schema.isValid("text text"));
    }

}
