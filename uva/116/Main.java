import java.util.*;

class Main {
	static Scanner conIn;

	static int[][] grid = new int[10][100];
	static int[][] best = new int[10][100];
	static int[][] next = new int[10][100];
	static int row;
	static int col;

	public static void main(String[] args) {
		conIn = new Scanner(System.in);
		while (conIn.hasNext()) {
			getInput();
			initialize();
			buildBottomUp();
			printBestSolution();
		}
	}

	static void initialize() {
		for (int i = 0; i < row; i++) {
			Arrays.fill(best[i], 1000);
			Arrays.fill(next[i], 1000);
		}

		for (int i = 0; i < row; i++) {
			best[i][col - 1] = grid[i][col - 1];
		}
	}

	static void buildBottomUp() {
		int test;

		//--From the second to the last column, going to the left
		for (int c = col - 2; c >= 0; c--) {
			//--For each row
			for (int r = 0; r < row; r++) {
				//--up
				test = grid[r][c] + best[up(r)][c + 1];
				if (test < best[r][c]) {
					best[r][c] = test;
					next[r][c] = up(r);
				}
				else if (test == best[r][c]
						&& up(r) < next[r][c]) {
					best[r][c] = test;
					next[r][c] = up(r);
				}

				//--across
				test = grid[r][c] + best[r][c + 1];
				if (test < best[r][c]) {
					best[r][c] = test;
					next[r][c] = r;
				}
				else if (test == best[r][c]
						&& r < next[r][c]) {
					best[r][c] = test;
					next[r][c] = r;
				}

				//--down
				test = grid[r][c] + best[down(r)][c + 1];
				if (test < best[r][c]) {
					best[r][c] = test;
					next[r][c] = down(r);
				}
				else if (test == best[r][c]
						&& down(r) < next[r][c]) {
					best[r][c] = test;
					next[r][c] = down(r);
				}
			}
		}
	}

	static void printBest() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(best[i][j] + " ");
			}

			System.out.println();
		}

		System.out.println();
	}

	static void printBestSolution() {
		int startingRow = 0;
		int smallest = Integer.MAX_VALUE;
		for (int i = 0; i < row; i++) {
			if (best[i][0] < smallest) {
				smallest = best[i][0];
				startingRow = i;
			}
		}

		System.out.print(startingRow + 1);
		int previousRow = startingRow;
		for (int i = 0; i < col - 1; i++) {
			System.out.print(" ");
			System.out.print(next[previousRow][i] + 1);

			previousRow = next[previousRow][i];
		}

		System.out.println();
		System.out.println(smallest);
	}

	static int down(int r) {
		return (r + 1) % row;
	}

	static int up(int r) {
		return (r + row - 1) % row;
	}

	static void getInput() {
		row = conIn.nextInt();
		col = conIn.nextInt();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				grid[i][j] = conIn.nextInt();
			}
		}
	}
}
