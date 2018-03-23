package hu.elte.eserial.parser;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses booleans.
 */
public class BooleanParser {
    public ParserEntry<String, Object> parser(Pair<String, String> pair) {

        ParserEntry<String, Object> entry = new ParserEntry(pair.getKey(), Boolean.parseBoolean(pair.getValue()));

        return entry;
    }
}
