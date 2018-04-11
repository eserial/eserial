package hu.elte.eserial.parser;

/**
 * Parses booleans.
 */
public class BooleanParser extends AbstractParser {

    BooleanParser(String json) {
        super(json);
    }

    @Override
    Boolean parser() {
        return Boolean.parseBoolean(json);
    }
}
