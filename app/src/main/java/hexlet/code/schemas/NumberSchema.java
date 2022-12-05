package hexlet.code.schemas;

import java.util.function.Predicate;

import static java.util.Objects.isNull;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        Predicate<Object> checkIsNullOrInteger = objectValue -> isNull(objectValue) || objectValue instanceof Integer;
        addCheck(Rules.IS_NULL_OR_STRING, checkIsNullOrInteger);
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
        Predicate<Object> checkRangeRule = objectValue -> isNull(objectValue)
                || (Integer) objectValue >= start && (Integer) objectValue <= finish;
        addCheck(Rules.RANGE, checkRangeRule);
    }

    public NumberSchema required() {
        Predicate<Object> checkIsInteger = Integer.class::isInstance;
        addCheck(Rules.IS_INTEGER, checkIsInteger);
        return this;
    }
}
