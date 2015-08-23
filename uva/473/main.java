import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();
		StringBuilder numberWithComma = new StringBuilder(10);
		for (int i = 0; i < numCases; i++) {
			int n = conIn.nextInt();
			int t = conIn.nextInt();
			int m = conIn.nextInt();
			int song[] = new int[n];
			for (int j = 0; j < n; j++) {
				numberWithComma.append(conIn.next());
				if (j != n - 1)
					numberWithComma.deleteCharAt(numberWithComma.length() - 1);

				song[j] = new Integer(numberWithComma.toString());
				numberWithComma.delete(0, numberWithComma.length());
			}
			System.out.println(maxSongs(m, t, song));
			if (i != numCases - 1) System.out.println();
		}
	}

	static int maxSongs(int m, int t, int[] song) {
		int[] timeRem = new int[song.length + 1];
		int[] discRem = new int[song.length + 1];
		Arrays.fill(discRem, -1);
		discRem[0] = m;

		int result = 0;
		for (int i = 0; i < song.length; i++) {
			if (song[i] > t) continue;
			for (int j = result; j >= 0; j--) {
				int newTime = timeRem[j] - song[i];
				int newDisc = discRem[j];
				if (newTime < 0) {
					newDisc--;
					newTime = t - song[i];
				}

				if (newDisc < discRem[j + 1]
					|| newDisc == -1) continue;

				if (newDisc > discRem[j + 1]
					|| newTime > timeRem[j + 1]) {
//					System.out.printf("Adding song %d of length %d to position %d\n",
//						i, song[i], j);
					discRem[j + 1] = newDisc;
					timeRem[j + 1] = newTime;
					result = Math.max(result, j + 1);
				}
			}
		}

		return result;
	}
}
