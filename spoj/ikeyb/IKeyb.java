import java.util.*;

class IKeyb {

	//1 8 26 23456789 ABCDEFGHIJKLMNOPQRSTUVWXYZ 
	static int[] freq = {3371, 589, 1575, 1614, 6212, 971, 773, 1904, 2989, 123, 209, 1588, 1513, 2996, 3269, 1080, 121, 2726, 3083, 4368, 1334, 518, 752, 427, 733, 871 };

	public static void main(String[] args) {
		new IKeyb().solve();
	}

	public void solve() {
		int letterCount = 26;
		int keyCount = 8;
		int[][] dp = new int[keyCount][26];
		BackPointer[][] trail = new BackPointer[keyCount][26];
		int[] minPos = new int[keyCount];
		//--For every letter, start at the last
		//  position it could be placed.
		//--Calculate the cost of putting it there, and
		//  all previous, valid positions
		dp[0][0] = freq[0];
		trail[0][0] = new BackPointer('a',0,0,null);
		for (int letter = 1; letter < freq.length; letter++) {
			for (int key = Math.min(letter, keyCount - 1); key >= 0; key--) {
				int minCost = Integer.MAX_VALUE;
				for (int pos = letter - key; pos >= 0; pos--) {
					if (key == 0 && pos < letter) break;
					int cost = 0;
					BackPointer p;
					if (pos != 0) {
						cost = dp[key][pos - 1] + (pos + 1) * freq[letter];
						trail[key][pos] = new BackPointer((char)((int)letter + 'a'),
							key,
							pos,
							trail[key][pos - 1]);
					}
					else {
						cost = freq[letter] + dp[key - 1][minPos[key - 1]];
						trail[key][pos] = new BackPointer((char)((int)letter + 'a'),
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
		for (int key = Math.min(letterCount, keyCount) - 1; key >= 0; key--) {
			for (int pos = letterCount - 1 - key; pos >= 0; pos--) {
				if (key == 0 && pos < letterCount - 1) break;
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
		char letter;
		int key;
		int pos;

		BackPointer prev;

		public BackPointer(char letter, int key, int pos, BackPointer prev) {
			this.letter = letter;
			this.key = key;
			this.pos = pos;
			this.prev= prev;
		}

		@Override
		public String toString() {
			return String.format("%s, key %d, pos %d\n",
				letter, key, pos);
		}
	}
}
