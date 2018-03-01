package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

public class DateMapperTest {

    private DateMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new DateMapper();
    }

    @Test(expected = NullPointerException.class)
    public void map_GivenNull_ThrowsNullPointerException() {
        mapper.map(null, null);
    }

    @Test(expected = EserialMapperMismatchException.class)
    public void map_GivenInvalidType_ThrowsEserialMapperMismatchException() {
        mapper.map(0, null);
    }

    @Test
    public void map_GivenADate_ReturnsItsTimestampInMs() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = sdf.parse("01/01/2018");

        assertEquals(1514764800000L, mapper.map(date, null));
    }
}