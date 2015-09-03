import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int cases = s.nextInt();
        for (int i = 0; i < cases; i++) {
            int a = s.nextInt();
            int b = s.nextInt();
            if (a < b) System.out.println("<");
            else if (a > b) System.out.println(">");
            else System.out.println("=");
        }
    }
}
