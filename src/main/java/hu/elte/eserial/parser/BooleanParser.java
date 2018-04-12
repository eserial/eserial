package hu.elte.eserial.parser;

/**
 * Parses booleans.
 */
public class BooleanParser extends AbstractParser {

    /**
     * Stores a string for parsing
     * @param json string to be parsed
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
    Boolean parser() {
        if(json == null) {
            throw new NullPointerException();
        }

        return Boolean.parseBoolean(json);
    }
}
