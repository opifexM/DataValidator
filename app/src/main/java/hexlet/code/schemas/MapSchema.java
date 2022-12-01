package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

public final class MapSchema extends BaseSchema {
    private static final Predicate<Object> IS_MAP = Map.class::isInstance;
    private static final Predicate<Object> IS_NULL_OR_MAP = objectValue ->
            isNull(objectValue) || objectValue instanceof Map;

    public MapSchema() {
        addCheck(Rules.IS_NULL_OR_MAP, IS_NULL_OR_MAP);
    }

    private void addRuleOnlyMapObject() {
        deleteCheck(Rules.IS_NULL_OR_MAP);
        addCheck(Rules.IS_MAP, IS_MAP);
    }

    public void sizeof(int number) {
        addRuleOnlyMapObject();
        Predicate<Object> checkSizeofRule = objectValue -> ((Map<?, ?>) objectValue).size() == number;
        addCheck(Rules.SIZE_OF, checkSizeofRule);
    }

    public MapSchema required() {
        addRuleOnlyMapObject();
        return this;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        addRuleOnlyMapObject();
        if (!isNull(schemas) && !schemas.isEmpty()) {
            Predicate<Object> checkShapeValues = objectValue ->
                    ((Map<?, ?>) objectValue).entrySet().stream()
                            .allMatch(entry ->
                                    schemas.containsKey(entry.getKey())
                                            && schemas.get(entry.getKey()).isValid(entry.getValue()));
            addCheck(Rules.SHAPE, checkShapeValues);
        }
    }
}
