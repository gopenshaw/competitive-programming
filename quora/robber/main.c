/*

https://www.quora.com/A-robber-enters-a-colony-of-houses...

Recurrence relation:
  max(n) is maximum sum from set of houses 1..n
  max(n) = Max{max(n - 2) + value[n], max(n - 1)}

*/

#include <math.h>
#include <stdio.h>

int max(int a, int b) {
  return (a > b) ? a : b;
}

int main() {
  int MAX_N = 10000;
  int value[MAX_N];
  int dp[MAX_N];

  int n;
  int count = 0;
  while (scanf("%d", &n) != EOF) {
    value[count++] = n;
  }

  if (count == 0) {
    printf("%d\n", -1);
  }
  else if (count == 1) {
    printf("%d\n", value[0]);
  }
  else {
    dp[0] = value[0];
    dp[1] = max(value[0], value[1]);
    for (int i = 2; i < count; i++)
      dp[i] = max(dp[i - 2] + value[i], dp[i - 1]);

    printf("%d\n", dp[count - 1]);
  }

  return 0;
}

