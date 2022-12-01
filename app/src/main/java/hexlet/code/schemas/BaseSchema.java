package hexlet.code.schemas;

import java.util.EnumMap;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final EnumMap<Rules, Predicate<Object>> checks = new EnumMap<>(Rules.class);

    public boolean isValid(Object object) {
        return checks.values().stream()
                .allMatch(predicate -> predicate.test(object));
    }

    protected void addCheck(Rules rules, Predicate<Object> predicate) {
        checks.put(rules, predicate);
    }

    protected void deleteCheck(Rules rules) {
        checks.remove(rules);
    }
}
