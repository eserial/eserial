package hu.elte.eserial.parser;

/**
 * Abstract class for the different parsers.
 */
abstract class AbstractParser {

    protected String json;

    /**
     * Stores a string for parsing
     * @param json string to be parsed
     */
    AbstractParser(String json) {
        this.json = json;
    }

    /**
     * Builds an object and initializes it with the given value
     * Returns the right Object representation of {@code json}
     *
     * @return Object representation of {@code json}
     */
    abstract Object parse();
}
