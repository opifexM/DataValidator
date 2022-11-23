package hexlet.code;

import java.util.Map;

import static java.util.Objects.isNull;

public class MapSchema extends BaseSchema {
    private int sizeof = -1;

    public boolean isValid(Object objText) {
        if (!required) {
            return true;
        }
        if (isNull(objText)) {
            return false;
        }
        if (objText instanceof Map map) {
            return sizeof <= -1 || map.size() == sizeof;
        }
        return false;
    }

    public void sizeof(int number) {
        sizeof = number;
    }
}
