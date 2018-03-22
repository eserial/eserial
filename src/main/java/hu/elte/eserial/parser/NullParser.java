package hu.elte.eserial.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses null values.
 */
public class NullParser {
    public Map<String, Object> parser(String json) {
        Map<String, Object> map = new HashMap<>();
        json = json.replace("{", "");
        json = json.replace("}", "");
        json = json.replace("\"", "");

        String[] parts = json.split(":");

        map.put(parts[0], null);

        return map;
    }
}
