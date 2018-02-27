package hu.elte.eserial.mapper;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class MapperFactoryTest {

    @Test
    public void create_withSerializable() {
        assertTrue(MapperFactory.create(Integer.class) instanceof SerializableMapper);
    }

    @Test
    public void create_withCompound() {
        assertTrue(MapperFactory.create(MapperFactory.class) instanceof CompoundMapper);
    }

    @Test
    public void create_withCollection() {
        assertTrue(MapperFactory.create(List.class) instanceof CollectionMapper);
    }

    @Test
    public void create_withMap() {
        assertTrue(MapperFactory.create(Map.class) instanceof MapMapper);
    }

    @Test
    public void create_withArray() {
        assertTrue(MapperFactory.create(Integer[].class) instanceof ArrayMapper);
    }

    private enum TestEnum {}

    @Test
    public void create_withEnum() {
        assertTrue(MapperFactory.create(TestEnum.class) instanceof EnumMapper);
    }
}