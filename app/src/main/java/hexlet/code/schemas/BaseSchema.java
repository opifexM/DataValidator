package hexlet.code.schemas;

public class BaseSchema {
    protected boolean requiredRule;

    public BaseSchema required() {
        requiredRule = true;
        return this;
    }

    protected boolean isValid(Object objText) {
        if (requiredRule && objText == null) {
            return false;
        }
        return true;
    }

    public BaseSchema contains(String value) {
        return this;
    }
}
