package ua.dkovalov.datastructures.list;

import org.junit.*;
import static org.junit.Assert.*;

public abstract class AbstractListTest {

    @Test
    public void testAdd() {
        List list = instantiateList();
        list.add("first");
        assertEquals(list.size(), 1);
        list.add("second");
        list.add("third");
        assertEquals(list.size(), 3);
        assertEquals(list.get(0), "first");
        assertEquals(list.get(2), "third");
    }

    @Test
    public void testAddToIndex() {
        List list = instantiateList("first", "third", "fourth");
        list.add(1, "second");
        assertEquals(list.size(), 4);
        assertEquals(list.get(1), "second");
        list.add(0, "zero");
        list.add(list.size(), "fifth");
        assertEquals(list.get(0), "zero");
        assertEquals(list.get(list.size() - 1), "fifth");
        assertEquals(list.size(), 6);
    }

    @Test(expected = RuntimeException.class)
    public void testAddLowerBound() {
        List list = instantiateList("string");
        list.add(-1, "another string");
    }

    @Test(expected = RuntimeException.class)
    public void testAddUpperBound() {
        List list = instantiateList("string");
        list.add(list.size() + 1, "another string");
    }

    @Test(expected = RuntimeException.class)
    public void testGetLowerBound() {
        List list = instantiateList(1, 2, 3);
        list.get(-1);
    }

    @Test(expected = RuntimeException.class)
    public void testGetUpperBound() {
        List list = instantiateList(1, 2, 3);
        list.get(list.size());
    }

    @Test
    public void testSet() {
        List list = instantiateList('a', 'b', 'c');
        list.set(1, 'd');
        assertEquals(list.get(1), 'd');
    }

    @Test
    public void testClear() {
        List list = instantiateList('a', 'b', 'c', 'd', 'e');
        assertTrue(list.size() > 0);
        list.clear();
        assertFalse(list.size() > 0);
    }

    @Test
    public void testRemove() {
        List list = instantiateList(1, 2, 3, 4, 5);
        list.remove(2);
        list.remove(0);
        list.remove(list.size() - 1);
        assertEquals(list.size(), 2);
        assertEquals(list.get(0), 2);
        assertEquals(list.get(1), 4);
    }

    @Test
    public void testIsEmpty() {
        List list = instantiateList(101, 102, 103, 104, 105);
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testIndexOf() {
        List list = instantiateList(10, 20, 30, 20, 10);
        assertEquals(list.indexOf(10), 0);
        assertEquals(list.indexOf(0), -1);
    }

    @Test
    public void testLastIndexOf() {
        List list = instantiateList(10, 20, 30, 20, 10);
        assertEquals(list.lastIndexOf(20), 3);
        assertEquals(list.lastIndexOf(40), -1);
    }

    @Test
    public void testContains() {
        List list = instantiateList('a', 'b', 'c', 'd', 'e');
        assertTrue(list.contains('c'));
        assertFalse(list.contains('f'));
    }

    protected abstract List instantiateList();
    protected abstract List instantiateList(Object... objects);

}
