package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

public final class StringSchema extends BaseSchema {
    private final List<String> dictionary = new ArrayList<>();

    public StringSchema() {
        Predicate<Object> checkIsNullOrString = objectValue -> isNull(objectValue) || objectValue instanceof String;
        addCheck(Rules.IS_NULL_OR_STRING, checkIsNullOrString);
    }

    public StringSchema contains(String word) {
        if (!isNull(word) && !word.isEmpty()) {
            dictionary.add(word);
            Predicate<Object> checkDictionaryRule = objectValue -> {
                if (isNull(objectValue)) {
                    return true;
                }
                String objectText = (String) objectValue;
                return dictionary.stream()
                        .allMatch(objectText::contains);
            };
            addCheck(Rules.CONTAINS, checkDictionaryRule);
        }
        return this;
    }

    public StringSchema minLength(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Length number is not valid.");
        }
        Predicate<Object> checkLengthRule = objectValue -> isNull(objectValue)
                || ((String) objectValue).length() >= number;
        addCheck(Rules.MIN_LENGTH, checkLengthRule);
        return this;
    }

    public StringSchema required() {
        Predicate<Object> checkIsString = String.class::isInstance;
        addCheck(Rules.IS_STRING, checkIsString);
        Predicate<Object> checkRequiredRule = objectValue -> !((String) objectValue).isEmpty();
        addCheck(Rules.REQUIRED, checkRequiredRule);
        return this;
    }
}
