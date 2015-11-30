package courses.lesson1.converter;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumConverterTest {
    @Test
    public void testIntToString() {
        assertEquals("0", NumConverter.intToString(0));
        assertEquals("3", NumConverter.intToString(3));
        assertEquals("-5", NumConverter.intToString(-5));
        assertEquals("1345618", NumConverter.intToString(1345618));
        assertEquals("-547332", NumConverter.intToString(-547332));
    }

    @Test (expected = IntOverflowException.class)
    public void testStringToInt() throws IntOverflowException {
        assertEquals(0, NumConverter.stringToInt("0"));
        assertEquals(7, NumConverter.stringToInt("7"));
        assertEquals(-8, NumConverter.stringToInt("-8"));
        assertEquals(712353, NumConverter.stringToInt("712353"));
        assertEquals(-235713, NumConverter.stringToInt("-235713"));
        assertEquals(Integer.MAX_VALUE, NumConverter.stringToInt(String.valueOf(Integer.MAX_VALUE)));
        assertNotEquals(Integer.MAX_VALUE, NumConverter.stringToInt(String.valueOf(Integer.MAX_VALUE + 1)));
        assertNotEquals(Integer.MAX_VALUE, NumConverter.stringToInt(String.valueOf(Integer.MAX_VALUE) + "123"));
    }

    @Test
    public void testDoubleToString() {
        assertEquals("0", NumConverter.doubleToString(0));
        assertEquals("0", NumConverter.doubleToString(0.0));
        assertEquals("123", NumConverter.doubleToString(123));
        assertEquals("-321", NumConverter.doubleToString(-321));
        assertEquals("0" + NumConverter.DECIMAL_SEPARATOR + "321", NumConverter.doubleToString(0.321));
        assertEquals("-0" + NumConverter.DECIMAL_SEPARATOR + "456", NumConverter.doubleToString(-0.456));
        assertEquals("46352" + NumConverter.DECIMAL_SEPARATOR + "1462", NumConverter.doubleToString(46352.1462));
        assertEquals("-743" + NumConverter.DECIMAL_SEPARATOR + "6862", NumConverter.doubleToString(-743.6862));
        assertEquals("2354" + NumConverter.DECIMAL_SEPARATOR + "121642", NumConverter.doubleToString(2354.121641843146));
    }

    @Test (expected = IntOverflowException.class)
    public void testStringToDouble() throws IntOverflowException {
        assertEquals(0, NumConverter.stringToDouble("0"), Math.pow(10, -NumConverter.MAX_FRACTIONAL_DIGITS - 1));
        assertEquals(0, NumConverter.stringToDouble("0.0"), Math.pow(10, -NumConverter.MAX_FRACTIONAL_DIGITS - 1));
        assertEquals(5674, NumConverter.stringToDouble("5674"), Math.pow(10, -NumConverter.MAX_FRACTIONAL_DIGITS - 1));
        assertEquals(-9813, NumConverter.stringToDouble("-9813"), Math.pow(10, -NumConverter.MAX_FRACTIONAL_DIGITS - 1));
        assertEquals(0.6124, NumConverter.stringToDouble("0" + NumConverter.DECIMAL_SEPARATOR + "6124"), Math.pow(10, -NumConverter.MAX_FRACTIONAL_DIGITS - 1));
        assertEquals(-0.23547, NumConverter.stringToDouble("-0" + NumConverter.DECIMAL_SEPARATOR + "23547"), Math.pow(10, -NumConverter.MAX_FRACTIONAL_DIGITS - 1));
        assertEquals(-2341234.512311, NumConverter.stringToDouble("-2341234" + NumConverter.DECIMAL_SEPARATOR + "5123112521"), Math.pow(10, -NumConverter.MAX_FRACTIONAL_DIGITS - 1));
        assertEquals(2147483647.12345, NumConverter.stringToDouble("2147483647" + NumConverter.DECIMAL_SEPARATOR + "12345"), Math.pow(10, -NumConverter.MAX_FRACTIONAL_DIGITS - 1));
        assertNotEquals(2147483648.12345, NumConverter.stringToDouble("2147483648" + NumConverter.DECIMAL_SEPARATOR + "12345"));
        assertNotEquals(21474836471.12345, NumConverter.stringToDouble("21474836471" + NumConverter.DECIMAL_SEPARATOR + "12345"));
        assertEquals(0.214748, NumConverter.stringToDouble("0" + NumConverter.DECIMAL_SEPARATOR + "2147483648"), Math.pow(10, -NumConverter.MAX_FRACTIONAL_DIGITS - 1));
    }
}
