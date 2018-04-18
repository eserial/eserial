package hu.elte.eserial.parser;

import hu.elte.eserial.exception.EserialInvalidJsonException;

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

        json = json.substring(1, json.length());

        if(json.trim().startsWith(",")) {
            throw new EserialInvalidJsonException("Comma before the first element in the list");
        }

        while(!json.startsWith("]")) {
            AbstractParser abstractParser = ParserFactory.create(json.trim());
            list.add(abstractParser.parse());
            json = abstractParser.json.trim();

            if(!json.startsWith(",") && !json.startsWith("]")) {
                throw new EserialInvalidJsonException("Missing comma in a list in the json");
            } else if(json.startsWith(",") && json.substring(1).trim().charAt(0) == ']') {
                throw new EserialInvalidJsonException("Comma after the last element int the list");
            } else if(json.startsWith(",")) {
                json = json.substring(1);
            }
        }

        json = json.substring(1);

        return list;
    }
}
