import java.util.*;

class Main {

	public static int[][] cache = new int[262144][18];

	public static String name;
	public static boolean[] selected = new boolean[18];

	public static void main(String[] args) {

		Scanner conIn = new Scanner(System.in);

		name = conIn.next();

		while (!name.equals("0")) {

			clearCache();

			int length = name.length();
			for (int i = 0; i < 18; i++) {
				if (i < length)
					selected[i] = true;
				else
					selected[i] = false;
			}

			int min = Integer.MAX_VALUE;

			for (int i = 0; i < name.length(); i++) {
				int result = minButtonPushes(selected, i);
				if (result < min) min = result;
			}

			System.out.println(min);

			name = conIn.next();
		}
	}

	public static int minButtonPushes(boolean[] selected, int activePosition) {

		int cacheIndex = getIndex(selected);
		int cacheResult = cache[cacheIndex][activePosition];
		if (cacheResult != 0)
			return cacheResult;

		int length = 0;
		int[] map = new int[18];
		for (int i = 0; i < 18; i++) {
			if (selected[i]) {
				map[length] = i;
				length++;
			}
		}

		if (length == 1) {
			return minDistance('A', name.charAt(map[0])) + 1;
		}

		int min = Integer.MAX_VALUE;

		selected[map[activePosition]] = false;

		for (int i = 0; i < length; i++) {
			if (i == activePosition)
				continue;

			int sub;
			if (i < activePosition)
				sub = minButtonPushes(selected, i);
			else
				sub = minButtonPushes(selected, i - 1);
			
			int cursorMove = activePosition > i ? activePosition - (i + 1) : i - activePosition;
			int charMove = minDistance(name.charAt(map[i]), name.charAt(map[activePosition]));

			int result = sub + cursorMove + charMove + 1;
			if (result < min) min = result;
		}

		selected[map[activePosition]] = true;

		cache[cacheIndex][activePosition] = min;

		return min;
	}

	public static int getIndex(boolean[] a) {
		int index = 0;
		if (a[0]) index += 1;
		int power = 2;

		for (int i = 1; i < 18; i++) {
			if (a[i]) 
				index += power;

			power = power * 2;
		}

		return index;
	}

	public static void clearCache() {
		for (int i = 0; i < 262144; i++) {
			for (int j = 0; j < 18; j++) {
				cache[i][j] = 0;
			}
		}
	}

	public static int minDistance(char a, char b) {
		int d1 = Math.abs(a - b);

		int d2;
		if (a > b) {
			d2 = 'Z' - a + b - 'A' + 1;
		}
		else {
			d2 = 'Z' - b + a - 'A' + 1;
		}

		return Math.min(d1, d2);
	}
}