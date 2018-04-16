package hu.elte.eserial.parser;

/**
 * Parses strings.
 */
public class StringParser extends AbstractParser{

    /**
     * {@inheritDoc}
     * @param {@inheritDoc}
     */
    StringParser(String json) {
        super(json);
    }

    /**
     * Returns the string representation of the string {@code json}.
     *
     * @return string representation of {@code json}
     */
    @Override
    String parse() {
        if(json == null) {
            throw new NullPointerException();
        }
        return json;
    }
}
