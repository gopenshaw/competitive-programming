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
    Book[] book = new Book[height.length];
    for (int i = 0; i < height.length; i++)
      book[i] = new Book(height[i], width[i]);

    System.out.printf("expected %d actual %d\n", expected, minHeight(book, L));
  }

  int minHeight(Book[] book, int L) {
    for (Book b : book)
      if (b.width > L) return -1;

    int N = book.length;

    //--init
    State[] row = new State[N];
    row[0] = new State();
    row[0].spaceRem = L - book[0].width;
    row[0].totalHeight = book[0].height;
    row[0].tallest = book[0].height;
    row[0].lastBook = 0;

    for (int i = 1; i < row.length; i++) {
      row[i] = new State();
      row[i].spaceRem = L - book[i].width;
      row[i].totalHeight = row[i - 1].totalHeight + book[i].height;
      row[i].tallest = book[i].height;
      row[i].lastBook = i;
    }

    //--dp
    for (int i = 1; i < N; i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (row[j].lastBook != i - 1) break;
        if (row[j].totalHeight + book[i].height < row[j + 1].totalHeight
          || (row[j].totalHeight + book[i].height == row[j + 1].totalHeight
            && L - book[i].width <= row[j + 1].spaceRem)) {
          row[j + 1].spaceRem = L - book[i].width;
          row[j + 1].totalHeight = row[j].totalHeight + book[i].height;
          row[j + 1].tallest = book[i].height;
          row[j + 1].lastBook = i;
        }

        if (row[j].spaceRem >= book[i].width) {
          row[j].spaceRem -= book[i].width;
          int dif = Math.max(0, book[i].height - row[j].tallest);
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

  class Book {
    int height;
    int width;

    Book(int h, int w) {
      height = h;
      width = w;
    }
  }
}
