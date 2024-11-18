package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
        String[] expected = new String[1];
        expected[0] = "Hi";

        assertArrayEquals(expected, out.toArray());
    }

    @Test
    public void shouldPositionCorrectlyWhenAddingWithIndex() {
        out.add("Hi");
        out.add(0, "Hi0");
        String[] expected = new String[2];
        expected[0] = "Hi0";
        expected[1] = "Hi";

        assertArrayEquals(expected, out.toArray());

        out.add(2, "Hi2");
        expected = new String[3];
        expected[0] = "Hi0";
        expected[1] = "Hi";
        expected[2] = "Hi2";

        assertArrayEquals(expected, out.toArray());
    }

    @Test
    public void shouldSetByIndexCorrectly() {
        out.add("Hi0");
        out.set(0, "Im 0");

        assertEquals("Im 0", out.get(0));

        out.add("Hi1");
        out.set(0, "Im not 0");
        String[] expected = new String[2];
        expected[0] = "Im not 0";
        expected[1] = "Hi1";

        assertArrayEquals(expected, out.toArray());
    }
}