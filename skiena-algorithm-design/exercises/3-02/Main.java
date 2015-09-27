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

    //--base
    int[] h4 = new int[] {2};
    int[] w4 = new int[] {2};
    int L4 = 100;
    test(h4, w4, L4, 2);

    //--base2
    int[] h5 = new int[0];
    int[] w5 = new int[0];
    int L5 = 100;
    test(h5, w5, L5, 0);
  }

  void test(int[] height, int[] width, int L, int expected) {
    Book[] book = new Book[height.length];
    for (int i = 0; i < height.length; i++)
      book[i] = new Book(i, height[i], width[i]);

    System.out.printf("expected %d actual %d\n", expected, minHeight(book, L));
  }

  int minHeight(Book[] book, int L) {
    for (Book b : book)
      if (b.width > L) return -1;

    if (book.length == 0) return 0;

    int N = book.length;

    //--init
    Row[] row = new Row[N];
    row[0] = new Row(L);
    row[0].add(book[0]);

    for (int i = 1; i < row.length; i++) {
      row[i] = new Row(L);
      row[i].add(book[i]);
      row[i].totalHeight += row[i - 1].totalHeight;
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
      Row s = row[i];
      if (s.lastBook == N - 1)
        min = Math.min(min, s.totalHeight);
    }

    return min;
  }

  class Row {
    int tallest;
    int spaceRem;
    int totalHeight;
    int lastBook;
    
    Row(int length) {
      spaceRem = length;
    }

    void add(Book b) {
      spaceRem -= b.width;
      tallest = Math.max(tallest, b.height);
      totalHeight += b.height;
      lastBook = Math.max(lastBook, b.id);
    }
  }

  class Book {
    int id;
    int height;
    int width;

    Book(int id, int h, int w) {
      this.id = id;
      height = h;
      width = w;
    }
  }
}
