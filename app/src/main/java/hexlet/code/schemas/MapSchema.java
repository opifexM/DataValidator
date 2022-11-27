package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

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

        Predicate<Map<String, String>> checkSchemaRule = objectMap ->
                !schemaRule
                        || schemaMap.isEmpty()
                        || isMap.test(object) && objectMap.size() == sizeof;


//        Predicate<String> checkDictionaryRule = objectText ->
//                !dictionaryRule
//                        || isString.test(object) && textContain.test(objectText);

        boolean test1 = isNotNull.test(object);
        boolean test2 = isMap.test(object);

        if (isMap.test(object)) {
            Map test = (Map) object;
            boolean t1 = checkRequiredRule.test(test);
            boolean t2 = checkSizeofRule.test(test);
            System.out.println();
        }

//        Predicate<Map<String, String>> checkShapeValues = objectMap ->
//                objectMap.entrySet().stream()
//                        .allMatch(stringStringEntry -> {
//                            if (!schemaMap.containsKey(stringStringEntry.getKey())) {
//                                return false;
//                            }
//
//                            return
//                        });

        return checkRequiredRule.and(checkSizeofRule)
                .test(isNotNull.and(isMap).test(object)
                        ? convertToMap.apply(object)
                        : Map.of());
    }

    private boolean checkValues(Map<String, Object> map) {
        if (!map.isEmpty() && !isNull(schemaMap) && !schemaMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (schemaMap.containsKey(key)) {
                    BaseSchema baseSchema = schemaMap.get(key);
                    if (!baseSchema.isValid(value)) {
                        return false;
                    }
                }
            }
        }
        return true;
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
