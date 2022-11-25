package hexlet.code.schemas;

public abstract class BaseSchema {
    private boolean requiredRule;

    /**
     * @return turn on validation for null
     */
    public BaseSchema required() {
        requiredRule = true;
        return this;
    }

    /**
     * @param objText object for null validation
     * @return true if not null
     */
    protected boolean isValid(Object objText) {
        return !requiredRule || objText != null;
    }
}
