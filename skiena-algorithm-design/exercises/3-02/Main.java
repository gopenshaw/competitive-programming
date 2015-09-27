class Main {
  public static void main(String[] args) {
    new Main().runTestCases();
  }

  void runTestCases() {
    //--error
    int[] h1 = new int[] {5};
    int[] w1 = new int[] {5};
    int L1 = 4;
    test(h1, w1, L1, -1);

    //--greedy
    int[] h2 = new int[] {2, 2, 2, 2, 5};
    int[] w2 = new int[] {1, 1, 1, 1, 1};
    int L2 = 4;
    test(h2, w2, L2, 7);

    //--dp
    int[] h3 = new int[] {2, 5, 2, 2, 5};
    int[] w3 = new int[] {1, 1, 1, 1, 1};
    int L3 = 4;
    test(h3, w3, L3, 7);
  }

  void test(int[] height, int[] width, int L, int expected) {
    System.out.printf("expected %d actual %d\n", expected, minHeight(height, width, L));
  }

  int minHeight(int[] height, int[] width, int L) {
    for (int i : width)
      if (i > L) return -1;

    if (height.length != width.length)
      return -1;

    int N = height.length;

    //--init
    State[] row = new State[N];
    row[0] = new State();
    row[0].spaceRem = L - width[0];
    row[0].totalHeight = height[0];
    row[0].tallest = height[0];
    row[0].lastBook = 0;

    for (int i = 1; i < row.length; i++) {
      row[i] = new State();
      row[i].spaceRem = L - width[i];
      row[i].totalHeight = row[i - 1].totalHeight + height[i];
      row[i].tallest = height[i];
      row[i].lastBook = i;
    }

    //--dp
    for (int i = 1; i < N; i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (row[j].lastBook != i - 1) break;
        if (row[j].totalHeight + height[i] < row[j + 1].totalHeight
          || (row[j].totalHeight + height[i] == row[j + 1].totalHeight
            && L - width[i] <= row[j + 1].spaceRem)) {
          row[j + 1].spaceRem = L - width[i];
          row[j + 1].totalHeight = row[j].totalHeight + height[i];
          row[j + 1].tallest = height[i];
          row[j + 1].lastBook = i;
        }

        if (row[j].spaceRem >= width[i]) {
          row[j].spaceRem -= width[i];
          int dif = Math.max(0, height[i] - row[j].tallest);
          row[j].totalHeight += dif;
          row[j].tallest += dif;
          row[j].lastBook = i;
        }
      }
    }

    int min = Integer.MAX_VALUE;
    for (int i = 0; i < row.length; i++) {
      State s = row[i];
      if (s.lastBook == N - 1)
        min = Math.min(min, s.totalHeight);
    }

    return min;
  }

  class State {
    int tallest;
    int spaceRem;
    int totalHeight;
    int lastBook;
    
    State() {
      totalHeight = Integer.MAX_VALUE;
    }
  }
}
