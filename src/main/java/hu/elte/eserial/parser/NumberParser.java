package hu.elte.eserial.parser;

import hu.elte.eserial.util.StringUtils;
import javafx.util.Pair;

/**
 * Parses numbers.
 */
public class NumberParser extends AbstractParser{

    /**
     * {@inheritDoc}
     * @param {@inheritDoc}
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
    Number parse() {

        if(StringUtils.isInteger(json)) {
            return Long.parseLong(json);
        } else {
            return Double.parseDouble(json);
        }
    }
}


