package hu.elte.eserial.builder;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ObjectBuilderTest {
    private ObjectBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = new ObjectBuilder();
    }

    public static class Point {
        private int x;
        private int y;
        private Person person;

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Person getPerson() {
            return person;
        }

        public void setPerson(Person person) {
            this.person = person;
        }
    }

    public static class Person {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void test() {
        Map<String, Object> map = new HashMap<>();
        map.put("x", 4);
        map.put("y", 5);

        Map<String, Object> pointMap = new HashMap<>();

        pointMap.put("name", "Bela");

        map.put("person", pointMap);

        Point point = new Point();

        builder.build(map, point);

        assertEquals(4, point.getX());
        assertEquals(5, point.getY());
        assertEquals("Bela", point.getPerson().getName());
    }
}
