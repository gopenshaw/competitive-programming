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

class Main {

	public static PriorityQueue<Edge> q = new PriorityQueue<Edge>();

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		
		while (conIn.hasNext()) {
			q.clear();
		
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
		}
	}
}