//--UVA #11003

import java.util.*;

class Box {
	int weight;
	int capacity;
}

class Main {
	//--A array will hold the provided input
	public static Box[] A = new Box[1000];
	
	//--M will hold the capacity of the box in the stack
	//--with the smallest remaining capacity
	public static int[] M = new int[1001];
	
	public static int numBoxes;

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			A[i] = new Box();
		}
	
		Scanner conIn = new Scanner(System.in);
		numBoxes = conIn.nextInt();
		
		while (numBoxes != 0) {
		
			for (int i = 0; i < numBoxes; i++) {
				A[i].weight = conIn.nextInt();
				A[i].capacity = conIn.nextInt();
			}
			
			System.out.println(LIS());
			
			numBoxes = conIn.nextInt();
		}
	}
	
	public static int LIS() {
		//--Add the first box
		M[1] = A[0].capacity;
		int length = 1;
		
		for (int i = 1; i < numBoxes; i++) {
			for (int j = length; j > 0; j--) {
				int newCapacity =  M[j] - A[i].weight;
				if (newCapacity >= 0
					&& newCapacity > M[j + 1])
					M[j + 1] = newCapacity < A[i].capacity ?
									newCapacity : A[i].capacity;
					if (j + 1 > length)
						length = j + 1;
			}
			
			//--Check if block should be added to base
			if (A[i].capacity > M[1])
				M[1] = A[i].capacity;
		}
		
		return length;
	}
}