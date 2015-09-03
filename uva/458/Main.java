import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            char[] input = s.nextLine().toCharArray();
            for (int i = 0; i < input.length; i++) {
                input[i] -= 7;
            }
            
            System.out.println(input);
        }
    }
}
