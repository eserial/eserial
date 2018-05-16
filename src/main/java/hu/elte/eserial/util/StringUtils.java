package hu.elte.eserial.util;

import hu.elte.eserial.model.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * Un-escape escaped characters in the string.
     *
     * @param str the string to be un-escaped
     * @return the un-escaped version of {@code str}
     */
    public static String unEscape(String str) {

        StringBuilder sb = new StringBuilder(str.length());

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '\\') {
                char nextChar = (i == str.length() - 1) ? '\\' : str
                        .charAt(i + 1);
                if (nextChar >= '0' && nextChar <= '7') {
                    StringBuilder stringBuilder = new StringBuilder();
                    i++;
                    if ((i < str.length() - 1) && str.charAt(i + 1) >= '0'
                            && str.charAt(i + 1) <= '7') {
                        stringBuilder.append(str.charAt(i + 1));
                        i++;
                        if ((i < str.length() - 1) && str.charAt(i + 1) >= '0'
                                && str.charAt(i + 1) <= '7') {
                            stringBuilder.append(str.charAt(i + 1));
                            i++;
                        }
                    }
                    sb.append((char) Integer.parseInt(stringBuilder.toString(), 8));
                    continue;
                }
                switch (nextChar) {
                    case '\\':
                        ch = '\\';
                        break;
                    case 'b':
                        ch = '\b';
                        break;
                    case 'f':
                        ch = '\f';
                        break;
                    case 'n':
                        ch = '\n';
                        break;
                    case 'r':
                        ch = '\r';
                        break;
                    case 't':
                        ch = '\t';
                        break;
                    case '\"':
                        ch = '\"';
                        break;
                    case '\'':
                        ch = '\'';
                        break;
                    default:
                        break;
                }
                i++;
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * Check the the param string is a Numeric
     *
     * @param str a string
     * @return boolean
     */
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Check the the param string is a Boolean
     *
     * @param str a string
     * @return boolean
     */
    public static boolean isBoolean(String str) {
        return str.equals("true") || str.equals("false");
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
    public static String findNumber(String str) {
        String number = null;

        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(str);
        if(matcher.find(0)) {
            number = matcher.group(0);
        }

        return number;
    }
}
