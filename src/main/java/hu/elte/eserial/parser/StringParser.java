package hu.elte.eserial.parser;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses strings.
 */
public class StringParser extends AbstractParser{

    StringParser(String value) {
        super(value);
    }

    @Override
    String parser() {

        return value.substring(1, value.length() - 1);
    }
}
