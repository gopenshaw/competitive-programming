//-UVA #481 - Longest Increasing Subsequence (n log n)

import java.util.*;

class Main {

	public static int[] A = new int[1000000];
	public static int[] M = new int[1000000 + 1];
	public static int[] P = new int[1000000];
	public static int length = 1;

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		int numElements = 0;
		while (conIn.hasNext())
			A[numElements++] = conIn.nextInt();
			
		M[1] = A[0];
		for (int i = 1; i < numElements; i++) {
			int index = binarySearch(A[i]);
		}
	}
	
	public static int binarySearch(int value) {
		int low = 1;
		int high = length;
		
		while (low < high) {
			int mid = (low + high) / 2;
			if (value > M[mid])
				low = mid + 1;
			else
				high = mid - 1;
		}
		
		if (value <= M[low])
			return low;
		else
			return low + 1;
	}
	
	public static void printResults() {
		
	}
}