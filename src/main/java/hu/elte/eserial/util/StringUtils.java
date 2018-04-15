package hu.elte.eserial.util;

import hu.elte.eserial.model.Getter;

import java.util.HashMap;
import java.util.Map;

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

        Map<Character, String> escaped = new HashMap<>();
        escaped.put('"', "\\\"");
        escaped.put('\\', "\\\\");
        escaped.put('/', "\\/");
        escaped.put('\b', "\\b");
        escaped.put('\f', "\\f");
        escaped.put('\n', "\\n");
        escaped.put('\r', "\\r");
        escaped.put('\t', "\\t");

        for (Character character : characters) {
            if (escaped.containsKey(character)) {
                builder.append(escaped.get(character));
            } else {
                builder.append(character);
            }
        }

        return builder.toString();
    }

    /**
     * Check the the param string is a Numeric
     *
     * @param str a string
     * @return boolean
     */
    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Check the the param string is a Boolean
     *
     * @param str a string
     * @return boolean
     */
    public static boolean isBoolean(String str) {
        if(str.equals("false") || str.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check the the param string is an Integer
     *
     * @param str a string
     * @return boolean
     */
    public static boolean isInteger(String str) {
        return str.matches("-?[\\d]+");
    }

    /**
     * Check the the param string is an Double
     *
     * @param str a string
     * @return boolean
     */
    public static boolean isDouble(String str) {
        return str.matches("-?[0-9]+\\.?[0-9]+");
    }

    /**
     * Find the number at the beginning of the String
     *
     * @param str a string
     * @return {@code str} the index of the last digit
     */
    public static int findNumber(String str) {
        int index = 0;

        while(index < str.length() && (Character.isDigit(str.charAt(index)) || str.charAt(index) == '.' || str.charAt(index) == '-') ) {
            index++;
        }

        return index - 1;
    }

    /**
     * Find the closing curly bracket int the given string
     *
     * @param str a string
     * @return {@code closePos} the index of the closing curly bracket
     */
    public static int findClosingCurlyBracket(String str) {
        int closePos = 0;
        int counter = 1;

        while (counter > 0) {
            char c = str.charAt(++closePos);
            if (c == '{') {
                counter++;
            }
            else if (c == '}') {
                counter--;
            }
        }
        return closePos;
    }


    /**
     * Find the closing square bracket int the given string
     *
     * @param str a string
     * @return {@code closePos} the index of the closing square bracket
     */
    public static int findSquareBracket(String str) {
        int closePos = 0;
        int counter = 1;

        while (counter > 0) {
            char c = str.charAt(++closePos);
            if (c == '[') {
                counter++;
            }
            else if (c == ']') {
                counter--;
            }
        }
        return closePos;
    }
}
