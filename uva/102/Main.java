import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner conIn = new Scanner(System.in);
        int b1, g1, c1, b2, g2, c2, b3, g3, c3;
        String[] color = new String[6];
        int cost[] = new int[6];
        
        while (conIn.hasNext()) {
            b1 = conIn.nextInt();
            g1 = conIn.nextInt();
            c1 = conIn.nextInt();
            b2 = conIn.nextInt();
            g2 = conIn.nextInt();
            c2 = conIn.nextInt();
            b3 = conIn.nextInt();
            g3 = conIn.nextInt();
            c3 = conIn.nextInt();

            //--calculate cost for all 6 possibilites
            color[0] = "BCG";
            cost[0] = g1 + c1 + b2 + g2 + b3 + c3;

            color[1] = "BGC";
            cost[1] = c1 + g1 + b2 + c2 + b3 + g3;

            color[2] = "CBG";
            cost[2] = b1 + g1 + c2 + g2 + b3 + c3;

            color[3] = "CGB";
            cost[3] = b1 + g1 + b2 + c2 + c3 + g3;

            color[4] = "GBC";
            cost[4] = b1 + c1 + c2 + g2 + b3 + g3;

            color[5] = "GCB";
            cost[5] = b1 + c1 + b2 + g2 + c3 + g3;

            int min = cost[0];
            int index = 0;
            for (int i = 1; i < 6; i++) {
                if (cost[i] < min) {
                    min = cost[i];
                    index = i;
                }
            }

            System.out.printf("%s %d\n", color[index], min);
        }
    }
}
