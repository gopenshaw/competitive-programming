import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner conIn = new Scanner(System.in);
        int n = conIn.nextInt();
        for (int i = 0; i < n; i++)
            nextPalindrome(conIn.next());
    }

    public static void nextPalindrome(String number) {
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
            System.out.println(n);
            return;
        }

        if (n[mid] != '9') {
            n[mid]++;
            if (n.length % 2 == 0)
                n[mid + 1]++;

            System.out.println(n);
            return;
        }

        int pointer = mid;
        while (pointer >= 0
                && n[pointer] == '9') {
            int pair = n.length - pointer - 1;
            n[pointer] = '0';
            n[pair] = '0';
            pointer--;
        }

        if (pointer < 0) {
            System.out.print('1');
            for (int i = 0; i < n.length - 1; i++) {
                System.out.print('0');
            }
            System.out.println('1');
            return;
        }

        n[pointer]++;
        n[n.length - pointer - 1]++;
        System.out.println(n);
        return;
    }
}
