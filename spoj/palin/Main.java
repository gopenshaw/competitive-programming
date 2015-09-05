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
                        if (n[pair] > n[j]) {
                            n[pair] = (char)(n[j] + 1);
                            n[j]++;
                        }
                        else {
                            n[pair] = n[j];
                        }
                    }
                }
            }

            if (larger) {
                System.out.println(n);
                continue;
            }

            //--The number was already a palindrome!

            //--Set all the 9's to 0's, and leave the pointer
            //  on the first non-9 digit
            int mid = (n.length - 1) / 2;
            int pointer = mid;
            while (pointer >= 0 && n[pointer] == '9') {
                if (n.length % 2 == 0) {
                    n[pointer] = '0';
                }

                n[n.length - pointer - 1] = '0';
                pointer--;
            }
            
            //--Increment the pointer and maybe it's pair
            if (pointer >= 0) {
                if (n.length % 2 == 0
                    || pointer != mid) {
                    n[n.length - pointer - 1]++;
                }

                n[pointer]++;
                System.out.println(n);
            }
            //--The number was all nines, print
            else {
                int zeroLength = n.length - 1;
                System.out.print(1);
                for (int j = 0; j < zeroLength; j++)
                    System.out.print(0);
                
                System.out.println(1);
            }
        }
    }
}
