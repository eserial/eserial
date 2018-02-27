package hu.elte.eserial.util;

import hu.elte.eserial.Getter;

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
}
