package hu.elte.eserial.parser;

/**
 * Parses null values.
 */
public class NullParser extends AbstractParser{

    /**
     * Stores a string for parsing
     * @param json string to be parsed
     */
    NullParser(String value) {
        super(value);
    }

    /**
     * Returns the representation of null object.
     *
     * @return object representation of null a object
     */
    @Override
    Object parser() {
        return null;
    }
}
