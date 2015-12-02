package courses.lesson1.converter;

import org.junit.Test;

import static courses.lesson1.converter.NumConverter.*;
import static org.junit.Assert.*;

public class NumConverterTest {
    @Test
    public void testIntToString() {
        assertEquals("0", intToString(0));
        assertEquals("3", intToString(3));
        assertEquals("-5", intToString(-5));
        assertEquals("1345618", intToString(1345618));
        assertEquals("-547332", intToString(-547332));
    }

    @Test (expected = IntOverflowException.class)
    public void testStringToInt() throws IntOverflowException {
        assertEquals(0, stringToInt("0"));
        assertEquals(7, stringToInt("7"));
        assertEquals(-8, stringToInt("-8"));
        assertEquals(712353, stringToInt("712353"));
        assertEquals(-235713, stringToInt("-235713"));
        assertEquals(Integer.MAX_VALUE, stringToInt(String.valueOf(Integer.MAX_VALUE)));
        assertNotEquals(Integer.MAX_VALUE, stringToInt(String.valueOf(Integer.MAX_VALUE + 1)));
        assertNotEquals(Integer.MAX_VALUE, stringToInt(String.valueOf(Integer.MAX_VALUE) + "123"));
    }

    @Test
    public void testDoubleToString() {
        assertEquals("0", doubleToString(0));
        assertEquals("0", doubleToString(0.0));
        assertEquals("123", doubleToString(123));
        assertEquals("-321", doubleToString(-321));
        assertEquals("0.321", doubleToString(0.321));
        assertEquals("-0" + DECIMAL_SEPARATOR + "456", doubleToString(-0.456));
        assertEquals("46352" + DECIMAL_SEPARATOR + "1462", doubleToString(46352.1462));
        assertEquals("-743" + DECIMAL_SEPARATOR + "6862", doubleToString(-743.6862));
        assertEquals("2354" + DECIMAL_SEPARATOR + "121642", doubleToString(2354.121641843146));
    }

    // TODO: separate tests that expect errors
    @Test (expected = IntOverflowException.class)
    public void testStringToDouble() throws IntOverflowException {
        assertEquals(0, stringToDouble("0"), Math.pow(10, -MAX_FRACTIONAL_DIGITS - 1));
        assertEquals(0, stringToDouble("0.0"), Math.pow(10, -MAX_FRACTIONAL_DIGITS - 1));
        assertEquals(5674, stringToDouble("5674"), Math.pow(10, -MAX_FRACTIONAL_DIGITS - 1));
        assertEquals(-9813, stringToDouble("-9813"), Math.pow(10, -MAX_FRACTIONAL_DIGITS - 1));
        assertEquals(0.6124, stringToDouble("0" + DECIMAL_SEPARATOR + "6124"), Math.pow(10, -MAX_FRACTIONAL_DIGITS - 1));
        assertEquals(-0.23547, stringToDouble("-0" + DECIMAL_SEPARATOR + "23547"), Math.pow(10, -MAX_FRACTIONAL_DIGITS - 1));
        assertEquals(-2341234.512311, stringToDouble("-2341234" + DECIMAL_SEPARATOR + "5123112521"), Math.pow(10, -MAX_FRACTIONAL_DIGITS - 1));
        assertEquals(2147483647.12345, stringToDouble("2147483647" + DECIMAL_SEPARATOR + "12345"), Math.pow(10, -MAX_FRACTIONAL_DIGITS - 1));
        assertNotEquals(2147483648.12345, stringToDouble("2147483648" + DECIMAL_SEPARATOR + "12345"));
        assertNotEquals(21474836471.12345, stringToDouble("21474836471" + DECIMAL_SEPARATOR + "12345"));
        assertEquals(0.214748, stringToDouble("0" + DECIMAL_SEPARATOR + "2147483648"), Math.pow(10, -MAX_FRACTIONAL_DIGITS - 1));
    }
}
