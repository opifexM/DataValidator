package hexlet.code;

public class BaseSchema {
    protected boolean required;

    public BaseSchema required() {
        required = true;
        return this;
    }

    protected boolean isValid(Object value) {
        return false;
    }
}
