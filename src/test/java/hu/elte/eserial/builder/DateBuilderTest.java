package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialBuilderMismatchException;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DateBuilderTest {

    @Test(expected = EserialBuilderMismatchException.class)
    public void build_GivenInvalidType_ThrowsEserialBuilderMismatchException() {
        new DateBuilder(Integer.class).build(3000000L);
    }

    @Test
    public void build_GivenStringValue_ReturnsDateWithValue() {
        Date date = new Date();
        Long value = date.getTime();
        DateBuilder dateBuilder = new DateBuilder(Date.class);

        assertEquals(date, dateBuilder.build(value.toString()));
    }

    @Test
    public void build_GivenDateNullValue_ReturnsNullValue() {
        assertNull(new EnumBuilder(Date.class).build(null));
    }

    @Test
    public void build_GivenLongValue_ReturnDateWithValue() {
        Date date = new Date();
        Long value = date.getTime();
        DateBuilder dateBuilder = new DateBuilder(Date.class);

        assertEquals(date, dateBuilder.build(value));

    }
}
