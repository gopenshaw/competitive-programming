import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner conIn = new Scanner(System.in);
    long n = conIn.nextInt();
    long m = conIn.nextInt();
    long c = conIn.nextInt();
    while (n != 0 || m != 0 || c != 0) {
      long height = n - 7;
      long width = m - 7;
      if (height <= 0 || width <= 0) {
        System.out.println(0);
      }
      else {
        long offset = height % 2 == 1 && width % 2 == 1 ? c : 0;
        System.out.println(height * width / 2 + offset); 
      }

      n = conIn.nextInt();
      m = conIn.nextInt();
      c = conIn.nextInt();
    }
  }
}
