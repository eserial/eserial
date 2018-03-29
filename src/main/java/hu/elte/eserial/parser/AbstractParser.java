package hu.elte.eserial.parser;

/**
 * Abstract class for the different parsers.
 */
abstract class AbstractParser {

    protected String value;

    AbstractParser(String value) {
        this.value = value;
    }

    abstract Object parser();
}
