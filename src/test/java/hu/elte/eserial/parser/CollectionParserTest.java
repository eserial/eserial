package hu.elte.eserial.parser;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class CollectionParserTest {
    @Test
    public void parser_GivenAJsonWhichRepresentAListOfNumbers_ReturnLongList() {
        LinkedList<Object> testList = new CollectionParser().parser("2, 3,4, 5 ");

        Assert.assertTrue(4 == testList.size());
        Assert.assertEquals(testList.get(0), new Long(2));
        Assert.assertEquals(testList.get(1), new Long(3));
        Assert.assertEquals(testList.get(2), new Long(4));
        Assert.assertEquals(testList.get(3), new Long(5));
    }

    @Test
    public void parser_GivenAJsonWhichRepresentAListOfBooleans_ReturnBooleanList() {
        LinkedList<Object> testList = new CollectionParser().parser("true, false, true, false");

        Assert.assertTrue(4 == testList.size());
        Assert.assertEquals(testList.get(0), true);
        Assert.assertEquals(testList.get(1), false);
        Assert.assertEquals(testList.get(2), true);
        Assert.assertEquals(testList.get(3), false);
    }

    @Test
    public void parser_GivenAJsonWhichRepresentAListOfStrings_ReturnStringList() {
        LinkedList<Object> testList = new CollectionParser().parser("\"test1 \", \"test2\", \"test3\", \"test4\"");

        Assert.assertTrue(4 == testList.size());

        Assert.assertEquals(testList.get(0), "test1 ");
        Assert.assertEquals(testList.get(1), "test2");
        Assert.assertEquals(testList.get(2), "test3");
        Assert.assertEquals(testList.get(3), "test4");
    }
}
