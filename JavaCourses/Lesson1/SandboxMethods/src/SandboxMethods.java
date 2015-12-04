import com.sun.deploy.util.ArrayUtil;

import java.io.StringWriter;
import java.util.Arrays;

/**
 * Created by dgkovalev on 12/01/2015.
 */
public class SandboxMethods {

    // 1 - naming convention
    public static void print(char[] array) {
        for (char currentChar: array) {
            System.out.print(currentChar);
        }
    }

    // 2 - naming convention
    public static void print(String[][] array) {
        for (String[] arrayRow : array) {
            for (String string : arrayRow) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }

    // 3 - naming convention + handling array with different lengths of rows
    public static char[][] convert(int[][] array) {
        char[][] charArray = new char[array.length][];

        for (int i = 0; i < array.length; i++) {
            char[] arrayRow = new char[array[i].length];
            for (int j = 0; j < array[i].length; j++) {
                arrayRow[j] = (char) array[i][j];
            }
            charArray[i] = arrayRow;
        }

        return charArray;
    }

    // 4 - naming convention + braces in loops
    public static void invert(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] *= -1;
            }
        }
    }

    // 5 - rename + refactor
    public static int max(int a, int b) {
        return (a < b ? b : a);
    }

    // 6 - rename
    public static int max(int a, int b, int c) {
        return max(max(a, b), c);
    }

    // 7 - rename + refactor
    public static int max(int a, int b, int c, int d, int e) {
        return max(max(a, b, c), max(d, e));
    }

    // 8 - rewritten
    public static String toString(char[] array) {
        StringBuilder stringBuilder = new StringBuilder();

        for (char currentChar: array) {
            stringBuilder.append(currentChar);
        }

        return stringBuilder.toString();
    }

    // 9 - renamed
    public static boolean contains(char[] whole, char[] part) {
        boolean result = true;
        for (int i = 0; i < whole.length; i++) {
            result = true;
            for (int j = 0; j < part.length; j++) {
                if (whole[i + j] != part[j]) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    // 10 - naming
    public static int indexOf(int[] array, int value) {
        for (int i = 0; i < array.length; i++)
            if (array[i] == value) {
                return i;
            }

        return -1;
    }

    // 11 - naming
    public static int lastIndexOf(int[] array, int value) {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    // 12
    public static int factorial(int value) {
        int result = 1;
        if (value > 1) {
            result = value * factorial(value - 1);
        }
        return result;
    }

    // 13 - change algo
    public static boolean isLeapYear(int year) {
        return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
    }

    // 14
    public static String[] filterByPattern(String[] array, String pattern) {
        String[] filteredStrings = new String[array.length];
        int addedStringsCount = 0;

        for (String arrayItem: array) {
            if (arrayItem.contains(pattern)) {
                filteredStrings[addedStringsCount++] = arrayItem;
            }
        }
        filteredStrings = Arrays.copyOfRange(filteredStrings, 0, addedStringsCount);

        return filteredStrings;
    }

    // 15 - naming
    public static void printDivisible(int[] array, int divider) {
        for (int arrayItem: array) {
            if (arrayItem % divider == 0) {
                System.out.print(arrayItem + " ");
            }
        }
    }

    // 16
    public static void printRounded(double number) {
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

    // 17
    public static void sortAscending(int[] array) {
        quickSort(array, 0, array.length - 1, true);
    }

    // 18
    public static void sortDescending(int[] array) {
        quickSort(array, 0, array.length - 1, false);
    }

    // 19
    public static void sortOptionalOrder(int[] array, boolean isAscending) {
        quickSort(array, 0, array.length - 1, isAscending);
    }

    // 20 - rewritten
    public static boolean containsDuplicates(byte[] array) {
        boolean[] valuePresence = new boolean[128];

        for (byte arrayItem : array) {
            if (valuePresence[arrayItem]) {
                return true;
            } else {
                valuePresence[arrayItem] = true;
            }
        }

        return false;
    }

    public static void main(String... args) {

        /*
        System.out.println("1. print(char[]) testing: ");
        char[] charArray = {'e','f','g','h','i','j','k'};
        print(charArray);

        System.out.println("\n\n2. print(String[][]) testing: ");
        String[][] string2DArray = {
                {"abc", "def", "ghi"},
                {"jkl", "pqr"},
                {"stu", "vwx", "yz"}
        };
        print(string2DArray);

        System.out.println("\n\n3. convert(int[][]) testing: ");
        int[][] intArray = {{65, 67, 71}, {66, 68, 70, 72}, {74, 75}};
        char[][] charArrayConverted = convert(intArray);
        for (char[] charArrayRow : charArrayConverted) {
            print(charArrayRow);
            System.out.println();
        }

        System.out.println("\n\n4. invert() testing: ");
        int[][] intArrayToInvert = {
                {2, -6, 6, 4},
                {7, 3, -1, 5, 17, -2},
                {23, -73, -43}
        };
        invert(intArrayToInvert);
        for (int[] invertedRow: intArrayToInvert) {
            for (int invertedInt : invertedRow)
                System.out.print(invertedInt + " ");
            System.out.println();
        }

        System.out.print("\n\n5. max(a, b) testing: ");
        System.out.print(max(-5, 1));

        System.out.print("\n\n6. max(a, b, c) testing: ");
        System.out.print(max(0, -6, -3));

        System.out.print("\n\n7. max(a, b, c, d, e) testing: ");
        System.out.print(max(15, -16, 3, 9, 12));

        System.out.print("\n\n8. toString(char[]) testing: ");
        char[] charArrayRepresentation = {'z', 'y', 'x', 'w', 'v', 'u'};
        System.out.print(toString(charArrayRepresentation));

        System.out.print("\n\n9. contains() testing: ");
        char[] charArrayWhole = {'a', 'b', 'c', 'c', 'd', 'd', 'e', 'f', 'g'};
        char[] charArrayPart = {'c', 'd', 'e'};
        System.out.print(contains(charArrayWhole, charArrayPart));
        */

        System.out.print("\n\n10. indexOf() testing: ");
        int[] intArrayToLookup = {1, 2, 6, 2, 7, 12, 2, 9};
        System.out.print(indexOf(intArrayToLookup, 2));

        System.out.print("\n\n11. lastIndexOf() testing: ");
        System.out.print(lastIndexOf(intArrayToLookup, 2));

        System.out.print("\n\n12. factorial() testing: ");
        System.out.print(factorial(6));

        System.out.print("\n\n13. isLeapYear() testing: ");
        System.out.print(isLeapYear(2100));

        System.out.println("\n\n14. filterByPattern() testing: ");
        String[] stringArray = {"abcdef", "cdefghi", "fsddbdrjj", "wehdfnc", "gsssegb"};
        String[] filteredArray = filterByPattern(stringArray, "def");
        for (String string: filteredArray) {
            System.out.println(string);
        }

        System.out.println("\n\n15. printDivisible() testing: ");
        int[] arrayForDivisibilityCheck = {346, 111, 632, 9, 81};
        printDivisible(arrayForDivisibilityCheck, 3);

        System.out.print("\n\n16. printRounded() testing: ");
        printRounded(0.6438124);

        System.out.println("\n\n17. sortAscending() testing: ");
        int[] arrayToSortAsc = {2, 4, 1, 3};
        sortAscending(arrayToSortAsc);
        for (int arrayElement: arrayToSortAsc)
            System.out.print(arrayElement + " ");

        System.out.println("\n\n18. sortDescending testing: ");
        int[] arrayToSortDesc = {4, 2, 7, 1, 2, 10};
        sortDescending(arrayToSortDesc);
        for (int arrayElement: arrayToSortDesc)
            System.out.print(arrayElement + " ");

        System.out.print("\n\n20. containsDuplicates() testing: ");
        byte[] arrayToCheckDuplicates = {4, 2, 7, 1, 4, 10};
        System.out.println(containsDuplicates(arrayToCheckDuplicates));
    }
}
