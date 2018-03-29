package hu.elte.eserial.parser;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses strings.
 */
public class StringParser {

    public String parser(String value) {

        return value.substring(1, value.length() - 1);
    }
}
