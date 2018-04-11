package hu.elte.eserial.parser;

/**
 * Parses strings.
 */
public class StringParser extends AbstractParser{

    /**
     * Stores a string for parsing
     * @param json string to be parsed
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
    String parser() {

        return json;
    }
}
