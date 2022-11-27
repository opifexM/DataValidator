package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

public final class StringSchema extends BaseSchema {
    private final List<String> dictionary = new ArrayList<>();
    private int minLength;
    private boolean lengthRule;
    private boolean dictionaryRule;

    @Override
    public boolean isValid(Object object) {
        Predicate<Object> isNotNull = objectValue -> !isNull(objectValue);
        Predicate<Object> isString = String.class::isInstance;

        Predicate<String> textContain = objectText ->
                dictionary.stream()
                        .allMatch(objectText::contains);
        Function<Object, String> convertToString = String.class::cast;

        Predicate<String> checkRequiredRule = objectText ->
                !requiredRule && isNotNull.negate().test(object)
                        || !requiredRule && isString.test(object)
                        || requiredRule && isString.test(object) && !objectText.isEmpty();
        Predicate<String> checkLengthRule = objectText ->
                !lengthRule
                        || isString.test(object) && objectText.length() >= minLength;
        Predicate<String> checkDictionaryRule = objectText ->
                !dictionaryRule
                        || isString.test(object) && textContain.test(objectText);

        return checkRequiredRule.and(checkLengthRule.and(checkDictionaryRule))
                .test(isNotNull.and(isString).test(object)
                        ? convertToString.apply(object)
                        : "");
    }

    public StringSchema contains(String word) {
        if (!isNull(word)) {
            dictionary.add(word);
            dictionaryRule = true;
        }
        return this;
    }

    public StringSchema minLength(int number) {
        if (number >= 0) {
            minLength = number;
            lengthRule = true;
        }
        return this;
    }

    @Override
    public StringSchema required() {
        super.required();
        return this;
    }
}
