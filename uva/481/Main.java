//-UVA #481 - Longest Increasing Subsequence (n log n)

import java.util.*;

class Main {

	//--A holds the user-provided sequence
	public static int[] A = new int[1000000];
	
	//--M[k] holds an index 'i' such that A[i] is the minimum value
	//--for the kth element in an increasing sequence
	public static int[] M = new int[1000000 + 1];
	
	//--For the ith element in A[i], P[i] will hold an index j such that
	//--A[j] the parent of A[i]
	public static int[] P = new int[1000000];

	public static int length = 1;

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		int numElements = 0;
		while (conIn.hasNext())
			A[numElements++] = conIn.nextInt();
		
		//--insert first element with no parent
		length = 1;
		M[1] = 0;
		P[0] = -1;
		
		//--no element can be in a sequence of zero length
		M[0] = -1;
		
		for (int i = 1; i < numElements; i++) {
			int index = binarySearch(A[i]);
			M[index] = i;
			P[i] = M[index - 1];
			if (index > length)
				length = index;
		}
		
		printResults();
	}
	
	public static int binarySearch(int value) {
		int low = 1;
		int high = length;
		
		while (low < high) {
			int mid = (low + high) / 2;
			if (value > A[M[mid]])
				low = mid + 1;
			else
				high = mid - 1;
		}
		
		if (value <= A[M[low]])
			return low;
		else
			return low + 1;
	}
	
	public static void printResults() {
		Stack<Integer> lis = new Stack<Integer>();
		int index = M[length];
		do {
			lis.add(A[index]);
			index = P[index];
		} while (index != -1);
		
		System.out.println(length + "\n-");
		
		while (!lis.isEmpty())
			System.out.println(lis.pop());
	}
}