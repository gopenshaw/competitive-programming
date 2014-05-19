//--UVA #291 - House of Santa Clause

import java.util.*;

class Main {

	public static int[][] graph = new int[][] {
							{ 0, 0, 0, 0, 0, 0 }
							,{ 0, 0, 1, 1, 0, 1 }
							,{ 0, 1, 0, 1, 0, 1 }
							,{ 0, 1, 1, 0, 1, 1 }
							,{ 0, 0, 0, 1, 0, 1 }
							,{ 0, 1, 1, 1, 1, 0 } };
	public static LinkedList<Integer> result = new LinkedList<Integer>();

	public static void main(String[] args) {
		result.add(1);
		makeConnections(1);
	}
	
	public static void makeConnections(int source) {
		if (result.size() > 8)
			return;
	
		for (int target = 1; target <= 5; target++) {
			if (graph[source][target] == 1) {
				graph[source][target] = 0;
				graph[target][source] = 0;
				result.add(target);
				
				if (result.size() == 9) {
					for (Integer i : result)
						System.out.print(i);
					System.out.println();
				}
				
				makeConnections(target);
				
				graph[source][target] = 1;
				graph[target][source] = 1;
				result.removeLast();
			}
			
		}
	}
}