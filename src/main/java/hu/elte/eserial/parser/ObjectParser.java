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
            } else {
                throw new EserialInvalidJsonException("Missing comma or closing curly bracket in the json");
            }
        }

        return map;
    }
}
