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
}