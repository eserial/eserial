package hu.elte.eserial.parser;

/**
 * Parses booleans.
 */
public class BooleanParser extends AbstractParser {

    /**
     * {@inheritDoc}
     * @param {@inheritDoc}
     */
    BooleanParser(String json) {
        super(json);
    }

    /**
     * Returns the boolean representation of the string {@code json}.
     *
     * @return boolean representation of {@code json}
     */
    @Override
    Boolean parse() {
        if(json == null) {
            throw new NullPointerException();
        }

        return Boolean.parseBoolean(json);
    }
}
