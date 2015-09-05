import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner conIn = new Scanner(System.in);
        int numCases = conIn.nextInt();
        for (int i = 0; i < numCases; i++) {
            char[] n = conIn.next().toCharArray();
            boolean larger = false;
            for (int j = (n.length - 1) / 2; j >= 0; j--) {
                int pair = n.length - j - 1;
                if (pair == j) continue;
                if (n[j] != n[pair]) {
                    if (larger) {
                        n[pair] = n[j];
                    }
                    else {
                        larger = true;
                        n[pair] = (char)(n[j] + 1);
                        n[j]++;
                    }
                }
            }

            //--handle a string of 9's separately
            boolean special = false;

            //--The number was already a palindrome
            if (!larger) {
                //--we may have to change 1 or 2 digits
                //  at the center of the number
                int mid = (n.length - 1) / 2;
                while (mid >= 0 && n[mid] == '9') {
                    if (n.length % 2 == 0) {
                        n[mid] = '0';
                    }

                    n[n.length - mid - 1] = '0';
                    mid--;

                    special = mid < 0;
                }
                
                if (special) {
                    int zeroLength = n.length - 1;
                    System.out.print(1);
                    for (int j = 0; j < zeroLength; j++)
                        System.out.print(0);
                    
                    System.out.println(1);
                }
                else {
                    if (n.length % 2 == 0
                        || mid != (n.length - 1) / 2) {
                        n[n.length - mid - 1]++;
                    }

                    n[mid]++;
                }
            }
            
            if (!special) {
                System.out.println(n);
            }
        }
    }
}
