//-UVA #497 - Longest Increasing Subsequence (n log n)

import java.util.*;

class Main {

	public static Scanner conIn;
	
	public static int numElements;
	public static int length;
	public static int[] A = new int[1000000];
	public static int[] M = new int[1000000];
	public static int[] P = new int[1000000];
	public static Stack<Integer> LIS;

	public static void main(String[] args) {
		LIS = new Stack<Integer>();
		conIn = new Scanner(System.in);
		
		int numCases = new Integer(conIn.nextLine());
		conIn.nextLine();
		
		for (int i = 0; i < numCases; i++) {
			solveProblem();
			
			if (i != numCases - 1)
				System.out.println();
		}
	}
	
	public static void solveProblem() {
		getInput();
		
		//--insert first element with no parent
		length = 1;
		M[1] = 0;
		P[0] = -1;
		
		//--no element can be in a sequence of zero length
		M[0] = -1;
		
		//--build M and P arrays
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
		LIS.clear();
		int index = M[length];
		do {
			LIS.add(A[index]);
			index = P[index];
		} while (index != -1);
		
		System.out.println("Max hits: " + length);
		
		while (!LIS.isEmpty())
			System.out.println(LIS.pop());
	}
	
	public static void getInput() {
		numElements = 0;
		while (conIn.hasNext()) {
			String input = conIn.nextLine();
			if (input.isEmpty())
				break;
				
			A[numElements++] = new Integer(input);
		}
	}
}