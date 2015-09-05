import java.util.*;

class Main {

    static int[] car = new int[50];

    public static void main(String[] args) {
        Scanner conIn = new Scanner(System.in);
        int numCases = conIn.nextInt();
        for (int i = 0; i < numCases; i++) {
            int count = conIn.nextInt();
            int perm = 0;
            for (int j = 0; j < count; j++) {
                car[j] = conIn.nextInt();

                //--count permutations
                for (int k = 0; k < j; k++) {
                    if (car[k] > car[j]) perm++;
                }
            }

            System.out.printf(
                "Optimal train swapping takes %d swaps.\n", perm);
        }
    }
}
        
