import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            int v = s.nextInt();
            int t = s.nextInt();
            //  (1/2)(v/t)(2t)^2
            //  = (1/2)(v/t)*4t^2
            //  = 2*v*t
            System.out.println(2 * v * t);
        }
    }
}
