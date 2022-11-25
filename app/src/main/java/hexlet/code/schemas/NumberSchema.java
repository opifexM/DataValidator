package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    private boolean positiveRule;
    private boolean rangeRule;
    private int rangeStart;
    private int rangeFinish;

    @Override
    public boolean isValid(Object objText) {
        if (!super.isValid(objText)) {
            return false;
        }

        boolean isInteger = objText instanceof Integer;
        if (requiredRule && !isInteger) {
            return false;
        }
        if (positiveRule && isInteger && (Integer) objText <= 0) {
            return false;
        }
        if (rangeRule && isInteger && ((Integer) objText < rangeStart || (Integer) objText > rangeFinish)) {
            return false;
        }
        return true;
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
