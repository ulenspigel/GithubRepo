import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;

/**
 * Created by dgkovalev on 12/01/2015.
 */
public class SandboxMethods {

    public static void printCharArray(char[] charArray) {
        for (char currentChar: charArray)
            System.out.print(currentChar);
    }

    public static void print2DStringArray(String[][] string2DArray) {
        for (String[] stringArray : string2DArray) {
            for (String string : stringArray)
                System.out.print(string + " ");
            System.out.println();
        }
    }

    public static char[][] intArrayToCharArray(int[][] intArray) {
        char[][] charArray = new char[intArray.length][intArray[0].length];

        for (int i = 0; i < intArray.length; i++)
            for (int j = 0; j < intArray[i].length; j++)
                charArray[i][j] = (char) intArray[i][j];

        return charArray;
    }

    public static void intArrayInversion(int[][] int2DArray) {
        for (int i = 0; i < int2DArray.length; i++)
            for (int j = 0; j < int2DArray[i].length; j++)
                int2DArray[i][j] *= -1;
    }

    public static int findGreatest(int a, int b) {
        int result = a;
        if (result < b)
            result = b;
        return result;
    }

    public static int findGreatest(int a, int b, int c) {
        return findGreatest(findGreatest(a, b), c);
    }

    public static int findGreatest(int a, int b, int c, int d, int e) {
        return findGreatest(findGreatest(findGreatest(a, b), findGreatest(c, d)), e);
    }

    public static String charArrayToString(char[] charArray) {
        String string = new String();
        for (char currentChar: charArray)
            string += currentChar;
        return string;
    }

    public static boolean isSubstring(char[] whole, char[] part) {
        boolean result = true;
        for (int i = 0; i < whole.length; i++) {
            result = true;
            for (int j = 0; j < part.length; j++)
                if (whole[i + j] != part[j]) {
                    result = false;
                    break;
                }
            if (result)
                break;
        }

        return result;
    }

    public static int findIntPosition(int[] intArray, int intValue) {
        int index = -1;

        for (int i = 0; i < intArray.length; i++)
            if (intArray[i] == intValue) {
                index = i;
                break;
            }

        return index;
    }

    public static int findIntPositionReverse(int[] intArray, int intValue) {
        int index = -1;
        for (int i = intArray.length - 1; i >= 0; i--)
            if (intArray[i] == intValue) {
                index = i;
                break;
            }
        return index;
    }

    public static int factorial(int value) {
        int result = 1;
        if (value > 1)
            result = value * factorial(value - 1);
        return result;
    }

    public static boolean isLeapYear(int year) {
        boolean result = (year % 4 == 0);
        return result;
    }

    public static String[] filterBySubstring(String[] stringArray, String pattern) {
        String[] filteredStrings = new String[stringArray.length];
        int stringsAdded = 0;

        for (String currentString: stringArray)
            if (currentString.contains(pattern)) {
                filteredStrings[stringsAdded++] = currentString;
            }
        filteredStrings = Arrays.copyOfRange(filteredStrings, 0, stringsAdded);

        return filteredStrings;
    }

    public static void printDivisibleElements(int[] intArray, int divider) {
        for (int currentInt: intArray)
            if (currentInt % divider == 0)
                System.out.print(currentInt + " ");
    }

    public static void printRoundedDouble(double number) {
        System.out.print(Math.round(number * 1000) / 1000d);
    }

    private static void quickSort(int[] array, int leftBound, int rightBound, boolean isAscending) {
        int i = leftBound;
        int j = rightBound;
        int pivotIndex = (leftBound + (rightBound - leftBound) / 2);
        int pivot = array[pivotIndex];
        while (i <= j) {
            if (isAscending) {
                while (array[i] < pivot && i < rightBound)
                    i++;
                while (array[j] > pivot && j > leftBound)
                    j--;
            } else {
                while (array[i] > pivot && i < rightBound)
                    i++;
                while (array[j] < pivot && j > leftBound)
                    j--;
            }
            if (i <= j) {
                int tmp = array[i];
                array[i++] = array[j];
                array[j--] = tmp;
                //i++;
                //j--;
            }
        }

        if (leftBound < j)
            quickSort(array, leftBound, j, isAscending);

        if (rightBound > i)
            quickSort(array, i, rightBound, isAscending);
    }

    public static void sortArrayAscending(int[] array) {
        quickSort(array, 0, array.length - 1, true);
    }

    public static void sortArrayDescending(int[] array) {
        quickSort(array, 0, array.length - 1, false);
    }

    public static void sortArrayOptionalOrder(int[] array, boolean isAscending) {
        quickSort(array, 0, array.length - 1, isAscending);
    }

    public static boolean containsDuplicates(byte[] array) {
        boolean result = false;
        int[] sortedArray = new int[array.length];
        for (int i = 0; i < array.length; i++)
            sortedArray[i] = (int) array[i];
        quickSort(sortedArray, 0, sortedArray.length - 1, true);
        for (int i = 0; i < sortedArray.length - 1; i++)
            if (sortedArray[i] == sortedArray[i + 1]) {
                result = true;
                break;
            }

        return result;
    }

    public static void main(String... args) {
        System.out.println("1. printCharArray testing: ");
        char[] charArray = {'e','f','g','h','i','j','k'};
        printCharArray(charArray);

        System.out.println("\n\n2. print2DStringArray testing: ");
        String[][] string2DArray = {
                {"abc", "def", "ghi"},
                {"jkl", "mno", "pqr"},
                {"stu", "vwx", "yz"}
        };
        print2DStringArray(string2DArray);

        System.out.println("\n\n3. intArrayToCharArray testing: ");
        int[][] intArray = {{65, 67, 69, 71}, {66, 68, 70, 72}};
        char[][] charArrayConverted = intArrayToCharArray(intArray);
        for (char[] charArrayRow : charArrayConverted) {
            printCharArray(charArrayRow);
            System.out.println();
        }

        System.out.println("\n\n4. intArrayInversion testing: ");
        int[][] intArrayToInvert = {
                {2, -6, 6, 4},
                {7, 3, -1, 5},
                {23, -73, 11, -43}
        };
        intArrayInversion(intArrayToInvert);
        for (int[] invertedRow: intArrayToInvert) {
            for (int invertedInt : invertedRow)
                System.out.print(invertedInt + " ");
            System.out.println();
        }

        System.out.print("\n\n5. findGreatest(a, b) testing: ");
        System.out.print(findGreatest(-5, 1));

        System.out.print("\n\n6. findGreatest(a, b, c) testing: ");
        System.out.print(findGreatest(0, -6, 3));

        System.out.print("\n\n7. findGreatest(a, b, c, d, e) testing: ");
        System.out.print(findGreatest(15, -16, 3, 9, 12));

        System.out.print("\n\n8. charArrayToString testing: ");
        char[] charArrayRepresentation = {'z', 'y', 'x', 'w', 'v', 'u'};
        System.out.print(charArrayToString(charArrayRepresentation));

        System.out.print("\n\n9. isSubstring testing: ");
        char[] charArrayWhole = {'a', 'b', 'c', 'c', 'd', 'e', 'f', 'g'};
        char[] charArrayPart = {'c', 'd', 'e'};
        System.out.print(isSubstring(charArrayWhole, charArrayPart));

        System.out.print("\n\n10. findIntPosition testing: ");
        int[] intArrayToLookup = {1, 2, 6, 2, 7, 12, 2, 9};
        System.out.print(findIntPosition(intArrayToLookup, 2));

        System.out.print("\n\n11. findIntPositionReverse testing: ");
        System.out.print(findIntPositionReverse(intArrayToLookup, 2));

        System.out.print("\n\n12. factorial testing: ");
        System.out.print(factorial(6));

        System.out.print("\n\n13. isLeapYear testing: ");
        System.out.print(isLeapYear(2014));

        System.out.println("\n\n14. filterBySubstring testing: ");
        String[] stringArray = {"abcdef", "cdefghi", "fsddbdrjj", "wehdfnc", "gsssegb"};
        String[] filteredArray = filterBySubstring(stringArray, "def");
        for (String string: filteredArray) {
            System.out.println(string);
        }

        System.out.println("\n\n15. printDivisibleElements testing: ");
        int[] arrayForDivisibilityCheck = {346, 111, 632, 9, 81};
        printDivisibleElements(arrayForDivisibilityCheck, 3);

        System.out.print("\n\n16. printRoundedDouble testing: ");
        printRoundedDouble(0.6438124);

        System.out.println("\n\n17. sortArrayAscending testing: ");
        int[] arrayToSortAsc = {2, 4, 1, 3};
        sortArrayAscending(arrayToSortAsc);
        for (int arrayElement: arrayToSortAsc)
            System.out.print(arrayElement + " ");

        System.out.println("\n\n18. sortArrayDescending testing: ");
        int[] arrayToSortDesc = {4, 2, 7, 1, 2, 10};
        sortArrayDescending(arrayToSortDesc);
        for (int arrayElement: arrayToSortDesc)
            System.out.print(arrayElement + " ");

        System.out.print("\n\n20. containsDuplicates testing: ");
        byte[] arrayToCheckDuplicates = {4, 2, 7, 1, 10};
        System.out.println(containsDuplicates(arrayToCheckDuplicates));
    }
}
