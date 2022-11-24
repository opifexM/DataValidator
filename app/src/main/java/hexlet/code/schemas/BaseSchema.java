package hexlet.code.schemas;

public class BaseSchema {
    protected boolean requiredRule;

    public BaseSchema required() {
        requiredRule = true;
        return this;
    }

    protected boolean isValid(Object objText) {
        return !requiredRule || objText != null;
    }
}

