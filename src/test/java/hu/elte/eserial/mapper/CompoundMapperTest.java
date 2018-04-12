package hu.elte.eserial.mapper;

import hu.elte.eserial.annotation.UseToString;
import hu.elte.eserial.exception.EserialMapperMismatchException;
import hu.elte.eserial.model.EserialContext;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CompoundMapperTest {

    @Test(expected = NullPointerException.class)
    public void map_GivenNull_ThrowsNullPointerException() {
        new CompoundMapper(null).map(null);
    }

    @Test(expected = EserialMapperMismatchException.class)
    public void map_GivenInvalidType_ThrowsEserialMapperMismatchException() {
        new CompoundMapper(0).map(null);
    }

    public class SimpleGetter {
        public int getId() {
            return 0;
        }
    }

    @Test
    public void map_GivenSimpleGetter_ReturnsListOfTheirValues() {
        SimpleGetter simpleGetter = new SimpleGetter();
        Object rootValue = new CompoundMapper(simpleGetter).map(EserialContext.forRoot(simpleGetter));
        assertTrue(rootValue instanceof Map);

        Map<String, Object> root = (Map) rootValue;
        assertEquals(1, root.size());
        assertEquals(0, root.get("id"));
    }

    @Test
    public void map_GivenAnIgnoredMethod_DoesNotReturnItsValue() {
        SimpleGetter simpleGetter = new SimpleGetter();
        Object rootValue = new CompoundMapper(simpleGetter).map(EserialContext.forRoot(simpleGetter));
        assertTrue(rootValue instanceof Map);

        Map<String, Object> root = (Map) rootValue;
        assertNull(root.get("class"));
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
        MultipleSimpleGetters multipleSimpleGetters = new MultipleSimpleGetters();
        Object rootValue = new CompoundMapper(multipleSimpleGetters).map(EserialContext.forRoot(multipleSimpleGetters));
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
        CompoundGetter compoundGetter = new CompoundGetter();
        Object rootValue = new CompoundMapper(compoundGetter).map(EserialContext.forRoot(compoundGetter));
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
        NotAGetter notAGetter = new NotAGetter();
        Object rootValue = new CompoundMapper(notAGetter).map(EserialContext.forRoot(notAGetter));
        assertTrue(rootValue instanceof Map);

        Map<String, Object> root = (Map) rootValue;
        assertTrue(root.isEmpty());
    }

    public class UserWithToken {
        public TokenWithUser token;
        public TokenWithUser getToken() { return token; }
    }

    public class TokenWithUser {
        public UserWithToken user;
        public UserWithToken getUser() { return user; }
    }

    @UseToString
    public class WithToString {
        public Integer getId() { return 2; }

        @Override
        public String toString() {
            return "toString representation";
        }
    }

    @Test
    public void map_GivenClassWithUseToString_ReturnsTheToStringRepresentation() {
        WithToString withToString = new WithToString();
        Object rootValue = new CompoundMapper(withToString).map(EserialContext.forRoot(withToString));
        assertTrue(rootValue instanceof String);
        assertEquals("toString representation", rootValue);
    }


    @Test
    public void map_GivenDirectlyRecursiveObjects_StopsRecursion() {
        UserWithToken user = new UserWithToken();
        TokenWithUser token = new TokenWithUser();
        user.token = token;
        token.user = user;

        Object rootValue = new CompoundMapper(user).map(EserialContext.forRoot(user));
        assertTrue(rootValue instanceof Map);

        Map<String, Object> root = (Map) rootValue;
        Map<String, Object> tokenRoot = (Map) root.get("token");

        assertNull(tokenRoot.get("user"));
    }

    public class LinkedNode {
        public LinkedNode next;
        public LinkedNode getNext() { return next; }
    }

    @Test
    public void map_GivenCircularRecursiveObject_StopsRecursion() {
        LinkedNode first = new LinkedNode();
        LinkedNode second = new LinkedNode();
        LinkedNode last = new LinkedNode();
        first.next = second;
        second.next = last;
        last.next = first;

        Object rootValue = new CompoundMapper(first).map(EserialContext.forRoot(first));
        assertTrue(rootValue instanceof Map);

        Map<String, Object> firstRoot = (Map) rootValue;
        Map<String, Object> secondRoot = (Map) firstRoot.get("next");
        Map<String, Object> lastRoot = (Map) secondRoot.get("next");

        assertNull(lastRoot.get("next"));
    }

    public class Node {
        public String nodeName;
        public List<Edge> edges1 = new ArrayList<>();
        public List<Edge> edges2 = new ArrayList<>();

        public String getNodeName() { return nodeName; }
        public List<Edge> getEdges1() { return edges1; }
        public List<Edge> getEdges2() { return edges2; }
    }

    public class Edge {
        public String edgeName;
        public Node node1;
        public Node node2;

        public String getEdgeName() { return edgeName; }
        public Node getNode1() { return node1; }
        public Node getNode2() { return node2; }
    }

    @Test
    public void map_GivenRecursionInCollectionWithRootInCollection_StopsRecursion() {
        Edge edge12 = new Edge();
        edge12.edgeName = "12";
        Node node1 = new Node();
        node1.nodeName = "1";
        Node node2 = new Node();
        node2.nodeName = "2";

        edge12.node1 = node1;
        edge12.node2 = node2;
        node1.edges1.add(edge12);
        node2.edges2.add(edge12);

        //Would throw a StackOverflowError on infinite recursion.
        new CompoundMapper(edge12).map(EserialContext.forRoot(edge12));
    }

    @Test
    public void map_GivenRecursionInCollectionWithRootOutsideCollection_StopsRecursion() {
        Edge edge12 = new Edge();
        edge12.edgeName = "12";
        Node node1 = new Node();
        node1.nodeName = "1";
        Node node2 = new Node();
        node2.nodeName = "2";

        edge12.node1 = node1;
        edge12.node2 = node2;
        node1.edges1.add(edge12);
        node2.edges2.add(edge12);

        //Would throw a StackOverflowError on infinite recursion.
        new CompoundMapper(node1).map(EserialContext.forRoot(node1));
    }
}
