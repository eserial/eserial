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

        json = json.substring(1, json.length());

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
           json = json.substring(index, json.length());
           return new ObjectParser(value).parser();
        } else if(json.startsWith("\"")) {
           json = json.substring(1, json.length());
           int index = json.indexOf("\"");
           value = json.substring(0, index);
           json = json.substring(index + 1, json.length());
           return new StringParser(value).parser();
        } else if(json.startsWith("[")) {
           int index = StringUtils.findSquareBracket(json) + 1;
           value = json.trim().substring(0,index);
           json = json.substring(index, json.length());
           return new CollectionParser(value).parser();
        } else if(json.startsWith("null")) {
            json = json.substring(4, json.length());
            return new NullParser("null").parser();
        } else if(json.startsWith("true")) {
            json = json.substring(4, json.length());
            return new BooleanParser("true").parser();
        } else if(json.startsWith("false")) {
            json = json.substring(5, json.length());
            return new BooleanParser("false").parser();
        } else if(StringUtils.isNumeric(Character.toString(json.charAt(0)))) {
            int index = StringUtils.findNumber(json);
            value = json.substring(0, index + 1);
            json = json.substring(index + 1, json.length());
            return new NumberParser(value).parser();
        }
        return null;
    }
}
