/*

Problem description:
  https://www.quora.com/A-robber-enters-a-colony-of-houses-numbered-from-1-to-n-Every-house-has-a-number-printed-on-the-top-of-it-That-number-is-the-amount-of-money-inside-that-house-However-there-is-one-constraint-If-the-robber-robs-the-i-th-house-he-cant-rob-house-no-i-1-and-house-no-i+1-How-can-the-robber-maximise-his

Recurrence relation:
  max(n) is maximum sum from set of houses 1..n
  max(n) = Max{max(n - 2) + value[n], max(n - 1)}

*/

#include <stdio.h>

int max(int a, int b) {
  return (a > b) ? a : b;
}

void print(int n) {
  printf("%d\n", n);
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
    print(-1);
  }
  else if (count == 1) {
    print(value[0]);
  }
  else {
    dp[0] = value[0];
    dp[1] = max(value[0], value[1]);
    for (int i = 2; i < count; i++)
      dp[i] = max(dp[i - 2] + value[i], dp[i - 1]);

    print(dp[count - 1]);
  }

  return 0;
}

