import java.util.*;

class Main {
    static String[] value = new String[100000];

    public static void main(String[] args) {
        Scanner conIn = new Scanner(System.in);
        int numCases = conIn.nextInt();
        for (int i = 0; i < numCases; i++) {
            conIn.nextLine();
            conIn.nextLine();
            String[] perm = conIn.nextLine().split(" ");

            for (int j = 0; j < perm.length; j++) {
                int index = new Integer(perm[j]) - 1;
                value[index] = conIn.next();
            }

            for (int j = 0; j < perm.length; j++)
                System.out.println(value[j]);

            if (i != numCases - 1) System.out.println();
        }
    }
}
