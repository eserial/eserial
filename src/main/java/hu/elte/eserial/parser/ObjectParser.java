package hu.elte.eserial.parser;

import hu.elte.eserial.exception.EserialParserMismatchException;
import hu.elte.eserial.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses objects.
 */
public class ObjectParser extends AbstractParser{

    /**
     * Stores a string for parsing
     * @param json string to be parsed
     */
    ObjectParser(String json) {
        super(json);
    }

    /**
     * Returns the map representation of the string {@code json}.
     *
     * @return map representation of {@code json}
     */
    @Override
    Map<String, Object> parser() {

        if(!json.trim().startsWith("{")) {
            throw new EserialParserMismatchException("Missing { the front of the json");
        }

        if(!json.trim().endsWith("}")) {
            throw new EserialParserMismatchException("Missing } the end of the json");
        }

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

    /**
     * Returns the object representation of the string {@code json}.
     *
     * @return object representation of {@code json}
     */
    public Object valueFinder() {
        String value;
        json = json.substring(1, json.length()).trim();

        if(json.startsWith("{")) {
           value = json;
           int index = StringUtils.findClosingCurlyBracket(json) + 1;
           json = json.substring(index, json.length());
           return new ObjectParser(value).parser();
        }  else if(json.startsWith("[")) {
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
        } else {
            json = json.substring(1, json.length());
            int index = json.indexOf("\"");
            value = json.substring(0, index);
            json = json.substring(index + 1, json.length());
            return new StringParser(value).parser();
        }
    }
}
