import java.util.*;

class Main {
  static int N = 100;
  static int A[][] = new int[N + 1][N + 1];
  static int dp[][] = new int[N + 1][N + 1];

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= i; j++) {
        A[i][j] = input.nextInt();
      }
    }

    dp[1][1] = A[1][1];
    for (int i = 2; i <= N; i++) {
      dp[i][1] = dp[i - 1][1] + A[i][1];
      dp[i][i] = dp[i - 1][i - 1] + A[i][i];
    }

    for (int i = 3; i <= N; i++) {
      for (int j = 2; j< i; j++) {
        dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + A[i][j];
      }
    }

    int result = 0;
    for (int i = 1; i <= N; i++) {
      result = Math.max(result, dp[N][i]);
    }

    System.out.println(result);
  }
}
