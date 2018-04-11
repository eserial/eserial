package hu.elte.eserial.parser;

import hu.elte.eserial.util.StringUtils;
import javafx.util.Pair;

/**
 * Parses numbers.
 */
public class NumberParser extends AbstractParser{

    /**
     * Stores a string for parsing
     * @param json string to be parsed
     */
    NumberParser(String json) {
        super(json);
    }

    /**
     * Returns the number representation of the string {@code json}.
     *
     * @return number representation of {@code json}
     */
    @Override
    Number parser() {

        if(StringUtils.isInteger(json.trim())) {
            return Long.parseLong(json.trim());
        } else {
            return Double.parseDouble(json.trim());
        }
    }
}


