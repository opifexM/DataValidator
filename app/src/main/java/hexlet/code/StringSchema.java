package hexlet.code;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class StringSchema {
    private final List<String> dictionary = new ArrayList<>();
    private boolean required;
    private int minLength;

    public boolean isValid(Object objText) {
        String text;
        if (isNull(objText)) {
            text = "";
        } else {
            text = objText.toString();
        }

        boolean isValidateMinLength = text.length() >= minLength;

        boolean isValidateRequired = !required || !text.replaceAll("[^a-zA-Z]", "").isEmpty();

        boolean isValidateContains = true;
        for (String word : dictionary) {
            if (!text.contains(word)) {
                isValidateContains = false;
                break;
            }
        }


        return isValidateMinLength && isValidateRequired && isValidateContains;
    }

    public void required() {
        required = true;
    }

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
