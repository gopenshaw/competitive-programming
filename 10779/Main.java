import java.util.*;

class Edge {
	int source;
	int target;
	int capacity;
	int flow;
	Edge parent;
	Edge residual;

	public Edge(int capacity) {
		this.capacity = capacity;
	}
}

class Vertex {
	ArrayList<Edge> fromHere = new ArrayList<Edge>();
}

class Main {
	static Scanner input;
	static int numCases;
	static int numPeople;
	static int numStickers;
	static int numVertexes;
	static int sourceVertex;
	static int targetVertex;
	static Vertex[] vertexArray;
	static int[][] stickerCount;

	static void getInput() {
		numPeople = input.nextInt();
		numStickers = input.nextInt();

		for (int i = 0; i < numPeople; i++) {
			int numStickersForPerson = input.nextInt();
			for (int j = 0; j < numStickersForPerson; j++) {
				int stickerNumber = input.nextInt();
				stickerCount[i][j]++;
			}
		}
	}

	static void buildGraph() {

	}

	static void doMaxFlowAndPrintResult() {
		Edge finalEdge = bfs();
	}

	static Edge bfs() {
		Edge finalEdge = null;
		LinkedList<Edge> q = new LinkedList<Edge>();
		boolean visited[] = new boolean[numVertexes];
		visited[sourceVertex] = true;

		for (Edge edge : vertexArray[sourceVertex].fromHere) {
			if (edge.flow < edge.capacity) {
				q.add(edge);
			}
		}

		while (!q.isEmpty()) {
			Edge current = q.poll();	
			if (current.target == targetVertex) {
				finalEdge = current;
				break;	
			}
			visited[current.source] = true;
			
			for (Edge edge : vertexArray[current.target].fromHere) {
				if (!visited[edge.target]
					&& edge.flow < edge.capacity) {
					edge.parent = current;
					q.add(edge);
				}
			}
		}

		return finalEdge;
	}

	public static void main(String[] args) {
		input = new Scanner(System.in);
		numCases = input.nextInt();

		for (int i = 0; i < numCases; i++) {
			getInput();
			buildGraph();
			doMaxFlowAndPrintResult();
		}
	}
}