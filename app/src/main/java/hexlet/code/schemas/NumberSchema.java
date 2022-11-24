package hexlet.code.schemas;

import static java.util.Objects.isNull;

public class NumberSchema extends BaseSchema {
    private boolean positive;
    private int rangeStart;
    private int rangeFinish;

    @Override
    public boolean isValid(Object objText) {
        if (!required && !positive) {
            return true;
        } else if (required && isNull(objText)) {
            return false;
        } else if (positive && isNull(objText)) {
            return true;
        } else if (objText instanceof Integer number) {
            boolean isPositive = number > 0;
            boolean isRange = (rangeStart == rangeFinish)
                    || (number >= rangeStart && number <= rangeFinish);

            return isPositive && isRange;
        }
        return false;
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
