package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class StringSchema extends BaseSchema {
    private final List<String> dictionary = new ArrayList<>();
    private int minLength;

    @Override
    public boolean isValid(Object objText) {
        if (!required) {
            return true;
        } else if (isNull(objText)) {
            return false;
        } else if (objText instanceof String text) {
            boolean isValidateMinLength = text.length() >= minLength;
            boolean isValidateRequired = !text.replaceAll("[^a-zA-Z]", "").isEmpty();
            boolean isValidateContains = true;
            for (String word : dictionary) {
                if (!text.contains(word)) {
                    isValidateContains = false;
                    break;
                }
            }
            return isValidateMinLength && isValidateRequired && isValidateContains;
        }
        return false;
    }

    @Override
    public StringSchema contains(String word) {
        if (!isNull(word)) {
            dictionary.add(word);
        }
        return this;
    }

    public void minLength(int number) {
        if (number >= 0) {
            minLength = number;
        }
    }

}
