package hexlet.code;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class MapSchema extends BaseSchema {
    private int sizeof = -1;
    Map<String, BaseSchema> schemaMap;

    public boolean isValid(Object objText) {
        if (!required) {
            return true;
        }
        if (isNull(objText)) {
            return false;
        }
        if (!(objText instanceof Map)) {
            return false;
        }
        Map<String, Object> map = (Map<String, Object>) objText;

        boolean isSizeof = sizeof <= -1 || map.size() == sizeof;

        if (!map.isEmpty() && !isNull(schemaMap) && !schemaMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (schemaMap.containsKey(key)) {
                    BaseSchema baseSchema = schemaMap.get(key);
                    boolean check = baseSchema.isValid(value);
                    if (!check) {
                        return false;
                    }
                }
            }
        }
        return isSizeof;
    }

    public void sizeof(int number) {
        sizeof = number;
    }

    public void shape(Map<String, BaseSchema> schemas) {
        schemaMap = schemas;
        required = true;
    }

}
