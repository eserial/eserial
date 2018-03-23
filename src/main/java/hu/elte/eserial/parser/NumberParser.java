package hu.elte.eserial.parser;

import hu.elte.eserial.util.StringUtils;
import javafx.util.Pair;

/**
 * Parses numbers.
 */
public class NumberParser {
    public ParserEntry<String, Object> parser(Pair<String, String> pair) {
        ParserEntry<String, Object> entry;

        if(StringUtils.isInteger(pair.getValue().trim())) {
            entry = new ParserEntry(pair.getKey().trim(), Long.parseLong(pair.getValue().trim()));
        } else {
            entry = new ParserEntry(pair.getKey().trim(), Double.parseDouble(pair.getValue().trim()));
        }

        return entry;
    }
}


