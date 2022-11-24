package hexlet.code.schemas;

import java.util.Map;

import static java.util.Objects.isNull;

public class MapSchema extends BaseSchema {
    private int sizeof = -1;
    Map<String, BaseSchema> schemaMap;

    @Override
    public boolean isValid(Object objText) {
        if (!required) {
            return true;
        } else if (isNull(objText)) {
            return false;
        } else if (objText instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) objText;
            boolean isSizeof = sizeof <= -1 || map.size() == sizeof;
            if (!map.isEmpty()
                    && !isNull(schemaMap) && !schemaMap.isEmpty()
                    && !checkValues(map)) {
                return false;
            }
            return isSizeof;
        }
        return false;
    }

    private boolean checkValues(Map<String, Object> map) {
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
        return true;
    }

    public void sizeof(int number) {
        sizeof = number;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        schemaMap = schemas;
        required = true;
    }

}
