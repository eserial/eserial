package hu.elte.eserial.parser;

import hu.elte.eserial.exception.EserialInvalidJsonException;
import hu.elte.eserial.util.StringUtils;

import java.util.LinkedList;

/**
 * Parses collections.
 */
public class CollectionParser extends AbstractParser{

    /**
     * {@inheritDoc}
     * @param {@inheritDoc}
     */
    CollectionParser(String json) {
        super(json);
    }

    /**
     * @return list representation of {@code json}
     */
    @Override
    LinkedList<Object> parse() {
        if(!json.startsWith("[")) {
            throw new EserialInvalidJsonException("Missing [ at the front of the list in json");
        }

        LinkedList<Object> list = new LinkedList<>();
        String value;
        int index;

        json = json.substring(1, json.length());

        while(json.length() > 0) {
            if(StringUtils.isNumeric(Character.toString(json.charAt(0)))) {
                index = StringUtils.findNumber(json);
                value = json.substring(0, index + 1);
                json = json.substring(index + 1);
                list.add(new NumberParser(value).parse());
            } else if(json.startsWith("\"")) {
                json = json.substring(1);
                index = json.indexOf("\"");
                value = json.substring(0, index);
                json = json.substring(index + 1);
                list.add(new StringParser(value).parse());
            } else if(json.startsWith("false")) {
                json = json.substring(5);
                list.add(new BooleanParser("false").parse());
            } else if(json.startsWith("true")) {
                json = json.substring(4);
                list.add(new BooleanParser("true").parse());
            } else if(json.startsWith("null")) {
                json = json.substring(4);
                list.add(new NullParser("null").parse());
            } else if(json.startsWith("[")) {
                index = StringUtils.findClosingSquareBracket(json) + 1;
                value = json.trim().substring(0,index);
                json = json.substring(index);
                list.add(new CollectionParser(value).parse());
            } else if(json.startsWith("{")) {
                index = StringUtils.findClosingCurlyBracket(json) + 1;
                value = json.trim().substring(0,index);
                json = json.substring(index);
                list.add(new ObjectParser(value).parse());
            } else if(json.startsWith(" ") || json.startsWith(",") || json.startsWith("]")) {
                json = json.substring(1);
            }
        }

        return list;
    }
}
