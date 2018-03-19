package hu.elte.eserial.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses objects.
 */
public class ObjectParser {
    public Map<String, Object> parser(String json) {
        Map<String, Object> map = new HashMap<>();

        json = json.substring(1, json.length()-1);

        for(String pair : json.split(",")) {
            String[] parts = pair.split(":");
            String key = parts[0].trim().replace("\"", "");
            String value = parts[1].trim().replace("\"", "");

            if(isNumeric(value)) {
                map.put(key, Double.parseDouble(value));
            } else if(isBoolean(value)){
                map.put(key, Boolean.parseBoolean(value));
            } else {
                map.put(key, value);
            }
        }

        return map;
    }

    //match a number with optional '-' and decimal.
    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isBoolean(String str) {
        if(str.equals("false") || str.equals("true")) {
            return true;
        } else {
            return false;
        }
    }
}