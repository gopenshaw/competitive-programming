// 
// UVA #383 - Shipping Routes
// Garrett Openshaw
//

import java.util.*;

class Leg {
	public int distance;
	public int node;
	
	public Leg(int node, int distance) {
		this.node = node;
		this.distance = distance;
	}
}

class Main {

	public static HashMap<String, Integer> warehouseMap = new HashMap<String, Integer>();
	public static boolean[][] graph = new boolean[31][31];
	public static boolean [] wasVisited = new boolean[31];

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();
		System.out.println("SHIPPING ROUTES OUTPUT\n");
		
		for (int n = 1; n <= numCases; n++) {
			System.out.println("DATA SET  " + n + "\n");
			int numWarehouses = conIn.nextInt();
			int numLegs = conIn.nextInt();
			int numRequests = conIn.nextInt();
			
			for (int i = 0; i < numWarehouses; i++) {
				warehouseMap.put(conIn.next(), i);
			}
			
			for (int i = 0; i < numLegs; i++) {
				int source = warehouseMap.get(conIn.next());
				int dest = warehouseMap.get(conIn.next());
				graph[source][dest] = true;
				graph[dest][source] = true;
			}
			
			for (int i = 0; i < numRequests; i++) {
				int cost = conIn.nextInt();
				String a = conIn.next();
				String b = conIn.next();
				
				for (int j = 0; j < numWarehouses; j++) {
					wasVisited[j] = false;
				}
				
				int distance = bfs(warehouseMap.get(a), warehouseMap.get(b));
				
				if (distance == -1)
					System.out.println("NO SHIPMENT POSSIBLE");
				else
					System.out.println("$" + cost * distance * 100);
			}
			
			System.out.println();
			
			if (n != numCases) {
				warehouseMap.clear();
				for (int i = 0; i < numWarehouses; i++) {
					for (int j = 0; j < numWarehouses; j++) {
						graph[i][j] = false;
					}
				}	
			}
		}
		
		System.out.println("END OF OUTPUT");
	}
	
	public static int bfs(int source, int dest) {
		//--Will return legs traveled to get from source to destination.
		//--Will return -1 if no route is possible
		LinkedList<Leg> q = new LinkedList<Leg>();
		
		q.add(new Leg(source, 0));
		
		while (!q.isEmpty()) {
			Leg current = q.poll();
			if (current.node == dest)
				return current.distance;
		
			for (int i = 0; i < 31; i++) {
				if (graph[current.node][i] && !wasVisited[i]) {
					wasVisited[i] = true;
					q.add(new Leg(i, current.distance + 1));
				}
			}
		}
		
		return -1;
	}

}