import java.util.*;

class Edge {
	Vertex source;
	Vertex target;
	int capacity;
	int flow;
	Edge parent;
	Edge residual;

	public Edge(Vertex source, Vertex target, int capacity) {
		this.source = source;
		this.target = target;
		this.capacity = capacity;
	}

	public void print() {
		System.out.printf("source - %d, target = %d, cap = %d, flow = %d\n", source.id, target.id, capacity, flow);
	}
}

class Vertex {
	int id;
	ArrayList<Edge> fromHere;

	public Vertex(int id) {
		this.id = id;
		fromHere = new ArrayList<Edge>();
	}
}

class Main {
	final static int MAX_PEOPLE = 10;
	final static int MAX_STICKERS = 25;

	//--need a vertex for each person/sticker combination,
	//-plus a target vertex for each sticker,
	//-plus an overall source and target vertex
	final static int NUM_VERTEXES = (MAX_PEOPLE + 1) * MAX_STICKERS + 2;

	static Vertex[] v = new Vertex[NUM_VERTEXES];
	final static int SOURCE_VERTEX_INDEX = NUM_VERTEXES - 2;
	final static int TARGET_VERTEX_INDEX = NUM_VERTEXES - 1;

	//--this will hold original stickers counts
	static int[][] originalStickers = new int[MAX_PEOPLE][MAX_STICKERS];

	static Scanner input;

	static int numPeople;
	static int numStickers;

	static void getInput() {
		numPeople = input.nextInt();
		numStickers = input.nextInt();

		for (int i = 0; i < numPeople; i++) {
			int stickerCount = input.nextInt();
			for (int j = 0; j < stickerCount; j++) {
				int stickerNumber = input.nextInt();
				originalStickers[i][stickerNumber - 1]++;
			}
		}
	}

	static void buildGraph() {
		//--there is an infinite capacity edge from the source to all of bobs vertexes (no residual necessary)
		Vertex source = v[SOURCE_VERTEX_INDEX];
		for (int i = 0; i < numStickers; i++) {
			Vertex bob_i = v[i];
			Edge edge = new Edge(source, bob_i, Integer.MAX_VALUE);
			source.fromHere.add(edge);

			// edge.print();
		}

		//--The vertex for a person/sticker will is at personNumber * numStickers + stickerNumber (sticker # is 0 indexed!)
		//--The "t" vertex for each sticker will be at index numPeople * numStickers + stickerNumber (sticker # is 0 indexed!)
		for (int i = 0; i < numPeople; i++) {
			for (int j = 0; j < numStickers; j++) {

				Vertex t_j = v[numPeople * numStickers + j];
				Vertex person_j = v[i * numStickers + i];
				int count = originalStickers[i][j];

				if (count == 0) {
					//--make an edge of capacity one from the "t" vertex of that sticker
					//-to the persons vertex for that sticker

					//--but if this person is bob the edge should go to the target
					if (i == 0)
						person_j = v[TARGET_VERTEX_INDEX];
					Edge edge = new Edge(t_j, person_j, 1);
					Edge residual = new Edge(person_j, t_j, 1);
					residual.flow = 1;

					edge.residual = residual;
					residual.residual = edge;

					t_j.fromHere.add(edge);
					person_j.fromHere.add(residual);

					// edge.print();
				}
				else if (count > 1) {
					//--make an edge of capacity (count - 1)
					//-from person_sticker to t_sticker
					Edge edge = new Edge(person_j, t_j, count - 1);
					Edge residual = new Edge(t_j, person_j, count - 1);
					residual.flow = count - 1;

					edge.residual = residual;
					residual.residual = edge;

					person_j.fromHere.add(edge);
					t_j.fromHere.add(residual);

					// edge.print();
				}
			}
		}
	}

	static int getMaxFlow() {
		int maxFlow = 0;
		Edge finalEdge = null;
		do {
			finalEdge = bfs();
			if (finalEdge != null) {
				int minimumCapacity = getMinimumCapacityOnPath(finalEdge);
				updateFlowAndResiduals(finalEdge, minimumCapacity);
			}
		} while (finalEdge != null);

		for (Edge edge : v[SOURCE_VERTEX_INDEX].fromHere) {
			maxFlow += edge.flow;
		}
		
		// System.out.println("max flow is " + maxFlow);
		return maxFlow;
	}

	static int getMinimumCapacityOnPath(Edge edge) {
		int minimumCapacity = Integer.MAX_VALUE;

		Edge current = edge;
		while (current != null) {
			int capacity = current.capacity;
			if (capacity < minimumCapacity)
				minimumCapacity = capacity;

			current = current.parent;
		}

		return minimumCapacity;
	}

	static void updateFlowAndResiduals(Edge edge, int minimumCapacity) {
		Edge current = edge;
		while (current != null) {
			current.flow += minimumCapacity;
			current.capacity -= minimumCapacity;

			if (current.residual != null) {
				current.residual.flow -= minimumCapacity;
				current.residual.capacity += minimumCapacity;
			}

			// current.print();
			current = current.parent;
		}
	}

	static Edge bfs() {
		Edge finalEdge = null;
		LinkedList<Edge> q = new LinkedList<Edge>();
		boolean visited[] = new boolean[NUM_VERTEXES];
		visited[SOURCE_VERTEX_INDEX] = true;

		for (Edge edge : v[SOURCE_VERTEX_INDEX].fromHere) {
			if (edge.flow < edge.capacity) {
				q.add(edge);
			}
		}

		while (!q.isEmpty()) {
			Edge current = q.poll();	
			if (current.target.id == TARGET_VERTEX_INDEX) {
				finalEdge = current;
				break;
			}
			visited[current.source.id] = true;
			
			for (Edge edge : current.target.fromHere) {
				if (!visited[edge.target.id]
					&& edge.flow < edge.capacity) {
					edge.parent = current;
					q.add(edge);
				}
			}
		}

		return finalEdge;
	}

	static int getBobsOriginalStickers() {
		int count = 0;

		for (int i = 0; i < numStickers; i++) {
			if (originalStickers[0][i] != 0)
				count++;
		}

		return count;
	}

	static void tearDown() {
		for (int i = 0; i < NUM_VERTEXES; i++) {
			v[i].fromHere.clear();
		}

		for (int i = 0; i < numPeople; i++) {
			for (int j = 0; j < numStickers; j++) {
				originalStickers[i][j] = 0;
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < NUM_VERTEXES; i++) {
			v[i] = new Vertex(i);
		}

		input = new Scanner(System.in);
		int numCases = input.nextInt();

		for (int i = 1; i <= numCases; i++) {
			getInput();
			buildGraph();
			int result = getMaxFlow() + getBobsOriginalStickers();
			System.out.println("Case #" + i + ": " +  result);
			tearDown();
		}
	}
}