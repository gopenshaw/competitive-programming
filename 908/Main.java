// 
// UVA #908 - Re-connecting Computer Sites
// Garrett Openshaw
// 

import java.util.*;

class Edge implements Comparable<Edge>{
	int source;
	int dest;
	int cost;
	
	public Edge(int source, int dest, int cost) {
		this.source = source;
		this.dest = dest;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Edge e) {
		return this.cost - e.cost;
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

	public static PriorityQueue<Edge> q = new PriorityQueue<Edge>();

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		boolean firstCase = true;
		
		while (conIn.hasNext()) {
			if (!firstCase) {
				q.clear();
				System.out.println();
			}
			
			firstCase = false;
			int result = 0;
		
			int numSites = conIn.nextInt();
			int originalCost = 0;
			for (int i = 0; i < numSites - 1; i++) {
				conIn.nextInt();
				conIn.nextInt();
				originalCost += conIn.nextInt();
			}
			
			System.out.println(originalCost);
			
			int numNewLines = conIn.nextInt();
			for (int i = 0; i < numNewLines; i++) {
				int source = conIn.nextInt();
				int dest = conIn.nextInt();
				int cost = conIn.nextInt();
				q.add(new Edge(source, dest, cost));
			}
			
			int numOriginal = conIn.nextInt();
			for (int i = 0; i < numOriginal; i++) {
				int source = conIn.nextInt();
				int dest = conIn.nextInt();
				int cost = conIn.nextInt();
				q.add(new Edge(source, dest, cost));
			}
			
			//--Kruskal's
			DisjointSets set = new DisjointSets(numSites);
			while (!q.isEmpty()) {
				Edge current = q.poll();
				int set1 = set.find(current.source - 1);
				int set2 = set.find(current.dest - 1);
				if (set1 != set2) {
					set.union(set1, set2);
					result += current.cost;
				}
			}
			
			System.out.println(result);
		}
	}
}