package courses.lesson1.converter;

public class NumConverter {

    private static final int MAX_FRACTIONAL_DIGITS = 6;
    private static final char DECIMAL_SEPARATOR = '.';

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

    public static int stringToInt(String number) {
        // TODO: overflow
        int result = 0;
        boolean isNegative = false;
        if (number.charAt(0) == '-') {
            isNegative = true;
            number = number.substring(1);
        }

        for (int i = 0; i < number.length(); i++)
            result += Character.getNumericValue(number.charAt(i)) * Math.pow(10, number.length() - 1 - i);

        if (isNegative)
            result *= -1;

        return result;
    }

    public static String doubleToString(double number) {
        int integral = (int) number;
        int fractional = (int) Math.round(Math.abs(number - integral) * Math.pow(10, MAX_FRACTIONAL_DIGITS));
        String integralString = intToString(integral);
        String fractionalString = intToString(fractional).replaceAll("0+$", "");
        String result = integralString + (fractionalString.equals("") ? "" : DECIMAL_SEPARATOR) + fractionalString;

        return result;
    }

    public static double stringToDouble(String number) {
        double result = 0;
        int separatorPosition = number.indexOf(DECIMAL_SEPARATOR);
        if (separatorPosition > 0) {
            String integralString = number.substring(0, number.indexOf(DECIMAL_SEPARATOR));
            String fractionalString = number.substring(number.indexOf(DECIMAL_SEPARATOR) + 1);
            int integral = stringToInt(integralString);
            double fractional = Math.signum(integral) *
                    stringToInt(fractionalString) / Math.pow(10, fractionalString.length());
            result = integral + fractional;
        } else {
            result = stringToInt(number);
        }

        return result;
    }

    public static void main(String... args) {

        /*String testNumber = new String("610");
        System.out.println("Initial string      : " + testNumber +
                           "\nResult of converting: " + stringToInt(testNumber));*/

        /*double testNumber = 0;
        System.out.println("Initial double number: " + testNumber +
                           "\nResult of converting : " + doubleToString(testNumber));*/

        String testNumber = new String("0");
        System.out.println("Initial string      : " + testNumber +
                           "\nResult of converting: " + stringToDouble(testNumber));
    }

}
