import java.util.*;

class Main {
    public static void main(String[] args) {
        test("1000", "1001");
        test("2003", "2112");
        test("203", "212");
    }

    public static void test(String number, String expected) {
        String result = nextPalindrome(number);
        if (expected.equals(result)) System.out.printf(
            "Correct result for %s\n", number);
        else System.out.printf("For %s expected %s but result was %s\n",
            number, expected, result);
    }

    public static String nextPalindrome(String number) {
        char[] n = number.toCharArray();
        int mid = (n.length - 1) / 2;
        boolean increased = false;
        for (int i = mid; i >=0; i--) {
            int pair = n.length - i - 1;
            if (n[pair] != n[i]) {
                if (n[i] > n[pair])
                    increased = true;

                n[pair] = n[i];
            }
        }

        if (increased) {
            return new String(n);
        }

        n[mid]++;
        if (n.length % 2 == 0)
            n[mid + 1]++;

        return new String(n);
    }
}
