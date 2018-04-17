package hu.elte.eserial.parser;

import hu.elte.eserial.util.StringUtils;

/**
 * Provides an adequate parser implementation for the given json
 */
public class ParserFactory {

    /**
     * Prevents the accidental instantiation of this factory class.
     */
    private ParserFactory() {}

    public static AbstractParser create(String json) {

        String value;
        int index;
        //json = json.substring(1).trim();

        if(json.startsWith("{")) {
            value = json;
            index = StringUtils.findClosingCurlyBracket(json) + 1;
            json = json.substring(index);
            return new ObjectParser(value);
        }  else if(json.startsWith("[")) {
            return new CollectionParser(json);
        } else if(json.startsWith("null")) {
            return new NullParser(json);
        } else if(json.startsWith("true") || json.startsWith("false")) {
            return new BooleanParser(json);
        } else if(StringUtils.isNumeric(Character.toString(json.charAt(0))) || (StringUtils.isNumeric(Character.toString(json.charAt(1))) && json.charAt(0) == '-')) {
            return new NumberParser(json);
        } else {
            return new StringParser(json);
        }
    }
}
