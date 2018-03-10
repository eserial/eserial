package hu.elte.eserial.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}