import java.util.*;

class Main {
	static int M = 0;
	static int C = 0;
	static int[] K = new int[21];
	static int[][] cost = new int[21][21];

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		for (int n = 0; n < N; n++) {
			M = input.nextInt();
			C = input.nextInt();
			for (int i = 1; i <= C; i++) {
				K[i] = input.nextInt();
				for (int j = 1; j <= K[i]; j++) {
					cost[i][j] = input.nextInt();
				}
			}

			solve();
		}
	}

	static void solve() {
		int[][] dp = new int[M + 1][21];
		//--For each item type i
		for (int i = 1; i <= C; i++) {
			//--and item number j
			for (int j = 1; j <= K[i]; j++) {
				int itemCost = cost[i][j];
				//--and each max amount of money k
				for (int k = itemCost; k <= M; k++) {
					//--The greatest we can spend with k dollars is
					//  the greatest we could spend on types 1 to i - 1
					//  with this "k-minus-this-item's-cost" dollars
					//  OR
					//  The greatest amount we could with this item
					int oldTotal = dp[k - itemCost][i - 1];
					if (oldTotal == 0 && i != 1)
						continue; //--We did not have the i - 1 types, so
									// we will not have a full set

					int newTotal = oldTotal + itemCost;
					if (newTotal > k) continue;

					dp[k][i] = Math.max(newTotal, dp[k][i]);
				}
			}
		}

		int result = dp[M][C];
		if (result == 0) System.out.println("no solution");
		else System.out.println(result);
	}
}
	
