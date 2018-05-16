package hu.elte.eserial.parser;

import hu.elte.eserial.util.StringUtils;

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
    public Number parse() {

        String value = StringUtils.findNumber(json);
        json = json.substring(value.length());

        if(StringUtils.isInteger(value)) {
            return Long.parseLong(value);
        } else {
            return Double.parseDouble(value);
        }
    }
}


