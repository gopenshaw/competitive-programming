import java.util.*;

class IKeyb {
	static int K = 90;
	static int L = 90;
	static String letters;
	static String keys;
	static int[] freq = new int[L];
	static int[][] dp = new int[K][L];

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

			new IKeyb().solve();
		}
	}

	public void solve() {
		BackPointer[][] trail = new BackPointer[K][L];
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
				System.out.printf("Count %d key %d pos %d\n",
					dp[key][pos], key, pos);
				if (dp[key][pos] < result) {
					result = dp[key][pos];
					last = trail[key][pos];
				}
			}
		}

		System.out.println(result);
		while (last != null) {
			System.out.print(last);
			last = last.prev;
		}
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
