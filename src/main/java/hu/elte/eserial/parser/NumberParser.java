package hu.elte.eserial.parser;

import hu.elte.eserial.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class NumberParser {
    public Map<String, Object> parser(String json) {
        Map<String, Object> map = new HashMap<>();

        json.replaceAll("\"", "");
        String parts[] = json.split(":");

        if(StringUtils.isInteger(parts[1])) {
           map.put(parts[0], Integer.parseInt(parts[1]));
        } else {
           map.put(parts[0], Double.parseDouble(parts[1]));
        }

        return map;
    }
}


