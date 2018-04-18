package hu.elte.eserial.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringUtilsTest {

    @Test
    public void lowercaseFirstLetter_GivenEmptyString_ReturnsEmptyString() {
        assertEquals("", StringUtils.lowercaseFirstLetter(""));
    }

    @Test
    public void lowercaseFirstLetter_GivenCapitalFirstLetter_LowercasesFirstLetter() {
        assertEquals("eserial", StringUtils.lowercaseFirstLetter("Eserial"));
    }

    @Test
    public void lowercaseFirstLetter_GivenCapitalFirstLetter_DoesNotModifyOtherParts() {
        assertEquals("eSERIAL", StringUtils.lowercaseFirstLetter("ESERIAL"));
    }

    @Test
    public void escape_GivenNull_ReturnsNull() {
        assertEquals(null, StringUtils.escape(null));
    }

    @Test
    public void escape_GivenEmptyString_ReturnsEmptyString() {
        assertEquals("", StringUtils.escape(""));
    }

    @Test
    public void escape_GivenNormalString_ReturnsNormalString() {
        assertEquals("eserial", StringUtils.escape("eserial"));
    }

    @Test
    public void escape_GivenStringWithSpecialCharacter_ReturnsEscapedString() {
        assertEquals("eserial\\\"", StringUtils.escape("eserial\""));
        assertEquals("eserial\\\\", StringUtils.escape("eserial\\"));
        assertEquals("eserial\\/", StringUtils.escape("eserial/"));
        assertEquals("eserial\\b", StringUtils.escape("eserial\b"));
        assertEquals("eserial\\f", StringUtils.escape("eserial\f"));
        assertEquals("eserial\\n", StringUtils.escape("eserial\n"));
        assertEquals("eserial\\r", StringUtils.escape("eserial\r"));
        assertEquals("eserial\\t", StringUtils.escape("eserial\t"));
    }

    @Test
    public void isNumeric_GivenStringWhichIsAnInteger_ReturnTrue() {
        assertTrue(StringUtils.isNumeric("2"));
    }

    @Test
    public void isNumeric_GivenStringWhichIsAnDouble_ReturnTrue() {
        assertTrue(StringUtils.isNumeric("2.0"));
    }

    @Test
    public void isNumeric_GivenStringWhichIsNotANumber_ReturnFalse() {
        assertFalse(StringUtils.isNumeric("test"));
    }

    @Test
    public void isBoolean_GivenStringWhichIsAFalseBoolean_ReturnTrue() {
        assertTrue(StringUtils.isBoolean("false"));
    }

    @Test
    public void isBoolean_GivenStringWhichIsATrueBoolean_ReturnTrue() {
        assertTrue(StringUtils.isBoolean("true"));
    }

    @Test
    public void isBoolean_GivenStringWhichIsNotABoolean_ReturnFalse() {
        assertFalse(StringUtils.isBoolean("test"));
    }

    @Test
    public void isInteger_GivenStringWhichIsAnInteger_ReturnTrue() {
        assertTrue(StringUtils.isInteger("2"));
    }

    @Test
    public void isInteger_GivenStringWhichIsAnDouble_ReturnFalse() {
        assertFalse(StringUtils.isInteger("2.0"));
    }

    @Test
    public void isInteger_GivenStringWhichIsANegativeInteger_ReturnTrue() {
        assertTrue(StringUtils.isInteger("-3"));
    }

    @Test
    public void isInteger_GivenStringWhichIsNotANumber_ReturnFalse() {
        assertFalse(StringUtils.isInteger("test"));
    }

    @Test
    public void isDouble_GivenStringWhichIsADouble_ReturnTrue() {
        assertTrue(StringUtils.isDouble("2.0"));
    }

    @Test
    public void isDouble_GivenStringWhichIsAInteger_ReturnFalse() {
        assertFalse(StringUtils.isDouble("2"));
    }

    @Test
    public void isDouble_GivenStringWhichIsNotANumber_ReturnFalse() {
        assertFalse(StringUtils.isDouble("test"));
    }

    @Test
    public void findNumber_GivenStringWhichStartWithInteger_ReturnString() {
        assertEquals("123", StringUtils.findNumber("123test"));
    }

    @Test
    public void findNumber_GivenStringWhichSecondCharacterIsADash_ReturnString() {
        assertEquals("1", StringUtils.findNumber("1-.123.123.test"));
    }

    @Test
    public void findNumber_GivenStringWhichStartWithDouble_ReturnString() {
        assertEquals("1.123", StringUtils.findNumber("1.123.123.test"));
    }

    @Test
    public void unEscape_GivenStringWithEscapedSlashNCharacter_ReturnUnescapedString() {
        assertEquals("tes\nt", StringUtils.unEscape("tes\\nt"));
    }

    @Test
    public void unEscape_GivenStringWithEscapedSlashBCharacters_ReturnUnescapedString() {
        assertEquals("tes\bt", StringUtils.unEscape("tes\\bt"));
    }

    @Test
    public void unEscape_GivenStringWithEscapedSlashFCharacters_ReturnUnescapedString() {
        assertEquals("tes\ft", StringUtils.unEscape("tes\\ft"));
    }

    @Test
    public void unEscape_GivenStringWithEscapedSlashRCharacters_ReturnUnescapedString() {
        assertEquals("tes\rt", StringUtils.unEscape("tes\\rt"));
    }

    @Test
    public void unEscape_GivenStringWithEscapedSlashTCharacters_ReturnUnescapedString() {
        assertEquals("tes\tt", StringUtils.unEscape("tes\\tt"));
    }
}