package hu.elte.eserial.parser;

import javafx.util.Pair;

/**
 * Parses null values.
 */
public class NullParser {
    public ParserEntry<String, Object> parser(Pair<String, String> pair) {

        ParserEntry<String, Object> entry = new ParserEntry(pair.getKey(), null);

        return entry;
    }
}
