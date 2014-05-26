// 
// UVA #383 - Shipping Routes
// Garrett Openshaw
//

import java.util.*;

class Main {

	public static HashMap<String, Integer> warehouseMap = new HashMap<String, Integer>();
	public static boolean[][] graph = new boolean[31][31];

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();
		
		for (int n = 1; n <= numCases; n++) {
			int numWarehouses = conIn.nextInt();
			int numLegs = conIn.nextInt();
			int numRequests = conIn.nextInt();
			
			for (int i = 0; i < numWarehouses; i++) {
				warehouseMap.put(conIn.next(), i);
			}
			
			for (int i = 0; i < numLegs; i++) {
				String a = conIn.next();
				String b = conIn.next();
				graph[warehouseMap.get(a)][warehouseMap.get(b)] = true;
			}
			
			for (int i = 0; i < numRequests; i++) {
				int cost = conIn.nextInt();
				String a = conIn.next();
				String b = conIn.next();
				int distance = bfs(warehouseMap.get(a), warehouseMap.get(b));
			}
		}
	}
	
	public static int bfs(int source, int dest) {
		return -1;
	}

}