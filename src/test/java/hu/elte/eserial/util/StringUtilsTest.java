package hu.elte.eserial.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

    @Test
    public void lowercaseFirstLetter_ifEmpty() {
        assertEquals("", StringUtils.lowercaseFirstLetter(""));
    }

    @Test
    public void lowercaseFirstLetter_modifiesFirstLetter() {
        assertEquals("eserial", StringUtils.lowercaseFirstLetter("Eserial"));
    }

    @Test
    public void lowercaseFirstLetter_doesNotModifyOtherParts() {
        assertEquals("eSERIAL", StringUtils.lowercaseFirstLetter("ESERIAL"));
    }
}