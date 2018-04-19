package hu.elte.eserial.builder;

import hu.elte.eserial.model.EserialElement;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class BuilderFactoryTest {

    @Test
    public void create_GivenPrimitiveType_ReturnsSimpleBuilder() {
        assertTrue(BuilderFactory.create(int.class) instanceof SimpleBuilder);
    }

    @Test
    public void create_GivenWrapperType_ReturnsSimpleBuilder() {
        assertTrue(BuilderFactory.create(Integer.class) instanceof SimpleBuilder);
    }

    @Test
    public void create_GivenCollectionType_ReturnsCollectionBuilder() {
        assertTrue(BuilderFactory.create(ArrayList.class) instanceof CollectionBuilder);
    }

    @Test
    public void create_GivenMapType_ReturnsMapBuilder() {
        assertTrue(BuilderFactory.create(HashMap.class) instanceof MapBuilder);
    }

    enum TestEnum {}

    @Test
    public void create_GivenEnumType_ReturnsEnumBuilder() {
        assertTrue(BuilderFactory.create(TestEnum.class) instanceof EnumBuilder);
    }

    @Test
    public void create_GivenDateType_ReturnsDateBuilder() {
        assertTrue(BuilderFactory.create(Date.class) instanceof DateBuilder);
    }

    @Test
    public void create_GivenCompoundType_ReturnsCompoundBuilder() {
        assertTrue(BuilderFactory.create(EserialElement.class) instanceof CompoundBuilder);
    }
}
