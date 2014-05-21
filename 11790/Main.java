// 
// UVA #11790
// Garrett Openshaw
// 

import java.util.*;

class Main {

	//--A[] holds the height
	public static int[] H = new int[1000000];
	
	//--L[] holds the length
	public static int[] L = new int[1000000];
	
	//--inc and dec will hold max length for increasing
	//--and decreasing sequences, respectively, that end
	//--with element i
	public static int[] inc = new int[1000000];
	public static int[] dec = new int[1000000];
	
	public static int numBuildings;
	public static int lis;
	public static int lds;

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();
		for (int n = 0; n < numCases; n++) {
			//--Get input
			numBuildings = conIn.nextInt();
			for (int i = 0; i < numBuildings; i++)
				H[i] = conIn.nextInt();
			for (int i = 0; i < numBuildings; i++)
				L[i] = conIn.nextInt();
			
			//--Solve problem
			solveProblem();
			
			//--Print results
			if (lis > lds) {
				System.out.println("Case " + (n + 1)
					+ ". Increasing (" + lis + "). Decreasing ("
					+ lds + ").");
			}
			else {
				System.out.println("Case " + (n + 1)
					+ ". Decreasing (" + lds + "). Increasing ("
					+ lis + ").");
			}
		}
	}
	
	public static void solveProblem() {
		init();
		
		//--for every building, check every building to its left
		for (int i = 1; i < numBuildings; i++) {
			for (int j = 0; j < i; j++) {
				if (H[j] < H[i]) {
					int newLength = L[i] + inc[j];
					if (newLength > inc[i])
						inc[i] = newLength;
					if (inc[i] > lis)
						lis = inc[i];
				}
				else if (H[j] > H[i]) {
					int newLength = L[i] + dec[j];
					if (newLength > dec[i])
						dec[i] = newLength;
					if (dec[i] > lds)
						lds = dec[i];
				}
			}
		}
	}
	
	public static void init() {
		//--sets up inc and dec arrays and 
		//--sets lis and lds to longest sequence of length one
		int length = 0;
		for (int i = 0; i < numBuildings; i++) {
			inc[i] = dec[i] = L[i];
			if (L[i] > length)
				length = L[i];
		}
		lis = lds = length;
	}
}