import java.util.*;

class Main {
	static Scanner conIn;

	static int[][] grid = new int[10][100];
	static int row;
	static int col;

	public static void main(String[] args) {
		conIn = new Scanner(System.in);
		while (conIn.hasNext()) {
			getInput();
		}
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
