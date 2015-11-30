package courses.lesson1.converter;

public class NumConverter {

    public static final int MAX_FRACTIONAL_DIGITS = 6;
    public static final char DECIMAL_SEPARATOR = '.';

    public static String intToString(int number) {
        String result = new String();
        boolean isNegative = (number < 0);

        do {
            result = Math.abs(number) % 10 + result;
            number = (int) number / 10;
        } while (number != 0);

        if (isNegative)
            result = '-' + result;

        return result;
    }

    public static int stringToInt(String number) throws IntOverflowException {
        int result = 0;
        boolean isNegative = false;
        if (number.charAt(0) == '-') {
            isNegative = true;
            number = number.substring(1);
        }

        for (int i = 0; i < number.length(); i++) {
            int digitInCurrentPosition = Character.getNumericValue(number.charAt(i));
            result += digitInCurrentPosition * Math.pow(10, number.length() - 1 - i);
            if (result == Integer.MAX_VALUE && (i < (number.length() - 1) || digitInCurrentPosition > Integer.MAX_VALUE % 10))
                throw new IntOverflowException("String representation contains number greater than max allowed");
        }

        if (isNegative)
            result *= -1;

        return result;
    }

    public static String doubleToString(double number) {
        int integral = (int) Math.abs((int) number);
        int fractional = (int) Math.round((Math.abs(number) - integral) * Math.pow(10, MAX_FRACTIONAL_DIGITS));
        String integralString = intToString(integral);
        String fractionalString = intToString(fractional).replaceAll("0+$", "");
        String result = (number < 0 ? "-" : "") + integralString +
                (fractionalString.equals("") ? "" : DECIMAL_SEPARATOR) + fractionalString;

        return result;
    }

    public static double stringToDouble(String number) throws IntOverflowException {
        double result = 0;

        int separatorPosition = number.indexOf(DECIMAL_SEPARATOR);
        if (separatorPosition > 0) {
            String integralString = number.substring(0, number.indexOf(DECIMAL_SEPARATOR));
            String fractionalString = number.substring(number.indexOf(DECIMAL_SEPARATOR) + 1);

            if (fractionalString.length() > MAX_FRACTIONAL_DIGITS)
                fractionalString = fractionalString.substring(0, MAX_FRACTIONAL_DIGITS);

            int integral = stringToInt(integralString);
            double fractional = (number.charAt(0) == '-' ? -1 : 1) *
                    stringToInt(fractionalString) / Math.pow(10, fractionalString.length());
            result = integral + fractional;
        } else {
            result = stringToInt(number);
        }

        return result;
    }

    public static void main(String... args) throws Exception {

        /*String testNumber = new String("610");
        System.out.println("Initial string      : " + testNumber +
                           "\nResult of converting: " + stringToInt(testNumber));*/

        /*double testNumber = 0;
        System.out.println("Initial double number: " + testNumber +
                           "\nResult of converting : " + doubleToString(testNumber));*/

        /*String testNumber = new String("0");
        System.out.println("Initial string      : " + testNumber +
                           "\nResult of converting: " + stringToDouble(testNumber));*/

        System.out.println(stringToInt("2147483647"));
        //System.out.println("\"" + Integer.MAX_VALUE + "\"");
    }

}

class IntOverflowException extends Exception {
    public IntOverflowException(String message) {
        super(message);
    }
}