package hu.elte.eserial.parser;

import hu.elte.eserial.exception.EserialInvalidJsonException;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses objects.
 */
public class ObjectParser extends AbstractParser{

    /**
     * {@inheritDoc}
     * @param {@inheritDoc}
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
    Map<String, Object> parse() {

        if(!json.trim().startsWith("{")) {
            throw new EserialInvalidJsonException("Missing { the front of the json");
        }

       /* if(!json.trim().endsWith("}")) {
            throw new EserialInvalidJsonException("Missing } the end of the json");
        }*/

        Map<String, Object> map = new HashMap<>();
        String key;

        json = json.substring(1, json.length());

        while (json.length() > 0) {
            json = json.trim();
            if (json.startsWith("\"")) {
                json = json.substring(1);
                int index = json.indexOf("\"");
                key = json.substring(0, index);
                json = json.substring(index + 1).trim();
                System.out.println(json);

                if(!json.startsWith(":")) {
                    throw new EserialInvalidJsonException("Missing : in the json");
                }

                json = json.substring(1);
                AbstractParser abstractParser = ParserFactory.create(json.trim());
                map.put(key, abstractParser.parse());
                json = abstractParser.json;
            } else if (json.startsWith(",")) {
                json = json.substring(1);
                continue;
            } else if (json.startsWith("}")) {
                json = json.substring(1);
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
   /*public Object valueFinder() {
       String value;
       int index;
       json = json.substring(1).trim();

       if (json.startsWith("{")) {
           value = json;
           index = StringUtils.findClosingCurlyBracket(json) + 1;
           json = json.substring(index);
           return new ObjectParser(value).parse();
       } else if (json.startsWith("[")) {
           index = StringUtils.findClosingSquareBracket(json) + 1;
           value = json.trim().substring(0, index);
           json = json.substring(index, json.length());
           return new CollectionParser(value).parse();
       } else if (json.startsWith("null")) {
           json = json.substring(4, json.length());
           return new NullParser("null").parse();
       } else if (json.startsWith("true")) {
           json = json.substring(4, json.length());
           return new BooleanParser("true").parse();
       } else if (json.startsWith("false")) {
           json = json.substring(5);
           return new BooleanParser("false").parse();
       } else if (StringUtils.isNumeric(Character.toString(json.charAt(0)))) {
           index = StringUtils.findNumber(json);
           value = json.substring(0, index + 1);
           json = json.substring(index + 1);
           return new NumberParser(value).parse();
       } else {
           json = json.substring(1);
           index = json.indexOf("\"");
           value = json.substring(0, index);
           json = json.substring(index + 1);
           return new StringParser(value).parse();
       }
   }*/
}
