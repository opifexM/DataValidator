package hexlet.code.schemas;

import java.util.function.Function;
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
                        || isInteger.test(object) && objectInteger > 0;

        IntPredicate checkRangeRule = objectInteger ->
                !rangeRule
                        || isInteger.test(object) && objectInteger >= rangeStart && objectInteger <= rangeFinish;

//        boolean test1 = isNotNull.test(object);
//        boolean test2 = isInteger.test(object);
//
//        if (isInteger.test(object)) {
//            Integer test = (Integer) object;
//            boolean t1 = checkRequiredRule.test(Void voidd);
//            boolean t2 = checkPositiveRule.test(test);
//            boolean t3 = checkRangeRule.test(test);
//            System.out.println();
//        }

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
