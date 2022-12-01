package hexlet.code.schemas;

import java.util.EnumMap;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final EnumMap<Rules, Predicate<Object>> checks = new EnumMap<>(Rules.class);

    /**
     * @param object - Starts object validation
     * @return - true/false
     */
    public boolean isValid(Object object) {
        return checks.values().stream()
                .allMatch(predicate -> predicate.test(object));
    }

    /**
     * @param rules - Add a new rule
     * @param predicate - The logic of the rule
     */
    protected void addCheck(Rules rules, Predicate<Object> predicate) {
        checks.put(rules, predicate);
    }

    /**
     * @param rules - Delete the rule
     */
    protected void deleteCheck(Rules rules) {
        checks.remove(rules);
    }
}
