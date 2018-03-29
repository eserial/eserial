package hu.elte.eserial.parser;

import hu.elte.eserial.util.StringUtils;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Parses objects.
 */
public class ObjectParser {
    public Map<String, Object> parser(String json) {
        Map<String, Object> map = new HashMap<>();
        String key;
        String value;

        json = json.substring(1, json.length() - 1);

        for(String pair : json.split(",")) {
            String[] parts = pair.split(":", 2);

            key = parts[0].trim().replace("\"", "");

            if(parts[1].trim().charAt(0) == '{') {
                map.put(key, parser(parts[1].trim()));
                continue;
            }

            if(parts[1].trim().charAt(0) == '[') {
                value = parts[1].trim().substring(1, parts[1].trim().length() - 1);
                LinkedList<Object> list = new CollectionParser(value).parser();
                map.put(key, list);
                continue;
            }

            value = parts[1].trim();

            if(StringUtils.isNumeric(value)) {
                map.put(key, new NumberParser(value).parser());
            } else if(StringUtils.isBoolean(value)){
                map.put(key, new BooleanParser(value).parser());
            } else if(value.equals("null")) {
                map.put(key, new NullParser(value).parser());
            } else {
                map.put(key, new StringParser(value).parser());
            }
        }

        return map;
    }
}
