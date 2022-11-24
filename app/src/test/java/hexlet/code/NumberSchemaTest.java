package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberSchemaTest {

    NumberSchema schema;

    @BeforeEach
    void setUp() {
        schema = new Validator().number();
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
    void testIsValid_ShouldFalse_WhenRequiredInputText() {
        schema.required();
        assertFalse(schema.isValid("text"));
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
    void testIsValid_ShouldTrue_WhenRequiredInputInteger() {
        schema.required();
        assertTrue(schema.isValid(123));
    }

    @Test
    void testIsValid_ShouldTrue_WhenPositiveInputPositiveInteger() {
        schema.required();
        schema.positive();
        assertTrue(schema.isValid(123));
    }

    @Test
    void testIsValid_ShouldFalse_WhenPositiveInputZero() {
        schema.required();
        assertFalse(schema.positive().isValid(0));
    }

    @Test
    void testIsValid_ShouldFalse_WhenPositiveInputNegativeInteger() {
        schema.required();
        assertFalse(schema.positive().isValid(-123));
    }

    @Test
    void testIsValid_ShouldTrue_WhenRangeInputCorrectInteger() {
        schema.required();
        schema.range(100, 200);
        assertTrue(schema.isValid(123));
    }

    @Test
    void testIsValid_ShouldTrue_WhenRangeInputSameInteger() {
        schema.required();
        schema.range(123, 123);
        assertTrue(schema.isValid(123));
    }

    @Test
    void testIsValid_ShouldFalse_WhenRangeInputLessInteger() {
        schema.required();
        schema.range(100, 200);
        assertFalse(schema.isValid(50));
    }

    @Test
    void testIsValid_ShouldFalse_WhenRangeInputMoreInteger() {
        schema.required();
        schema.range(100, 200);
        assertFalse(schema.isValid(300));
    }

}