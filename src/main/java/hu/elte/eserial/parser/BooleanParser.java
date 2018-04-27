package hu.elte.eserial.parser;

/**
 * Parses booleans.
 */
public class BooleanParser extends AbstractParser {

    /**
     * {@inheritDoc}
     * @param {@inheritDoc}
     */
    BooleanParser(String json) {
        super(json);
    }

    /**
     * Returns the boolean representation of the string {@code json}.
     *
     * @return boolean representation of {@code json}
     */
    @Override
    public Boolean parse() {
        if(json.startsWith("true")){
            json = json.substring(4);
            return true;
        } else {
            json = json.substring(5);
            return false;
        }
    }
}
