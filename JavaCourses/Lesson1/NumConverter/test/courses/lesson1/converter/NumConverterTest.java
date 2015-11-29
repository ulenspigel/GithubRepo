package courses.lesson1.converter;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumConverterTest {
    @Test
    public void testIntToString() {
        // TODO: test overflow
        assertEquals("0", NumConverter.intToString(0));
        assertEquals("3", NumConverter.intToString(3));
        assertEquals("-5", NumConverter.intToString(-5));
        assertEquals("1345618", NumConverter.intToString(1345618));
        assertEquals("-547332", NumConverter.intToString(-547332));
    }

    @Test
    public void testStringToInt() {
        // TODO: test overflow
        assertEquals(0, NumConverter.stringToInt("0"));
        assertEquals(7, NumConverter.stringToInt("7"));
        assertEquals(-8, NumConverter.stringToInt("-8"));
        assertEquals(712353, NumConverter.stringToInt("712353"));
        assertEquals(-235713, NumConverter.stringToInt("-235713"));
    }
}
