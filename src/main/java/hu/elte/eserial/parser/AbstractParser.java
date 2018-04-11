package hu.elte.eserial.parser;

/**
 * Abstract class for the different parsers.
 */
abstract class AbstractParser {

    protected String json;

    AbstractParser(String json) {
        this.json = json;
    }

    abstract Object parser();
}
