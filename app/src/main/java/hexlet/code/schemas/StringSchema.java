package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

public final class StringSchema extends BaseSchema {
    private final List<String> dictionary = new ArrayList<>();
    private static final Predicate<Object> IS_STRING = String.class::isInstance;
    private static final Predicate<Object> IS_NULL_OR_STRING = objectValue ->
            isNull(objectValue) || objectValue instanceof String;

    public StringSchema() {
        addCheck(Rules.IS_NULL_OR_STRING, IS_NULL_OR_STRING);
    }

    private void addRuleOnlyStringObject() {
        deleteCheck(Rules.IS_NULL_OR_STRING);
        addCheck(Rules.IS_STRING, IS_STRING);
    }

    public StringSchema contains(String word) {
        if (!isNull(word) && !word.isEmpty()) {
            dictionary.add(word);
            addRuleOnlyStringObject();
            Predicate<Object> checkDictionaryRule = objectValue -> {
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
        addRuleOnlyStringObject();
        Predicate<Object> checkLengthRule = objectValue -> ((String) objectValue).length() >= number;
        addCheck(Rules.MIN_LENGTH, checkLengthRule);
        return this;
    }

    public StringSchema required() {
        addRuleOnlyStringObject();
        Predicate<Object> checkRequiredRule = objectValue -> !((String) objectValue).isEmpty();
        addCheck(Rules.REQUIRED, checkRequiredRule);
        return this;
    }
}
