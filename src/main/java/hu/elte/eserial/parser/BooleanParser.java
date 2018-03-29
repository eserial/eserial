package hu.elte.eserial.parser;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses booleans.
 */
public class BooleanParser {
    public Boolean parser(String value) {

        return Boolean.parseBoolean(value);
    }
}
