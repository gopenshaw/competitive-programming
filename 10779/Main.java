import java.util.*;

class Edge {
	Vertex source;
	Vertex target;
	int capacity;
	int flow;
	Edge parent;
	Edge residual;

	public Edge(Vertex source, Vertex target, int capacity, boolean withResidual) {
		this.source = source;
		this.target = target;
		this.capacity = capacity;

		if (withResidual) {
			this.residual = new Edge(target, source, capacity, false);
			this.residual.residual = this;
			this.residual.flow = capacity;
		}
	}

	public void addFlow(int flow) {
		this.flow += flow;
		if (this.residual == null)
			return;

		this.residual.flow -= flow;
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
	//-plus a target_sticker vertex for each sticker,
	//-plus a source and target vertex
	final static int NUM_VERTEXES = (MAX_PEOPLE + 1) * MAX_STICKERS + 2;

	static Vertex[] v = new Vertex[NUM_VERTEXES];
	final static int SOURCE_VERTEX_INDEX = NUM_VERTEXES - 2;
	final static int TARGET_VERTEX_INDEX = NUM_VERTEXES - 1;

	static int[][] originalStickerCount = new int[MAX_PEOPLE][MAX_STICKERS];

	static Scanner input;

	static int numPeople;
	static int numStickers;

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

	static void getInput() {
		numPeople = input.nextInt();
		numStickers = input.nextInt();

		for (int i = 0; i < numPeople; i++) {
			int stickerCount = input.nextInt();
			for (int j = 0; j < stickerCount; j++) {
				int stickerNumber = input.nextInt();
				originalStickerCount[i][stickerNumber - 1]++;
			}
		}
	}

	static void buildGraph() {
		//--There is an infinite capacity edge from the source to all of bob's vertexes (no residual necessary)
		Vertex source = v[SOURCE_VERTEX_INDEX];
		for (int i = 0; i < numStickers; i++) {
			Vertex bob_i = v[i];
			Edge edge = new Edge(source, bob_i, Integer.MAX_VALUE, false);
			source.fromHere.add(edge);
		}

		//--The vertex for a person/sticker will is at index: personNumber * numStickers + stickerNumber
		//--Each target_sticker will be at index: numPeople * numStickers + stickerNumber 
		//--Note: sticker number is 0 indexed even though it is one indexed in the problem domain
		for (int i = 0; i < numPeople; i++) {
			for (int j = 0; j < numStickers; j++) {

				Vertex t_j = v[numPeople * numStickers + j];
				Vertex person_j = v[i * numStickers + i];
				int count = originalStickerCount[i][j];

				//--If they have none, they are willing to take stickers
				//--Make an edge of capacity one from the t_sticker to person_sticker
				if (count == 0) {
					//--but if this person is bob the edge should go to the target
					if (i == 0) {
						person_j = v[TARGET_VERTEX_INDEX];
					}
						
					Edge edge = new Edge(t_j, person_j, 1, true);
					t_j.fromHere.add(edge);
					person_j.fromHere.add(edge.residual);
				}
				//--If they have more than one, they are willing to give a sticker
				//--Make an edge from person_sticker to target_sticker of capacity (count - 1)
				else if (count > 1) {
					Edge edge = new Edge(person_j, t_j, count - 1, true);
					person_j.fromHere.add(edge);
					t_j.fromHere.add(edge.residual);
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
				updateFlow(finalEdge, minimumCapacity);
			}
		} while (finalEdge != null);

		for (Edge edge : v[SOURCE_VERTEX_INDEX].fromHere) {
			maxFlow += edge.flow;
		}
		
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

	static void updateFlow(Edge edge, int minimumCapacity) {
		Edge current = edge;
		while (current != null) {
			current.addFlow(minimumCapacity);

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
			if (originalStickerCount[0][i] != 0)
				count++;
		}

		return count;
	}

	static void tearDown() {
		//--It is not necessary to clean all vertexes,
		//  but I am too lazy to write the logic
		for (int i = 0; i < NUM_VERTEXES; i++) {
			v[i].fromHere.clear();
		}

		for (int i = 0; i < numPeople; i++) {
			for (int j = 0; j < numStickers; j++) {
				originalStickerCount[i][j] = 0;
			}
		}
	}
}