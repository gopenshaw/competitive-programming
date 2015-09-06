import java.util.*;

class Main {
    public static void main(String[] args) {
        test("1000", "1001");
        test("2003", "2112");
        test("203", "212");
        test("88988", "89098");
        test("9999", "10001");
        test("10320", "10401");
        test("1425151", "1425241");
    }

    public static void test(String number, String expected) {
        String result = nextPalindrome(number);
        if (expected.equals(result)) System.out.printf(
            "Correct! %s --> %s\n", number, result);
        else System.out.printf("For %s expected %s but result was %s\n",
            number, expected, result);
    }

    public static String nextPalindrome(String number) {
        char[] n = number.toCharArray();
        int mid = (n.length - 1) / 2;
        boolean increased = false;
        boolean decreased = false;
        boolean directionSet = false;
        for (int i = mid; i >=0; i--) {
            int pair = n.length - i - 1;
            if (n[pair] != n[i]) {
                if (!directionSet) {
                    directionSet = true;
                    if (n[i] > n[pair])
                        increased = true;
                    else
                        decreased = true;
                }

                n[pair] = n[i];
            }
        }

        increased = (increased && !decreased);

        if (increased) {
            return new String(n);
        }

        if (n[mid] != '9') {
            n[mid]++;
            if (n.length % 2 == 0)
                n[mid + 1]++;

            return new String(n);
        }

        int pointer = mid;
        while (pointer >= 0
                && n[pointer] == '9') {
            int pair = n.length - pointer - 1;
            n[mid] = '0';
            n[pair] = '0';
            pointer--;
        }

        if (pointer < 0) {
            char[] result = new char[n.length + 1];
            Arrays.fill(result, '0');
            result[0] = '1';
            result[result.length - 1] = '1';
            return new String(result);
        }

        n[pointer]++;
        n[n.length - pointer - 1]++;
        return new String(n);
    }
}
