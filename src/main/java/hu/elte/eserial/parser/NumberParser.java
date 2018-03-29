package hu.elte.eserial.parser;

import hu.elte.eserial.util.StringUtils;
import javafx.util.Pair;

/**
 * Parses numbers.
 */
public class NumberParser {
    public Number parser(String value) {

        if(StringUtils.isInteger(value.trim())) {
            return Long.parseLong(value.trim());
        } else {
            return Double.parseDouble(value.trim());
        }
    }
}


