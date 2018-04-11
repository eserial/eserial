package hu.elte.eserial.parser;

/**
 * Parses strings.
 */
public class StringParser extends AbstractParser{

    StringParser(String json) {
        super(json);
    }

    @Override
    String parser() {

        return json;
    }
}
