package hu.elte.eserial.builder;

import hu.elte.eserial.exception.EserialInstantiationException;
import hu.elte.eserial.exception.EserialInvalidMethodException;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void build_GivenPrimitiveDataMembers_ReturnsObjectWithValues() {
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
        private List<Integer> listDataMember;
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

        public List<Integer> getListDataMember() {
            return listDataMember;
        }

        public void setListDataMember(List<Integer> listDataMember) {
            this.listDataMember = listDataMember;
        }
    }

    @Test
    public void build_GivenCollectionDataMembers_ReturnsObjectWithValues() {

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

    public static class MapDataMembers {
        private Map map;
        private SortedMap sortedMap;
        private NavigableMap navigableMap;
        private ConcurrentNavigableMap concurrentNavigableMap;
        private TreeMap<String, Integer> treeMap;
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

        public TreeMap<String, Integer> getTreeMap() {
            return treeMap;
        }

        public void setTreeMap(TreeMap<String, Integer> treeMap) {
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

    @Test
    public void build_GivenMapDataMember_ReturnsObjectWithValues() {
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

    public static class DateDataMember {
       private Date date;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }

    @Test
    public void build_GivenDateDataMember_ReturnsObjectWithValue() {
        Map<String, Object> map = new HashMap<>();

        Date date = new Date();
        map.put("date", date.getTime());

        CompoundBuilder compoundBuilder = new CompoundBuilder(DateDataMember.class);

        DateDataMember ddm = compoundBuilder.build(map);

        assertEquals(date, ddm.getDate());
    }

    public static class CompoundTestClassOne {
        private CompoundTestClassTwo compoundTestClassTwo;
        private List<CompoundTestClassTwo> compoundTestClassTwoList;
        private List<List<CompoundTestClassTwo>> compoundTestClassTwoListList;
        private Map<String, CompoundTestClassTwo> compoundTestClassTwoMap;
        private Map<CompoundTestClassTwo, CompoundTestClassTwo> compoundTestClassTwoMap2;

        public CompoundTestClassTwo getCompoundTestClassTwo() {
            return compoundTestClassTwo;
        }

        public void setCompoundTestClassTwo(CompoundTestClassTwo compoundTestClassTwo) {
            this.compoundTestClassTwo = compoundTestClassTwo;
        }

        public List<CompoundTestClassTwo> getCompoundTestClassTwoList() {
            return compoundTestClassTwoList;
        }

        public void setCompoundTestClassTwoList(List<CompoundTestClassTwo> compoundTestClassTwoList) {
            this.compoundTestClassTwoList = compoundTestClassTwoList;
        }

        public List<List<CompoundTestClassTwo>> getCompoundTestClassTwoListList() {
            return compoundTestClassTwoListList;
        }

        public void setCompoundTestClassTwoListList(List<List<CompoundTestClassTwo>> compoundTestClassTwoListList) {
            this.compoundTestClassTwoListList = compoundTestClassTwoListList;
        }

        public Map<String, CompoundTestClassTwo> getCompoundTestClassTwoMap() {
            return compoundTestClassTwoMap;
        }

        public void setCompoundTestClassTwoMap(Map<String, CompoundTestClassTwo> compoundTestClassTwoMap) {
            this.compoundTestClassTwoMap = compoundTestClassTwoMap;
        }

        public Map<CompoundTestClassTwo, CompoundTestClassTwo> getCompoundTestClassTwoMap2() {
            return compoundTestClassTwoMap2;
        }

        public void setCompoundTestClassTwoMap2(Map<CompoundTestClassTwo, CompoundTestClassTwo> compoundTestClassTwoMap2) {
            this.compoundTestClassTwoMap2 = compoundTestClassTwoMap2;
        }
    }

    @Test
    public void build_GivenCompoundDataMember_ReturnsObjectWithValue() {
        Map<String, Object> classOneMap = new HashMap<>();
        Map<String, Object> classTwoMap = new HashMap<>();

        List<Integer> integerList1 = new ArrayList<>();
        integerList1.add(1);
        integerList1.add(2);

        List<Integer> integerList2 = new ArrayList<>();
        integerList2.add(3);
        integerList2.add(4);

        List<List<Integer>> integerListList = new ArrayList<>();
        integerListList.add(integerList1);
        integerListList.add(integerList2);

        classTwoMap.put("string", "test");
        classTwoMap.put("list", integerList1);
        classTwoMap.put("listList", integerListList);

        classOneMap.put("compoundTestClassTwo", classTwoMap);

        CompoundBuilder compoundBuilder = new CompoundBuilder(CompoundTestClassOne.class);

        CompoundTestClassOne compoundTestClassOne = compoundBuilder.build(classOneMap);

        assertEquals(CompoundTestClassTwo.class, compoundTestClassOne.getCompoundTestClassTwo().getClass());
    }

    public static class CompoundTestClassTwo {
        private String string;
        private List<Integer> list;
        private List<List<Integer>> listList;

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }

        public List<Integer> getList() {
            return list;
        }

        public void setList(List<Integer> list) {
            this.list = list;
        }

        public List<List<Integer>> getListList() {
            return listList;
        }

        public void setListList(List<List<Integer>> listList) {
            this.listList = listList;
        }
    }

    @Test
    public void build_GivenNullValue_ReturnsObjectWithNullValue() {
        CompoundBuilder compoundBuilder = new CompoundBuilder(CompoundTestClassTwo.class);

        CompoundTestClassTwo compoundTestClassTwo = compoundBuilder.build(null);

        assertNull(compoundTestClassTwo);
    }

    @Test
    public void build_GivenWrapperAndListAndListInListDataMember_ReturnsObjectWithValues() {
        Map<String, Object> map = new HashMap<>();

        List<Integer> integerList1 = new ArrayList<>();
        integerList1.add(1);
        integerList1.add(2);

        List<Integer> integerList2 = new ArrayList<>();
        integerList2.add(3);
        integerList2.add(4);

        List<List<Integer>> integerListList = new ArrayList<>();
        integerListList.add(integerList1);
        integerListList.add(integerList2);

        map.put("string", "test");
        map.put("list", integerList1);
        map.put("listList", integerListList);

        CompoundBuilder builder = new CompoundBuilder(CompoundTestClassTwo.class);

        CompoundTestClassTwo compoundTestClassTwo = builder.build(map);

        assertEquals("test", compoundTestClassTwo.getString());
        assertEquals(2, compoundTestClassTwo.getList().size());
        assertEquals(1, compoundTestClassTwo.getList().get(0).intValue());
        assertEquals(2, compoundTestClassTwo.getList().get(1).intValue());
        assertEquals(2, compoundTestClassTwo.getListList().size());
        assertEquals(1, compoundTestClassTwo.getListList().get(0).get(0).intValue());
        assertEquals(2, compoundTestClassTwo.getListList().get(0).get(1).intValue());
        assertEquals(3, compoundTestClassTwo.getListList().get(1).get(0).intValue());
        assertEquals(4, compoundTestClassTwo.getListList().get(1).get(1).intValue());
    }

    @Test
    public void build_GivenListOfCompoundType_ReturnsObjectWithValue() {
        Map<String, Object> classOneMap = new HashMap<>();
        Map<String, Object> classTwoMap = new HashMap<>();

        List<Integer> integerList1 = new ArrayList<>();
        integerList1.add(1);
        integerList1.add(2);

        List<Integer> integerList2 = new ArrayList<>();
        integerList2.add(3);
        integerList2.add(4);

        List<List<Integer>> integerListList = new ArrayList<>();
        integerListList.add(integerList1);
        integerListList.add(integerList2);

        classTwoMap.put("string", "test");
        classTwoMap.put("list", integerList1);
        classTwoMap.put("listList", integerListList);

        List<Object> compoundTestClassTwoList = new ArrayList<>();
        compoundTestClassTwoList.add(classTwoMap);

        classOneMap.put("compoundTestClassTwoList", compoundTestClassTwoList);

        CompoundBuilder compoundBuilder = new CompoundBuilder(CompoundTestClassOne.class);

        CompoundTestClassOne compoundTestClassOne = compoundBuilder.build(classOneMap);

        assertEquals(1, compoundTestClassOne.getCompoundTestClassTwoList().size());
        assertEquals(CompoundTestClassTwo.class, compoundTestClassOne.getCompoundTestClassTwoList().get(0).getClass());;
    }

    @Test
    public void build_GivenListOfCompoundList_ReturnsObjectWithValue() {
        Map<String, Object> classOneMap = new HashMap<>();
        Map<String, Object> classTwoMap = new HashMap<>();

        List<Integer> integerList1 = new ArrayList<>();
        integerList1.add(1);
        integerList1.add(2);

        List<Integer> integerList2 = new ArrayList<>();
        integerList2.add(3);
        integerList2.add(4);

        List<List<Integer>> integerListList = new ArrayList<>();
        integerListList.add(integerList1);
        integerListList.add(integerList2);

        classTwoMap.put("string", "test");
        classTwoMap.put("list", integerList1);
        classTwoMap.put("listList", integerListList);

        List<Object> compoundTestClassTwoList = new ArrayList<>();
        compoundTestClassTwoList.add(classTwoMap);

        List<List<Object>> compoundTestClassTwoListList = new ArrayList<>();
        compoundTestClassTwoListList.add(compoundTestClassTwoList);

        classOneMap.put("compoundTestClassTwoListList", compoundTestClassTwoListList);

        CompoundBuilder compoundBuilder = new CompoundBuilder(CompoundTestClassOne.class);

        CompoundTestClassOne compoundTestClassOne = compoundBuilder.build(classOneMap);

        assertEquals(1, compoundTestClassOne.getCompoundTestClassTwoListList().size());
        assertEquals(CompoundTestClassTwo.class,
                compoundTestClassOne.getCompoundTestClassTwoListList().get(0).get(0).getClass());
    }

    @Test
    public void build_GivenMapWithStringKeyAndCompoundValue_ReturnsObjectWithValue() {
        Map<String, Object> classOneMap = new HashMap<>();
        Map<String, Object> classTwoMap = new HashMap<>();

        List<Integer> integerList1 = new ArrayList<>();
        integerList1.add(1);
        integerList1.add(2);

        List<Integer> integerList2 = new ArrayList<>();
        integerList2.add(3);
        integerList2.add(4);

        List<List<Integer>> integerListList = new ArrayList<>();
        integerListList.add(integerList1);
        integerListList.add(integerList2);

        classTwoMap.put("string", "test");
        classTwoMap.put("list", integerList1);
        classTwoMap.put("listList", integerListList);

        Map<String, Object> map = new HashMap<>();
        map.put("test", classTwoMap);

        classOneMap.put("compoundTestClassTwoMap", map);

        CompoundBuilder compoundBuilder = new CompoundBuilder(CompoundTestClassOne.class);

        CompoundTestClassOne compoundTestClassOne = compoundBuilder.build(classOneMap);

        assertEquals(1, compoundTestClassOne.getCompoundTestClassTwoMap().size());
        assertTrue(compoundTestClassOne.getCompoundTestClassTwoMap().containsKey("test"));
        assertEquals(CompoundTestClassTwo.class,
                compoundTestClassOne.getCompoundTestClassTwoMap().get("test").getClass());
    }

    @Test
    public void build_GivenMapWithCompoundKeyAndValue_ReturnsObjectWithValue() {
        Map<String, Object> classOneMap = new HashMap<>();
        Map<String, Object> classTwoMap1 = new HashMap<>();
        Map<String, Object> classTwoMap2 = new HashMap<>();


        List<Integer> integerList1 = new ArrayList<>();
        integerList1.add(1);
        integerList1.add(2);

        List<Integer> integerList2 = new ArrayList<>();
        integerList2.add(3);
        integerList2.add(4);

        List<List<Integer>> integerListList = new ArrayList<>();
        integerListList.add(integerList1);
        integerListList.add(integerList2);

        classTwoMap1.put("string", "test");
        classTwoMap1.put("list", integerList1);
        classTwoMap1.put("listList", integerListList);

        classTwoMap2.put("string", "test");
        classTwoMap2.put("list", integerList1);
        classTwoMap2.put("listList", integerListList);

        Map<Object, Object> map = new HashMap<>();
        map.put(classTwoMap2, classTwoMap1);

        classOneMap.put("compoundTestClassTwoMap2", map);

        CompoundBuilder compoundBuilder1 = new CompoundBuilder(CompoundTestClassOne.class);

        CompoundTestClassOne compoundTestClassOne = compoundBuilder1.build(classOneMap);

        assertEquals(1, compoundTestClassOne.getCompoundTestClassTwoMap2().size());
    }

    public static class PrivateConstructor {

        private PrivateConstructor() {}
    }

    @Test(expected = EserialInvalidMethodException.class)
    public void build_GivenClassWithPrivateConstructor_ThrowsEserialInvalidMethodException() {
        Map<String, Object> map = new HashMap<>();
        CompoundBuilder compoundBuilder = new CompoundBuilder(PrivateConstructor.class);

        PrivateConstructor privateConstructor = compoundBuilder.build(map);
    }

    public enum Color {
        RED, GREEN, BLUE
    }

    @Test
    public void build_GivenEnumDataMember_ReturnsObjectWithValue() {
        Map<String, Object> map = new HashMap<>();
        map.put("colorDataMember", 2L);

        CompoundBuilder builder = new CompoundBuilder(EnumDataMember.class);

        EnumDataMember edm = builder.build(map);

        assertEquals(2, edm.getColorDataMember().ordinal());
    }
}
