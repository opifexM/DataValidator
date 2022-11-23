package hexlet.code;

import static java.util.Objects.isNull;

public class NumberSchema extends BaseSchema {
    private boolean positive;
    private int rangeStart;
    private int rangeFinish;

    public boolean isValid(Object objText) {
        if (!required) {
            return true;
        }
        if (isNull(objText) || objText.toString().isEmpty()) {
            return false;
        }
        if (!objText.toString().chars().allMatch(Character::isDigit)) {
            return false;
        }
        int number = Integer.parseInt(objText.toString());

        boolean isPositive = !positive || number > 0;

        boolean isRange = (rangeStart == rangeFinish)
                || (number >= rangeStart && number <= rangeFinish);

        return isPositive && isRange;
    }

    public NumberSchema positive() {
        positive = true;
        return this;
    }

    public void range(int start, int finish) {
        rangeStart = start;
        rangeFinish = finish;
    }
}
