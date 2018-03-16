package hu.elte.eserial.builder;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

public class CompoundBuilderTest {

    public static class PrimitiveDataMembers {
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

    public static class EnumDataMember {
        private Color colorDataMember;

        public Color getColorDataMember() {
            return colorDataMember;
        }

        public void setColorDataMember(Color colorDataMember) {
            this.colorDataMember = colorDataMember;
        }
    }

    public static class CollectionDataMember {
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

    public static class MapDataMembers {
        private Map map;
        private SortedMap sortedMap;
        private NavigableMap navigableMap;
        private ConcurrentNavigableMap concurrentNavigableMap;
        private TreeMap treeMap;
        private HashMap hashMap;
        private ConcurrentSkipListMap concurrentSkipListMap;

        public SortedMap getSortedMap() {
            return sortedMap;
        }

        public void setSortedMap(SortedMap sortedMap) {
            this.sortedMap = sortedMap;
        }

        public NavigableMap getNavigableMap() {
            return navigableMap;
        }

        public void setNavigableMap(NavigableMap navigableMap) {
            this.navigableMap = navigableMap;
        }

        public ConcurrentNavigableMap getConcurrentNavigableMap() {
            return concurrentNavigableMap;
        }

        public void setConcurrentNavigableMap(ConcurrentNavigableMap concurrentNavigableMap) {
            this.concurrentNavigableMap = concurrentNavigableMap;
        }

        public TreeMap getTreeMap() {
            return treeMap;
        }

        public void setTreeMap(TreeMap treeMap) {
            this.treeMap = treeMap;
        }

        public HashMap getHashMap() {
            return hashMap;
        }

        public void setHashMap(HashMap hashMap) {
            this.hashMap = hashMap;
        }

        public ConcurrentSkipListMap getConcurrentSkipListMap() {
            return concurrentSkipListMap;
        }

        public void setConcurrentSkipListMap(ConcurrentSkipListMap concurrentSkipListMap) {
            this.concurrentSkipListMap = concurrentSkipListMap;
        }

        public Map getMap() {
            return map;
        }

        public void setMap(Map map) {
            this.map = map;
        }
    }

    public enum Color {
        RED, GREEN, BLUE
    }

    public static class Person {
        private String name;
        private int age;
        private Lesson lesson;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Lesson getLesson() {
            return lesson;
        }

        public void setLesson(Lesson lesson) {
            this.lesson = lesson;
        }
    }

    public static class Lesson {
        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    @Test
    public void build_GivenPrimitiveDataMembers_ReturnsObjectWithValues() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("intDataMember", 1L);
        map.put("floatDataMember", 5.3d);
        map.put("shortDataMember", 2L);
        map.put("doubleDataMember", 4.2d);
        map.put("charDataMember", 'c');
        map.put("longDataMember", 10L);
        map.put("booleanDataMember", true);
        map.put("byteDataMember", 3L);

        CompoundBuilder builder = new CompoundBuilder(PrimitiveDataMembers.class);

        PrimitiveDataMembers pdm = builder.build(map);

        assertEquals(1, pdm.getIntDataMember());
        assertEquals(5.3f, pdm.getFloatDataMember(), 0.001);
        assertEquals(2, pdm.getShortDataMember());
        assertEquals(4.2d, pdm.getDoubleDataMember(), 0.001);
        assertEquals(10, pdm.getLongDataMember());
        assertEquals(true, pdm.isBooleanDataMember());
        assertEquals('c', pdm.getCharDataMember());
        assertEquals(3, pdm.getByteDataMember());
    }

    @Test
    public void build_GivenEnumDataMember_ReturnsObjectWithValue() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("colorDataMember", 2L);

       CompoundBuilder builder = new CompoundBuilder(EnumDataMember.class);

        EnumDataMember edm = builder.build(map);

        assertEquals(2, edm.getColorDataMember().ordinal());
    }

    @Test
    public void build_GivenCollectionDataMembers_ReturnsObjectWithValues() throws Exception {

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

         CompoundBuilder builder = new CompoundBuilder(CollectionDataMember.class);

        CollectionDataMember cdm = builder.build(map);

        assertEquals(3, cdm.getSortedSetDataMember().size());
        assertEquals(3, cdm.getSetDataMember().size());
        assertEquals(3, cdm.getQueueDataMember().size());
        assertEquals(3, cdm.getListDataMember().size());
        assertEquals(3, cdm.getLinkedListDataMember().size());
        assertEquals(3, cdm.getVectorDataMember().size());
        assertEquals(3, cdm.getBlockingQueueDataMember().size());
        assertEquals(3, cdm.getBlockingDequeDataMember().size());
        assertEquals(3, cdm.getNavigableSetDataMember().size());
        assertEquals(3, cdm.getDequeDataMember().size());
        assertEquals(3, cdm.getTransferQueueDataMember().size());
    }

    @Test
    public void build_GivenMapDataMember_ReturnsObjectWithValues() throws Exception {
        Map<String, Object> map = new HashMap<>();

        Map<Object, Object> hashMap = new HashMap<>();
        hashMap.put("1", 1);
        hashMap.put("2", 2);
        hashMap.put("3", 3);

        map.put("map", hashMap);
        map.put("sortedMap", hashMap);
        map.put("navigableMap", hashMap);
        map.put("concurrentNavigableMap", hashMap);
        map.put("concurrentSkipListMap", hashMap);
        map.put("hashMap", hashMap);
        map.put("treeMap", hashMap);

        CompoundBuilder builder = new CompoundBuilder(MapDataMembers.class);

        MapDataMembers mdm =  builder.build(map);

        assertEquals(3, mdm.getMap().size());
        assertEquals(3, mdm.getConcurrentNavigableMap().size());
        assertEquals(3, mdm.getConcurrentSkipListMap().size());
        assertEquals(3, mdm.getHashMap().size());
        assertEquals(3, mdm.getTreeMap().size());
        assertEquals(3, mdm.getNavigableMap().size());
        assertEquals(3, mdm.getSortedMap().size());
    }

    @Test
    public void build_GivenSimpleAndCompoundDataMembers_ReturnObjectWithValues() throws Exception {

        Map<String, Object> lessonMap = new HashMap<>();
        lessonMap.put("id", 2L);
        lessonMap.put("name", "Anal");

        Map<Object, Object> basicMap = new HashMap<>();
        basicMap.put("name", "Janos");
        basicMap.put("age", 22L);
        basicMap.put("lesson", lessonMap);

        CompoundBuilder builder = new CompoundBuilder(Person.class);

        Person person = builder.build(basicMap);

        assertEquals("Janos", person.getName());
        assertEquals(22, person.getAge());
        assertEquals("Anal", person.getLesson().getName());
        assertEquals(2, person.getLesson().getId());
    }
}
