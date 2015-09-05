import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner conIn = new Scanner(System.in);
        int numCases = conIn.nextInt();
        for (int i = 0; i < numCases; i++) {
            char[] n = conIn.next().toCharArray();
            boolean larger = false;
            for (int j = 0; j < n.length / 2; j++) {
                int last = n.length - j - 1;
                if (n[j] != n[last]) {
                    larger = true;
                    n[last] = (char)(n[j] + 1);
                    n[j]++;
                }
            }

            if (!larger) {
                //--we may have to change 1 or 2 digits
                //  at the center of the number

                //--this change may trigger a carry, which
                //  would affect other numbers
            }
            
            System.out.println(n);
        }
    }
}
