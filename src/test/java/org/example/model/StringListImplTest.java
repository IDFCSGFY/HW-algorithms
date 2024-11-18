package org.example.model;

import org.example.exception.ElementNotFoundException;
import org.example.exception.NullArgumentGivenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {

    private StringListImpl out;

    @BeforeEach
    public void setUp() {
        out = new StringListImpl();
    }

    @Test
    public void shouldReturnAddedItem() {
        assertEquals("Hi", out.add("Hi"));
        assertEquals("Hi1", out.add(1, "Hi1"));
    }

    @Test
    public void shouldReturnCorrectSize() {
        out = new StringListImpl(2);
        assertEquals(0, out.size());

        out.add("Hi");
        assertEquals(1, out.size());

        out.add(0, "Hello");
        assertEquals(2, out.size());
    }

    @Test
    public void shouldGetElementByIndex() {
        out.add("Hi");
        out.add("Hi1");
        assertEquals("Hi", out.get(0));
        assertEquals("Hi1", out.get(1));
    }

    @Test
    public void shouldReturnArray() {
        assertArrayEquals(new String[0], out.toArray());

        out.add("Hi");
        String[] expected = new String[]{"Hi"};

        assertArrayEquals(expected, out.toArray());
    }

    @Test
    public void shouldPositionCorrectlyWhenAddingWithIndex() {
        out.add("Hi");
        out.add(0, "Hi0");
        String[] expected = new String[]{"Hi0", "Hi"};

        assertArrayEquals(expected, out.toArray());

        out.add(2, "Hi2");
        expected = new String[]{"Hi0", "Hi", "Hi2"};

        assertArrayEquals(expected, out.toArray());
    }

    @Test
    public void shouldSetByIndexCorrectly() {
        out.add("Hi0");
        out.set(0, "Im 0");

        assertEquals("Im 0", out.get(0));

        out.add("Hi1");
        out.set(0, "Im not 0");
        String[] expected = {"Im not 0", "Hi1"};

        assertArrayEquals(expected, out.toArray());
    }

    @Test
    public void shouldRemoveByValue() {
        out.add("Hi");
        assertEquals(1, out.size());

        out.remove("Hi");

        assertEquals(0, out.size());
        assertArrayEquals(new String[0], out.toArray());
    }

    @Test
    public void shouldRemoveByIndex() {
        out.add("Zero");
        out.add("One");
        out.add("Two");
        assertEquals(3, out.size());
        String[] expected = new String[]{"Zero", "One", "Two"};
        assertArrayEquals(expected, out.toArray());

        out.remove(1);
        assertEquals(2, out.size());
        expected = new String[]{"Zero", "Two"};
        assertArrayEquals(expected, out.toArray());
    }

    @Test
    public void shouldReturnRemovedValue() {
        out.add("Hi");
        assertEquals("Hi", out.remove("Hi"));

        out.add("Hello");
        assertEquals("Hello", out.remove(0));
    }

    @Test
    public void shouldReturnCorrectBooleanIfContains() {
        out.add("One");
        out.add(1, "Two");

        assertTrue(out.contains("One"));
        assertTrue(out.contains("Two"));
        assertFalse(out.contains("Three"));
    }

    @Test
    public void shouldFindIndexOf() {
        out.add("One");
        out.add("Two");
        out.add("Three");

        assertEquals(1, out.indexOf("Two"));

        out.add("Two");

        assertEquals(1, out.indexOf("Two"));
    }

    @Test
    public void shouldFindLastIndexOf() {
        out.add("One");
        out.add("Two");
        out.add("Three");

        assertEquals(1, out.lastIndexOf("Two"));

        out.add("Two");

        assertEquals(3, out.lastIndexOf("Two"));
    }

    @Test
    public void shouldReturnNegativeOneWhenElementNotFoundInIndexOf() {
        assertEquals(-1, out.indexOf("One"));

        out.add("one");
        assertEquals(-1, out.indexOf("not one"));
        assertEquals(-1, out.indexOf("One"));
        assertEquals(-1, out.indexOf("ONE"));

        assertEquals(0, out.indexOf("one"));
        out.remove("one");
        assertEquals(-1, out.indexOf("one"));
    }

    @Test
    public void shouldReturnNegativeOneWhenElementNotFoundInLastIndexOf() {
        assertEquals(-1, out.lastIndexOf("One"));

        out.add("one");
        assertEquals(-1, out.lastIndexOf("not one"));
        assertEquals(-1, out.lastIndexOf("One"));
        assertEquals(-1, out.lastIndexOf("ONE"));

        assertEquals(0, out.lastIndexOf("one"));
        out.remove("one");
        assertEquals(-1, out.lastIndexOf("one"));
    }

    @Test
    public void shouldCorrectlyJudgeIfEqual() {
        out.add("Hi");
        out.add("Hello");
        StringListImpl targetStrList = new StringListImpl(2);
        targetStrList.add("Hi");
        targetStrList.add("Hello");

        assertTrue(out.equals(targetStrList));

        targetStrList = new StringListImpl(3);
        targetStrList.add("Hi");
        targetStrList.add("Hello");
        assertTrue(out.equals(targetStrList));

        targetStrList.set(0, "Hey");
        assertFalse(out.equals(targetStrList));
    }

    @Test
    public void shouldThrowIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> out.add(1, "A"));
        assertThrows(IndexOutOfBoundsException.class, () -> out.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> out.set(1, "A"));
        assertThrows(IndexOutOfBoundsException.class, () -> out.remove(1));
    }

    @Test
    public void shouldThrowElementNotFoundException() {
        out.add("Bye");
        assertThrows(ElementNotFoundException.class, () -> out.remove("Hi"));
    }

    @Test
    public void shouldThrowNullArgumentGivenException() {
        assertThrows(NullArgumentGivenException.class, () -> out.add(null));
        assertThrows(NullArgumentGivenException.class, () -> out.add(0, null));
        assertThrows(NullArgumentGivenException.class, () -> out.set(0, null));
        assertThrows(NullArgumentGivenException.class, () -> out.remove(null));
        assertThrows(NullArgumentGivenException.class, () -> out.contains(null));
        assertThrows(NullArgumentGivenException.class, () -> out.indexOf(null));
        assertThrows(NullArgumentGivenException.class, () -> out.lastIndexOf(null));
        assertThrows(NullArgumentGivenException.class, () -> out.equals(null));
    }

    @Test
    public void shouldCorrectlyJudgeIfEmpty() {
        assertTrue(out.isEmpty());
        out.add("sup");
        assertFalse(out.isEmpty());
        out.remove("sup");
        assertTrue(out.isEmpty());
    }

    @Test
    public void shouldClear() {
        out.add("Hi");
        assertTrue(out.contains("Hi"));

        out.clear();
        assertFalse(out.contains("Hi"));
        assertTrue(out.isEmpty());
    }
}