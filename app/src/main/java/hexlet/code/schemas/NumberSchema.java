package hexlet.code.schemas;

import java.util.function.Predicate;

import static java.util.Objects.isNull;

public final class NumberSchema extends BaseSchema {
    private static final Predicate<Object> IS_INTEGER = Integer.class::isInstance;
    private static final Predicate<Object> IS_NULL_OR_INTEGER = objectValue ->
            isNull(objectValue) || objectValue instanceof Integer;

    public NumberSchema() {
        addCheck(Rules.IS_NULL_OR_STRING, IS_NULL_OR_INTEGER);
    }

    private void addRuleIntegerObject() {
        addCheck(Rules.IS_INTEGER, IS_INTEGER);
    }

    public NumberSchema positive() {
        Predicate<Object> checkPositiveRule = objectValue -> isNull(objectValue) || (Integer) objectValue > 0;
        addCheck(Rules.POSITIVE, checkPositiveRule);
        return this;
    }

    public void range(int start, int finish) {
        if (start > finish) {
            throw new IllegalArgumentException("Range is not valid.");
        }
        addRuleIntegerObject();
        Predicate<Object> checkRangeRule = objectValue ->
                (Integer) objectValue >= start && (Integer) objectValue <= finish;
        addCheck(Rules.RANGE, checkRangeRule);
    }

    public NumberSchema required() {
        addRuleIntegerObject();
        return this;
    }
}
