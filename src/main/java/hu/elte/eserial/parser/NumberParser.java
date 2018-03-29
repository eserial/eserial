package hu.elte.eserial.parser;

import hu.elte.eserial.util.StringUtils;
import javafx.util.Pair;

/**
 * Parses numbers.
 */
public class NumberParser extends AbstractParser{

    NumberParser(String value) {
        super(value);
    }

    @Override
    Number parser() {

        if(StringUtils.isInteger(value.trim())) {
            return Long.parseLong(value.trim());
        } else {
            return Double.parseDouble(value.trim());
        }
    }
}


