package hu.elte.eserial.parser;

import javafx.util.Pair;

/**
 * Parses null values.
 */
public class NullParser extends AbstractParser{

    NullParser(String value) {
        super(value);
    }

    @Override
    Object parser() {

        return null;
    }
}
