package hu.elte.eserial.builder;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentNavigableMap;

import static org.junit.Assert.assertEquals;

public class CompoundBuilderTest {
    private CompoundBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = new CompoundBuilder();
    }

    public static class ClassWithPrimitiveDataMembers {
        private int intDataMember;
        private float floatDataMember;
        private short shortDataMember;
        private double doubleDataMember;
        private long longDataMember;
        private boolean booleanDataMember;
        private byte byteDataMember;
        private char charDataMember;
        private Color color;

        public int getIntDataMember() {
            return intDataMember;
        }

        public void setIntDataMember(int intDataMember) {
            this.intDataMember = intDataMember;
        }

        public float getFloatDataMember() {
            return floatDataMember;
        }

        public void setFloatDataMember(float floatDataMember) {
            this.floatDataMember = floatDataMember;
        }

        public short getShortDataMember() {
            return shortDataMember;
        }

        public void setShortDataMember(short shortDataMember) {
            this.shortDataMember = shortDataMember;
        }

        public double getDoubleDataMember() {
            return doubleDataMember;
        }

        public void setDoubleDataMember(double doubleDataMember) {
            this.doubleDataMember = doubleDataMember;
        }

        public long getLongDataMember() {
            return longDataMember;
        }

        public void setLongDataMember(long longDataMember) {
            this.longDataMember = longDataMember;
        }

        public boolean isBooleanDataMember() {
            return booleanDataMember;
        }

        public void setBooleanDataMember(boolean booleanDataMember) {
            this.booleanDataMember = booleanDataMember;
        }

        public byte getByteDataMember() {
            return byteDataMember;
        }

        public void setByteDataMember(byte byteDataMember) {
            this.byteDataMember = byteDataMember;
        }

        public char getCharDataMember() {
            return charDataMember;
        }

        public void setCharDataMember(char charDataMember) {
            this.charDataMember = charDataMember;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
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

    public static enum Color {
        RED, GREEN, BLUE
    }

    @Test
    public void build_GivenClassWithPrimiteDataMembers_ReturnsObjectWithValues() {
        Map<String, Object> map = new HashMap<>();
        map.put("intDataMember", 1L);
        map.put("floatDataMember", 5.3d);
        map.put("shortDataMember", 2L);
        map.put("doubleDataMember", 4.2d);
        map.put("charDataMember", 'c');
        map.put("longDataMember", 10L);
        map.put("booleanDataMember", true);
        map.put("byteDataMember", 3L);
        map.put("color", 2L);

        ClassWithPrimitiveDataMembers cwpdm = new ClassWithPrimitiveDataMembers();

        builder.build(map, cwpdm);

        assertEquals(1, cwpdm.getIntDataMember());
        assertEquals(5.3f, cwpdm.getFloatDataMember(), 0.001);
        assertEquals(2, cwpdm.getShortDataMember());
        assertEquals(4.2d, cwpdm.getDoubleDataMember(), 0.001);
        assertEquals(10, cwpdm.getLongDataMember());
        assertEquals(true, cwpdm.isBooleanDataMember());
        assertEquals('c', cwpdm.getCharDataMember());
        assertEquals(3, cwpdm.getByteDataMember());
        assertEquals(2, cwpdm.getColor().ordinal());
    }
}
