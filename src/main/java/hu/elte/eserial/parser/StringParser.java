package hu.elte.eserial.parser;

import hu.elte.eserial.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        json = json.substring(1);

        int index = 0;

        Pattern pattern = Pattern.compile("(?<!\\\\)(?:\\\\{2})*\"");
        Matcher matcher = pattern.matcher(json);

        if(matcher.find()) {
            index = matcher.start();
        }

        String value = json.substring(0, index);
        json = json.substring(index + 1);

        return StringUtils.unEscape(value);
    }
}
