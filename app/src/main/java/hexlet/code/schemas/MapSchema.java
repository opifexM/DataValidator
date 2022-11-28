package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Objects.isNull;

public final class MapSchema extends BaseSchema {
    private int sizeof = -1;
    private boolean sizeofRule;
    private Map<String, BaseSchema> schemaMap;
    private boolean schemaRule;

    @Override
    public boolean isValid(Object object) {
        Predicate<Object> isNotNull = objectValue -> !isNull(objectValue);
        Predicate<Object> isMap = Map.class::isInstance;
        Function<Object, Map<String, String>> convertToMap = Map.class::cast;

        Predicate<Map<String, String>> checkRequiredRule = objectMap ->
                !requiredRule && isNotNull.negate().test(object)
                        || isMap.test(object);

        Predicate<Map<String, String>> checkSizeofRule = objectMap ->
                !sizeofRule
                        || isMap.test(object) && objectMap.size() == sizeof;

        Predicate<Map<String, String>> checkShapeValues = objectMap ->
                objectMap.entrySet().stream()
                        .allMatch(stringStringEntry ->
                                schemaMap.containsKey(stringStringEntry.getKey())
                                && schemaMap.get(stringStringEntry.getKey()).isValid(stringStringEntry.getValue()));
        Predicate<Map<String, String>> checkSchemaRule = objectMap ->
                !schemaRule
                        || schemaMap.isEmpty()
                        || isMap.test(object) && checkShapeValues.test(objectMap);

        return checkRequiredRule.and(checkSizeofRule.and(checkSchemaRule))
                .test(isNotNull.and(isMap).test(object)
                        ? convertToMap.apply(object)
                        : Map.of());
    }

    public void sizeof(int number) {
        sizeof = number;
        sizeofRule = true;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        schemaMap = schemas;
        schemaRule = true;
    }

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }
}
