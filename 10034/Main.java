// 
// UVA #10034
// Garrett Openshaw
// 

import java.util.*;

class Freckle {
	public double x;
	public double y;
	
	public Freckle(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

class Edge implements Comparable<Edge>{
	int source;
	int dest;
	double cost;
	
	public Edge(int source, int dest, double cost) {
		this.source = source;
		this.dest = dest;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Edge e) {
		if (this.cost - e.cost < 0)
			return -1;
		else
			return 1;
	}
}

class DisjointSets {

	private int[] array;

	public DisjointSets(int numElements) {
		array = new int [numElements];
		for (int i = 0; i < array.length; i++) {
			array[i] = -1;
		}
	}

	public void union(int root1, int root2) {
		if (array[root2] < array[root1]) {
			array[root1] = root2;             // root2 is taller; make root2 new root
		} 
		else {
			if (array[root1] == array[root2]) {
				array[root1]--;            // Both trees same height; new one is taller
			}
			array[root2] = root1;       // root1 equal or taller; make root1 new root
		}
	}

	public int find(int x) {
		if (array[x] < 0) {
			return x;                         // x is the root of the tree; return it
		}
		else {
	      // Find out who the root is; compress path by making the root x's parent.
			array[x] = find(array[x]);
			return array[x];                                       // Return the root
		}
	}
}

class Main {

	public static Freckle[] A = new Freckle[100];

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		
		int numCases = conIn.nextInt();
		
		for (int n = 0; n < numCases; n++) {
		
			if (n != 0) {
				q.clear();
				System.out.println();
			}
		
			int numFreckles = conIn.nextInt();
			
			//--Get vertexes
			for (int i = 0; i < numFreckles; i++) {
				double x = conIn.nextDouble();
				double y = conIn.nextDouble();
				A[i] = new Freckle(x, y);
			}
			
			//--Make all edges
			for (int i = 0; i < numFreckles; i++) {
				for (int j = i; j < numFreckles; j++) {
					if (i == j)
						continue;
					q.add(new Edge(i, j, dist(i, j)));
				}
			}
		}
	}
	
	public static double dist(int source, int dest) {
		return Math.sqrt(Math.pow(A[source].x - A[dest].x, 2)
							+ Math.pow(A[source].y - A[dest].y, 2));
	}
}