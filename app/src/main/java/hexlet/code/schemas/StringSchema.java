package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class StringSchema extends BaseSchema {
    private final List<String> dictionary = new ArrayList<>();
    private int minLength;
    private boolean lengthRule;
    private boolean dictionaryRule;

    @Override
    public boolean isValid(Object objText) {
        if (!super.isValid(objText)) {
            return false;
        }

        boolean isString = objText instanceof String;
        if (requiredRule && (!isString || ((String) objText).isEmpty())) {
            return false;
        }
        if (lengthRule && isString && ((String) objText).length() < minLength) {
            return false;
        }
        if (dictionaryRule && isString && !checkContains((String) objText)) {
            return false;
        }
        return true;
    }

    private boolean checkContains(String text) {
        for (String word : dictionary) {
            if (!text.contains(word)) {
                return false;
            }
        }
        return true;
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

}
