package hu.elte.eserial.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses strings.
 */
public class StringParser {

    public Map<String, Object> parser(String json) {
        Map<String, Object> map = new HashMap<>();

        json = json.replace("{", "");
        json = json.replace("}", "");

        String[] parts = json.split(":");

        map.put(parts[0].replace("\"", ""), parts[1].substring(1, parts[1].length() - 1));

        return map;
    }
}
