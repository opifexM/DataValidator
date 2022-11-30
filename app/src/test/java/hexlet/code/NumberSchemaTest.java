package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {

    private NumberSchema schema;

    @BeforeEach
    void setUp() {
        schema = new Validator().number();
    }

    @Test
    void testIsValidShouldFalseWhenNoRequiredInputText() {
        assertFalse(schema.isValid("text"));
    }

    @Test
    void testIsValidShouldFalseWhenNoRequiredInputEmpty() {
        assertFalse(schema.isValid(""));
    }

    @Test
    void testIsValidShouldTrueWhenNoRequiredInputNull() {
        assertTrue(schema.isValid(null));
    }

    @Test
    void testIsValidShouldTrueWhenNoRequiredInputInteger() {
        assertTrue(schema.isValid(123));
    }

    @Test
    void testIsValidShouldFalseWhenRequiredInputText() {
        schema.required();
        assertFalse(schema.isValid("text"));
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
    void testIsValidShouldTrueWhenRequiredInputInteger() {
        schema.required();
        assertTrue(schema.isValid(123));
    }

    @Test
    void testIsValidShouldTrueWhenPositiveInputPositiveInteger() {
        schema.required();
        schema.positive();
        assertTrue(schema.isValid(123));
    }

    @Test
    void testIsValidShouldFalseWhenPositiveInputZero() {
        schema.required();
        assertFalse(schema.positive().isValid(0));
    }

    @Test
    void testIsValidShouldFalseWhenPositiveInputNegativeInteger() {
        schema.required();
        assertFalse(schema.positive().isValid(-123));
    }

    @Test
    void testIsValidShouldTrueWhenRangeInputCorrectInteger() {
        schema.required();
        schema.range(100, 200);
        assertTrue(schema.isValid(123));
    }

    @Test
    void testIsValidShouldTrueWhenRangeInputSameInteger() {
        schema.required();
        schema.range(123, 123);
        assertTrue(schema.isValid(123));
    }

    @Test
    void testIsValidShouldFalseWhenRangeInputLessInteger() {
        schema.required();
        schema.range(100, 200);
        assertFalse(schema.isValid(50));
    }

    @Test
    void testIsValidShouldFalseWhenRangeInputMoreInteger() {
        schema.required();
        schema.range(100, 200);
        assertFalse(schema.isValid(300));
    }
}
