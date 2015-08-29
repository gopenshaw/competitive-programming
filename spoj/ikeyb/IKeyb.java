import java.util.*;

class IKeyb {
	static int K = 90;
	static int L = 90;
	static String letters;
	static String keys;
	static int[] freq = new int[L];
	static int[][] dp = new int[K][L];
	static BackPointer[][] trail = new BackPointer[K][L];

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		for (int i = 0; i < n; i++) {
			K = s.nextInt();
			L = s.nextInt();
			keys = s.next();
			letters = s.next();
			for (int j = 0; j < L; j++) {
				freq[j] = s.nextInt();
			}

			BackPointer last = new IKeyb().solve();

			System.out.printf("Keyboard #%d:\n", i + 1);
			printReverse(last);
			System.out.println("\n");
		}
	}

	public BackPointer solve() {
		int[] minPos = new int[K];
		//--For every letter, start at the last
		//  position it could be placed.
		//--Calculate the cost of putting it there, and
		//  all previous, valid positions
		dp[0][0] = freq[0];
		trail[0][0] = new BackPointer(0,0,0,null);
		for (int letter = 1; letter < L; letter++) {
			for (int key = Math.min(letter, K - 1); key >= 0; key--) {
				int minCost = Integer.MAX_VALUE;
				for (int pos = letter - key; pos >= 0; pos--) {
					if (key == 0 && pos < letter) break;
					int cost = 0;
					BackPointer p;
					if (pos != 0) {
						cost = dp[key][pos - 1] + (pos + 1) * freq[letter];
						trail[key][pos] = new BackPointer(letter,
							key,
							pos,
							trail[key][pos - 1]);
					}
					else {
						cost = freq[letter] + dp[key - 1][minPos[key - 1]];
						trail[key][pos] = new BackPointer(letter,
							key,
							pos,
							trail[key - 1][minPos[key - 1]]);
					}

					dp[key][pos] = cost;
					//--Store the best position for the next number
					if (cost < minCost) {
						minCost = cost;
						minPos[key] = pos;
					}
				}
			}
		}

		//--Find the best result
		int result = Integer.MAX_VALUE;
		BackPointer last = null;
		for (int key = Math.min(L, K) - 1; key >= 0; key--) {
			for (int pos = L - 1 - key; pos >= 0; pos--) {
				if (key == 0 && pos < L - 1) break;
				if (dp[key][pos] < result) {
					result = dp[key][pos];
					last = trail[key][pos];
				}
			}
		}

		return last;
	}

	static void printReverse(BackPointer p) {
		if (p == null) return;
		printReverse(p.prev);
		if (p.pos == 0) {
			if (p.letter != 0) System.out.println();
			System.out.printf("%s: ", keys.charAt(p.key));
		}

		System.out.print(letters.charAt(p.letter));
	}

	class BackPointer {
		int letter;
		int key;
		int pos;

		BackPointer prev;

		public BackPointer(int letter, int key, int pos, BackPointer prev) {
			this.letter = letter;
			this.key = key;
			this.pos = pos;
			this.prev= prev;
		}

		@Override
		public String toString() {
			return String.format("%s, key %s, pos %d\n",
				letters.charAt(letter),
				keys.charAt(key),
				pos);
		}
	}
}
