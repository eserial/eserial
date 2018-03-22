package hu.elte.eserial.parser;

import hu.elte.eserial.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses objects.
 */
public class ObjectParser {
    public Map<String, Object> parser(String json) {
        Map<String, Object> map = new HashMap<>();

        json = json.substring(1, json.length()-1);

        System.out.println(json);

        for(String pair : json.split(",")) {
            String[] parts = pair.split(":", 2);

            String key = parts[0].trim().replace("\"", "");

            if(parts[1].trim().charAt(0) == '{') {
                map.put(key, parser(parts[1].trim()));
                continue;
            }

            String value = parts[1].trim().replace("\"", "");

            if(StringUtils.isNumeric(value)) {
                map.put(key, Double.parseDouble(value));
            } else if(StringUtils.isBoolean(value)){
                map.put(key, Boolean.parseBoolean(value));
            } else {
                map.put(key, value);
            }
        }

        return map;
    }
}
