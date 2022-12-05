package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        Predicate<Object> checkIsNullOrMap = objectValue -> isNull(objectValue) || objectValue instanceof Map;
        addCheck(Rules.IS_NULL_OR_MAP, checkIsNullOrMap);
    }

    public void sizeof(int number) {
        Predicate<Object> checkSizeofRule = objectValue -> isNull(objectValue) || ((Map<?, ?>) objectValue).size() == number;
        addCheck(Rules.SIZE_OF, checkSizeofRule);
    }

    public MapSchema required() {
        Predicate<Object> checkIsMap = Map.class::isInstance;
        addCheck(Rules.IS_MAP, checkIsMap);
        return this;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        if (!isNull(schemas) && !schemas.isEmpty()) {
            Predicate<Object> checkShapeValues = objectValue -> {
                if (isNull(objectValue)) {
                    return true;
                }
                return ((Map<?, ?>) objectValue).entrySet().stream()
                        .allMatch(entry ->
                                schemas.containsKey(entry.getKey())
                                        && schemas.get(entry.getKey()).isValid(entry.getValue()));
            };
            addCheck(Rules.SHAPE, checkShapeValues);
        }
    }
}
