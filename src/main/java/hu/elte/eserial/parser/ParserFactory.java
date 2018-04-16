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
        json = json.substring(1).trim();

        if(json.startsWith("{")) {
            value = json;
            index = StringUtils.findClosingCurlyBracket(json) + 1;
            json = json.substring(index);
            return new ObjectParser(value);
        }  else if(json.startsWith("[")) {
            index = StringUtils.findClosingSquareBracket(json) + 1;
            value = json.trim().substring(0,index);
            json = json.substring(index, json.length());
            return new CollectionParser(value);
        } else if(json.startsWith("null")) {
            json = json.substring(4, json.length());
            return new NullParser("null");
        } else if(json.startsWith("true")) {
            json = json.substring(4, json.length());
            return new BooleanParser("true");
        } else if(json.startsWith("false")) {
            json = json.substring(5);
            return new BooleanParser("false");
        } else if(StringUtils.isNumeric(Character.toString(json.charAt(0)))) {
            index = StringUtils.findNumber(json);
            value = json.substring(0, index + 1);
            json = json.substring(index + 1);
            return new NumberParser(value);
        } else {
            json = json.substring(1);
            index = json.indexOf("\"");
            value = json.substring(0, index);
            json = json.substring(index + 1);
            return new StringParser(value);
        }
    }
}
