package hexlet.code.schemas;

public abstract class BaseSchema {
    boolean requiredRule;

    public BaseSchema required() {
        requiredRule = true;
        return this;
    }

    protected boolean isValid(Object objText) {
        return !requiredRule || objText != null;
    }
}
