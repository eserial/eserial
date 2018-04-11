package hu.elte.eserial.parser;

import hu.elte.eserial.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses objects.
 */
public class ObjectParser extends AbstractParser{


    ObjectParser(String json) {
        super(json);
    }

    @Override
    Map<String, Object> parser() {
        Map<String, Object> map = new HashMap<>();
        String key;

        json = json.substring(1, json.length()).trim();

        while (json.length() > 0) {
            json = json.trim();
            if (json.startsWith("\"")) {
                json = json.substring(1, json.length());
                int index = json.indexOf("\"");
                key = json.substring(0, index);
                json = json.substring(index + 1, json.length()).trim();
                map.put(key, valueFinder());
            } else if (json.startsWith(",")) {
                json = json.substring(1, json.length());
                continue;
            } else if (json.startsWith("}")) {
                break;
            }
        }
        return map;
    }

    public Object valueFinder() {
        String value;
        json = json.substring(1, json.length()).trim();

        if(json.startsWith("{")) {
           value = json;
           int index = StringUtils.findClosingCurlyBracket(json) + 1;
           System.out.println(index);
           json = json.substring(index, json.length());
           System.out.println(json);
           return new ObjectParser(value).parser();
        } else if(json.startsWith("\"")) {
           json = json.substring(1, json.length());
           int index = json.indexOf("\"");
           value = json.substring(0, index);
           json = json.substring(index + 1, json.length()).trim();
           return new StringParser(value).parser();
        } else if(json.startsWith("[")) {
           value = json;
           int index = StringUtils.findSquareBracket(json) + 1;
           json = json.substring(index, json.length());
           return new CollectionParser(value);
        } else if(json.startsWith("null")) {
            value = json.substring(0,4);
            json = json.substring(4, json.length());
            return new NullParser(value).parser();
        } else if(json.startsWith("true")) {
            value = json.substring(0, 4);
            json = json.substring(4, json.length());
            return new BooleanParser(value).parser();
        } else if(json.startsWith("false")) {
            value = json.substring(0, 5);
            json = json.substring(5, json.length());
            return new BooleanParser(value).parser();
        } else if(StringUtils.isNumeric(Character.toString(json.charAt(0)))) {
            int index = StringUtils.findNumber(json);
            value = json.substring(0, index + 1);
            json = json.substring(index + 1, json.length());
            return new NumberParser(value).parser();
        }
        return null;
    }
}
