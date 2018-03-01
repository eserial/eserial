package hu.elte.eserial.mapper;

import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class MapperFactoryTest {

    @Test
    public void create_GivenSimpleType_ReturnsSimpleMapper() {
        assertTrue(MapperFactory.create(Integer.class) instanceof SimpleMapper);
    }

    @Test
    public void create_GivenCompoundType_ReturnsCompoundMapper() {
        assertTrue(MapperFactory.create(MapperFactory.class) instanceof CompoundMapper);
    }

    @Test
    public void create_GivenCollectionType_ReturnsCollectionMapper() {
        assertTrue(MapperFactory.create(List.class) instanceof CollectionMapper);
    }

    @Test
    public void create_GivenMapType_ReturnsMapMapper() {
        assertTrue(MapperFactory.create(Map.class) instanceof MapMapper);
    }

    @Test
    public void create_GivenArrayType_ReturnsArrayMapper() {
        assertTrue(MapperFactory.create(Integer[].class) instanceof ArrayMapper);
    }

    private enum TestEnum {}

    @Test
    public void create_GivenEnumType_ReturnsEnumMapper() {
        assertTrue(MapperFactory.create(TestEnum.class) instanceof EnumMapper);
    }

    @Test
    public void create_GivenDateType_ReturnsDateMapper() {
        assertTrue(MapperFactory.create(Date.class) instanceof DateMapper);
    }
}