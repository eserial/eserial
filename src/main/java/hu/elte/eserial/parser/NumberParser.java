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

        int index = StringUtils.findNumber(json);
        String value = json.substring(0, index + 1);
        json = json.substring(index + 1);

        if(StringUtils.isInteger(value)) {
            return Long.parseLong(value);
        } else {
            return Double.parseDouble(value);
        }
    }
}


