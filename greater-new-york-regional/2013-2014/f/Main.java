import java.util.*;

class WinningMove {
	public int col;
	public int row;

	public WinningMove(int r, int c) {
		this.col = c;
		this.row = r;
	}

	@Override
	public String toString() {
		return col + " " + row;
	}
}

class Main {
	public static WinningMove[][][] position = new WinningMove[101][101][101];

	public static void main(String[] args) {
		for (int p = 1; p <= 100; p++) {
			for (int q = 0; q <= p; q++) {
				for (int r = 0; r <= q; r++) {
					position[p][q][r] = getWinningMove(p, q, r);
				}
			}
		}

		printOutputForTestCases();
	}

	public static WinningMove getWinningMove(int p, int q, int r) {
		//--check row 3
		for (int i = 0; i < r; i++) {
			if (position[p][q][i] == null) {
				return new WinningMove(3, i + 1);
			}
		}

		//--check row 2
		for (int i = 0; i < q; i++) {
			if (position[p][i][Math.min(i, r)] == null) {
				return new WinningMove(2, i + 1);
			}
		}

		//--check row 1
		for (int i = 1; i < p; i++) {
			if (position[i][Math.min(i, q)][Math.min(i, r)] == null) {
				return new WinningMove(1, i + 1);
			}
		}

		return null;
	}

	public static void printOutputForTestCases() {
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();

		for (int i = 1; i <= numCases; i++) {
			conIn.nextInt();
			int p = conIn.nextInt();
			int q = conIn.nextInt();
			int r = conIn.nextInt();

			WinningMove result = position[p][q][r];

			if (result == null)
				System.out.println(i + " L");
			else
				System.out.println(i + " W " + result);
		}
	}
}
