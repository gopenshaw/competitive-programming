import java.util.*;

class Main {

	public static int[] value = new int[101];
	public static int[][] V = new int[101][25001];

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();
		
		for (int n = 0; n < numCases; n++) {
			int numCoins = conIn.nextInt();
			int totalValue = 0;
			for (int i = 1; i <= numCoins; i++) {
				int coin = conIn.nextInt();
				totalValue += coin;	
				value[i] = coin;		
			}
			
			int maxValue = totalValue / 2;	

			for (int i = 0; i <= maxValue; i++) {
				V[0][i] = 0;
			}
			
			int offset = totalValue % 2 == 0 ? 0 : 1;
			
			System.out.println(totalValue - 2 * knapSack(numCoins, maxValue));
		}
	}
	
	public static int knapSack(int numCoins, int maxValue) {
		for (int i = 1; i <= numCoins; i++) {
			for (int j = 0; j <= maxValue; j++) {
				int currentValue = value[i];
				if (currentValue <= j) {
					V[i][j] = 
						Math.max(V[i - 1][j], 
								currentValue + V[i - 1][j - currentValue]);
				}
				else
					V[i][j] = V[i - 1][j];
			}
		} 
			
		return V[numCoins][maxValue]; 
	}
}
