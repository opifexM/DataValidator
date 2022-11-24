package hexlet.code.schemas;

public class BaseSchema {
    protected boolean required;

    public BaseSchema required() {
        required = true;
        return this;
    }

    protected boolean isValid(Object value) {
        return false;
    }

    public BaseSchema contains(String value) {
        return this;
    }
}
