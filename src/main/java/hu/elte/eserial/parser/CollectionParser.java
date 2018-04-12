package hu.elte.eserial.parser;

import hu.elte.eserial.exception.EserialParserMismatchException;
import hu.elte.eserial.util.StringUtils;

import java.util.LinkedList;

/**
 * Parses collections.
 */
public class CollectionParser extends AbstractParser{

    /**
     * Stores a string for parsing
     * @param json string to be parsed
     */
    CollectionParser(String json) {
        super(json);
    }

    /**
     * Returns the list representation of the string {@code json}.
     *
     * @return list representation of {@code json}
     */
    @Override
    LinkedList<Object> parser() {
        if(!json.startsWith("[")) {
            throw new EserialParserMismatchException("Missing [ the front of the list in json");
        }

        if(!json.endsWith("]")) {
            throw new EserialParserMismatchException("Missing ] the front of the list in json");
        }

        LinkedList<Object> list = new LinkedList<>();
        String value;
        int index;

        json = json.substring(1, json.length());

        while(json.length() > 0) {
            if(StringUtils.isNumeric(Character.toString(json.charAt(0)))) {
                index = StringUtils.findNumber(json);
                value = json.substring(0, index + 1);
                json = json.substring(index + 1, json.length());
                list.add(new NumberParser(value).parser());
            } else if(json.startsWith("\"")) {
                json = json.substring(1, json.length());
                index = json.indexOf("\"");
                value = json.substring(0, index);
                json = json.substring(index + 1, json.length());
                list.add(new StringParser(value).parser());
            } else if(json.startsWith("false")) {
                json = json.substring(5, json.length());
                list.add(new BooleanParser("false").parser());
            } else if(json.startsWith("true")) {
                json = json.substring(4, json.length());
                list.add(new BooleanParser("true").parser());
            } else if(json.startsWith("null")) {
                json = json.substring(4, json.length());
                list.add(new NullParser("null").parser());
            } else if(json.startsWith("[")) {
                index = StringUtils.findSquareBracket(json) + 1;
                value = json.trim().substring(0,index);
                json = json.substring(index, json.length());
                list.add(new CollectionParser(value).parser());
            } else if(json.startsWith("{")) {
                index = StringUtils.findClosingCurlyBracket(json) + 1;
                value = json.trim().substring(0,index);
                json = json.substring(index, json.length());
                list.add(new ObjectParser(value).parser());
            } else {
                json = json.substring(1, json.length());
            }
        }

        return list;
    }
}
