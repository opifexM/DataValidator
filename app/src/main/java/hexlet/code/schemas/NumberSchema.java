package hexlet.code.schemas;

import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import static java.util.Objects.isNull;

public final class NumberSchema extends BaseSchema {
    private boolean positiveRule;
    private boolean rangeRule;
    private int rangeStart;
    private int rangeFinish;

    @Override
    public boolean isValid(Object object) {
        Predicate<Object> isNotNull = objectValue -> !isNull(objectValue);
        Predicate<Object> isInteger = Integer.class::isInstance;
        ToIntFunction<Object> convertToInteger = Integer.class::cast;

        IntPredicate checkRequiredRule = objectInteger ->
                !requiredRule && isNotNull.negate().test(object)
                        || isInteger.test(object);

        IntPredicate checkPositiveRule = objectInteger ->
                !positiveRule
                        || isNotNull.negate().test(object)
                        || isInteger.test(object) && objectInteger > 0;

        IntPredicate checkRangeRule = objectInteger ->
                !rangeRule
                        || isInteger.test(object) && objectInteger >= rangeStart && objectInteger <= rangeFinish;

        return checkRequiredRule.and(checkPositiveRule.and(checkRangeRule))
                .test(isNotNull.and(isInteger).test(object)
                        ? convertToInteger.applyAsInt(object)
                        : 0);
    }

    public NumberSchema positive() {
        positiveRule = true;
        return this;
    }

    public void range(int start, int finish) {
        if (start > finish) {
            throw new IllegalArgumentException("Range is not valid.");
        }
        rangeRule = true;
        rangeStart = start;
        rangeFinish = finish;
    }

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }
}
