package hexlet.code.schemas;

import java.util.Map;

import static java.util.Objects.isNull;

public final class MapSchema extends BaseSchema {
    private int sizeof = -1;
    private boolean sizeofRule;
    Map<String, BaseSchema> schemaMap;
    private boolean schemaRule;

    @Override
    public boolean isValid(Object object) {
        if (!super.isValid(object)) {
            return false;
        }

        boolean isMap = object instanceof Map<?, ?>;
        if (requiredRule && !isMap) {
            return false;
        }
        if (sizeofRule && isMap && ((Map<?, ?>) object).size() != sizeof) {
            return false;
        }
        if (schemaRule && isMap && !checkValues((Map<String, Object>) object)) {
            return false;
        }
        return true;
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
