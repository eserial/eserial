package hu.elte.eserial.builder;

import org.junit.Before;
import org.junit.Test;

import java.beans.beancontext.BeanContext;
import java.util.*;
import java.util.concurrent.*;

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
    }

    public static class ClassWithEnumDataMember {
        private Color colorDataMember;

        public Color getColorDataMember() {
            return colorDataMember;
        }

        public void setColorDataMember(Color colorDataMember) {
            this.colorDataMember = colorDataMember;
        }
    }

    public static class ClassWithCollectionDataMember {
        private SortedSet sortedSetDataMember;
        private Set setDataMember;
        private Queue queueDataMember;
        private List listDataMember;
        private LinkedList linkedListDataMember;
        private Vector vectorDataMember;
        private BlockingQueue blockingQueueDataMember;
        private BlockingDeque blockingDequeDataMember;
        private NavigableSet navigableSetDataMember;
        private Deque dequeDataMember;
        private TransferQueue transferQueueDataMember;

        public TransferQueue getTransferQueueDataMember() {
            return transferQueueDataMember;
        }

        public void setTransferQueueDataMember(TransferQueue transferQueueDataMember) {
            this.transferQueueDataMember = transferQueueDataMember;
        }

        public Deque getDequeDataMember() {
            return dequeDataMember;
        }

        public void setDequeDataMember(Deque dequeDataMember) {
            this.dequeDataMember = dequeDataMember;
        }

        public NavigableSet getNavigableSetDataMember() {
            return navigableSetDataMember;
        }

        public void setNavigableSetDataMember(NavigableSet navigableSetDataMember) {
            this.navigableSetDataMember = navigableSetDataMember;
        }

        public BlockingDeque getBlockingDequeDataMember() {
            return blockingDequeDataMember;
        }

        public void setBlockingDequeDataMember(BlockingDeque blockingDequeDataMember) {
            this.blockingDequeDataMember = blockingDequeDataMember;
        }

        public BlockingQueue getBlockingQueueDataMember() {
            return blockingQueueDataMember;
        }

        public void setBlockingQueueDataMember(BlockingQueue blockingQueueDataMember) {
            this.blockingQueueDataMember = blockingQueueDataMember;
        }

        public Vector getVectorDataMember() {
            return vectorDataMember;
        }

        public void setVectorDataMember(Vector vectorDataMember) {
            this.vectorDataMember = vectorDataMember;
        }


        public LinkedList getLinkedListDataMember() {
            return linkedListDataMember;
        }

        public void setLinkedListDataMember(LinkedList linkedListDataMember) {
            this.linkedListDataMember = linkedListDataMember;
        }

        public SortedSet getSortedSetDataMember() {
            return sortedSetDataMember;
        }

        public void setSortedSetDataMember(SortedSet sortedSetDataMember) {
            this.sortedSetDataMember = sortedSetDataMember;
        }

        public Set getSetDataMember() {
            return setDataMember;
        }

        public void setSetDataMember(Set setDataMember) {
            this.setDataMember = setDataMember;
        }

        public Queue getQueueDataMember() {
            return queueDataMember;
        }

        public void setQueueDataMember(Queue queueDataMember) {
            this.queueDataMember = queueDataMember;
        }

        public List getListDataMember() {
            return listDataMember;
        }

        public void setListDataMember(List listDataMember) {
            this.listDataMember = listDataMember;
        }
    }

    public enum Color {
        RED, GREEN, BLUE
    }

    @Test
    public void build_GivenClassWithPrimitiveDataMembers_ReturnsObjectWithValues() {
        Map<String, Object> map = new HashMap<>();
        map.put("intDataMember", 1L);
        map.put("floatDataMember", 5.3d);
        map.put("shortDataMember", 2L);
        map.put("doubleDataMember", 4.2d);
        map.put("charDataMember", 'c');
        map.put("longDataMember", 10L);
        map.put("booleanDataMember", true);
        map.put("byteDataMember", 3L);

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
    }

    @Test
    public void build_GivenClassWithEnumDataMember_ReturnsObjectWithValue() {
        Map<String, Object> map = new HashMap<>();
        map.put("colorDataMember", 2L);

        ClassWithEnumDataMember cwedm = new ClassWithEnumDataMember();

        builder.build(map, cwedm);

        assertEquals(2, cwedm.getColorDataMember().ordinal());
    }

    @Test
    public void build_GivenClassWithCollectionDataMembers_ReturnObjectWithValues() throws Exception {

        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);

        Map<String, Object> map = new HashMap<>();

        map.put("sortedSetDataMember", list);
        map.put("setDataMember", list);
        map.put("queueDataMember", list);
        map.put("listDataMember", list);
        map.put("linkedListDataMember", list);
        map.put("vectorDataMember", list);
        map.put("blockingQueueDataMember", list);
        map.put("blockingDequeDataMember", list);
        map.put("navigableSetDataMember", list);
        map.put("dequeDataMember", list);
        map.put("transferQueueDataMember", list);

        ClassWithCollectionDataMember cwcdm = new ClassWithCollectionDataMember();

        builder.build(map, cwcdm);

        assertEquals(3, cwcdm.getSortedSetDataMember().size());
        assertEquals(3, cwcdm.getSetDataMember().size());
        assertEquals(3, cwcdm.getQueueDataMember().size());
        assertEquals(3, cwcdm.getListDataMember().size());
        assertEquals(3, cwcdm.getLinkedListDataMember().size());
        assertEquals(3, cwcdm.getVectorDataMember().size());
        assertEquals(3, cwcdm.getBlockingQueueDataMember().size());
        assertEquals(3, cwcdm.getBlockingDequeDataMember().size());
        assertEquals(3, cwcdm.getNavigableSetDataMember().size());
        assertEquals(3, cwcdm.getDequeDataMember().size());
        assertEquals(3, cwcdm.getTransferQueueDataMember().size());
    }
}
