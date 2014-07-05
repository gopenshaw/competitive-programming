//
// UVA #10779 - Collector's Problem
// Garrett Openshaw
//

import java.util.*;

class Main {
	
	public static int[][] stickers = new int[10][25];
	public static int numPeople;
	public static int numStickers;

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();

		for (int n = 1; n <= numCases; n++) {
			numPeople = conIn.nextInt();
			numStickers = conIn.nextInt();

			for (int i = 0; i < numPeople; i++) {
				int count = conIn.nextInt();
				for (int c = 0; c < count; c++) {
					int value = conIn.nextInt();
					stickers[i][value]++;
				}
			}

			int maxStickers = maxStickers();

			System.out.println("Case #" + n + ": " + maxStickers);

			if (n != numCases) {
				for (int i = 0; i < numPeople; i++) {
					for (int j = 0; j < numStickers; j++) {
						stickers[i][j] = 0;
					}
				}
			}
		}
	}

	public static int maxStickers() {
		return 0;
	}
}
