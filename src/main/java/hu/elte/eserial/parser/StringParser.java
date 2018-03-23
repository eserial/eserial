package hu.elte.eserial.parser;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses strings.
 */
public class StringParser {

    public ParserEntry<String, Object> parser(Pair<String, String> pair) {

        ParserEntry<String, Object> entry = new ParserEntry<>(pair.getKey(), pair.getValue());

        return entry;
    }
}
