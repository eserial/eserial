package hu.elte.eserial.mapper;

import hu.elte.eserial.exception.EserialMapperMismatchException;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CompoundMapperTest {

    private CompoundMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new CompoundMapper();
    }

    @Test(expected = NullPointerException.class)
    public void map_GivenNull_ThrowsNullPointerException() {
        mapper.map(null);
    }

    @Test(expected = EserialMapperMismatchException.class)
    public void map_GivenInvalidType_ThrowsEserialMapperMismatchException() {
        mapper.map(0);
    }

    public class SimpleGetter {
        public int getId() {
            return 0;
        }
    }

    @Test
    public void map_GivenSimpleGetter_ReturnsListOfTheirValues() {
        Object rootValue = mapper.map(new SimpleGetter());
        assertTrue(rootValue instanceof Map);

        Map<String, Object> root = (Map) rootValue;
        assertEquals(1, root.size());
        assertEquals(0, root.get("id"));
    }

    @Test
    public void map_GivenAnIgnoredMethod_DoesNotReturnItsValue() {
        Object rootValue = mapper.map(new SimpleGetter());
        assertTrue(rootValue instanceof Map);

        Map<String, Object> root = (Map) rootValue;
        assertEquals(null, root.get("class"));
    }

    public class MultipleSimpleGetters {
        public int getId() {
            return 0;
        }

        public String getName() {
            return "Eserial";
        }

        public Boolean isAwesome() {
            return true;
        }
    }

    @Test
    public void map_GivenMultipleSimpleGetters_ReturnsListOfTheirValues() {
        Object rootValue = mapper.map(new MultipleSimpleGetters());
        assertTrue(rootValue instanceof Map);

        Map<String, Object> root = (Map) rootValue;
        assertEquals(3, root.size());
        assertEquals(0, root.get("id"));
        assertEquals("Eserial", root.get("name"));
        assertEquals(true, root.get("awesome"));
    }

    public class CompoundGetter {
        public MultipleSimpleGetters getMultipleGetters() {
            return new MultipleSimpleGetters();
        }
    }

    @Test
    public void map_GivenCompoundGetter_ReturnsListOfTheirValues() {
        Object rootValue = mapper.map(new CompoundGetter());
        assertTrue(rootValue instanceof Map);

        Map<String, Object> root = (Map) rootValue;
        assertEquals(1, root.size());

        Object childrenValue = root.get("multipleGetters");
        assertTrue(childrenValue instanceof Map);

        Map<String, Object> children = (Map) childrenValue;
        assertEquals(3, children.size());
    }

    public class NotAGetter {
        public void getId() {}
    }

    @Test
    public void map_GivenAnInvalidGetter_DoesNotReturnItsValue() {
        Object rootValue = mapper.map(new NotAGetter());
        assertTrue(rootValue instanceof Map);

        Map<String, Object> root = (Map) rootValue;
        assertTrue(root.isEmpty());
    }
}