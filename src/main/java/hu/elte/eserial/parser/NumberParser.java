package hu.elte.eserial.parser;

import hu.elte.eserial.util.StringUtils;
import javafx.util.Pair;

/**
 * Parses numbers.
 */
public class NumberParser extends AbstractParser{

    NumberParser(String json) {
        super(json);
    }

    @Override
    Number parser() {

        if(StringUtils.isInteger(json.trim())) {
            return Long.parseLong(json.trim());
        } else {
            return Double.parseDouble(json.trim());
        }
    }
}


