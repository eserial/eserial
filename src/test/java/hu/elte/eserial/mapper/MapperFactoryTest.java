package hu.elte.eserial.mapper;

import org.junit.Test;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class MapperFactoryTest {

    @Test
    public void create_GivenSimpleType_ReturnsSimpleMapper() {
        assertTrue(MapperFactory.create(0) instanceof SimpleMapper);
    }

    @Test
    public void create_GivenCompoundType_ReturnsCompoundMapper() {
        assertTrue(MapperFactory.create(new MapperFactoryTest()) instanceof CompoundMapper);
    }

    @Test
    public void create_GivenCollectionType_ReturnsCollectionMapper() {
        assertTrue(MapperFactory.create(Collections.emptyList()) instanceof CollectionMapper);
    }

    @Test
    public void create_GivenMapType_ReturnsMapMapper() {
        assertTrue(MapperFactory.create(new HashMap<>()) instanceof MapMapper);
    }

    @Test
    public void create_GivenArrayType_ReturnsArrayMapper() {
        assertTrue(MapperFactory.create(new Integer[0]) instanceof ArrayMapper);
    }

    private enum TestEnum {
        Test
    }

    @Test
    public void create_GivenEnumType_ReturnsEnumMapper() {
        assertTrue(MapperFactory.create(TestEnum.Test) instanceof EnumMapper);
    }

    @Test
    public void create_GivenDateType_ReturnsDateMapper() {
        assertTrue(MapperFactory.create(Calendar.getInstance().getTime()) instanceof DateMapper);
    }
}
