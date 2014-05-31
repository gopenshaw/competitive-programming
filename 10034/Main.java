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