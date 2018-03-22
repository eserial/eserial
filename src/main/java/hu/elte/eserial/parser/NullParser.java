package hu.elte.eserial.parser;

/**
 * Parses null values.
 */
public class NullParser {
    public ParserEntry<String, Object> parser(String json) {
        json = json.replace("{", "");
        json = json.replace("}", "");
        json = json.replace("\"", "");

        String[] parts = json.split(":");

        ParserEntry<String, Object> entry = new ParserEntry(parts[0].trim(), null);

        return entry;
    }
}
