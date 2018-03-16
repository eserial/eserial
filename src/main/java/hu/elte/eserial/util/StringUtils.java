package hu.elte.eserial.util;

import com.sun.org.apache.xpath.internal.operations.Bool;
import hu.elte.eserial.model.Getter;

/**
 * Contains helper methods for the built-in String class.
 */
public class StringUtils {

    /**
     * Prevents the accidental instantiation of this utility class.
     */
    private StringUtils() {}

    /**
     * Converts the first letter of a string to lowercase.
     *
     * @param str an arbitrary string
     * @return {@code str} with lowercase initial
     * @see Getter#getElementName
     */
    public static String lowercaseFirstLetter(String str) {
        if (str.isEmpty()) {
            return str;
        }

        char[] characters = str.toCharArray();
        characters[0] = Character.toLowerCase(characters[0]);

        return new String(characters);
    }

    /**
     * Escapes a string.
     *
     * @param str the string to be escaped
     * @return the escaped version of {@code str}
     */
    public static String escape(String str) {
        if (str == null) {
            return null;
        }

        if (str.isEmpty()) {
            return str;
        }

        StringBuilder builder = new StringBuilder();

        char[] characters = str.toCharArray();

        for (Character character : characters) {
            switch (character) {
                case '"':
                    builder.append("\\\"");
                    break;
                case '\\':
                    builder.append("\\\\");
                    break;
                case '/':
                    builder.append("\\/");
                    break;
                case '\b':
                    builder.append("\\b");
                    break;
                case '\f':
                    builder.append("\\f");
                    break;
                case '\n':
                    builder.append("\\n");
                    break;
                case '\r':
                    builder.append("\\r");
                    break;
                case '\t':
                    builder.append("\\t");
                    break;
                default:
                    builder.append(character);
            }
        }

        return builder.toString();
    }
}
