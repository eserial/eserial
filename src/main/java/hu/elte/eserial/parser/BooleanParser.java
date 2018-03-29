package hu.elte.eserial.parser;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses booleans.
 */
public class BooleanParser extends AbstractParser {

    BooleanParser(String value) {
        super(value);
    }

    @Override
    Boolean parser() {
        return Boolean.parseBoolean(value);
    }
}
