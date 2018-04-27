package hu.elte.eserial.parser;

/**
 * Parses null values.
 */
public class NullParser extends AbstractParser{

    /**
     * {@inheritDoc}
     * @param {@inheritDoc}
     */
    NullParser(String json) {
        super(json);
    }

    /**
     * Returns the representation of null object.
     *
     * @return object representation of null a object
     */
    @Override
    public Object parse() {
        json = json.substring(4);
        return null;
    }
}
