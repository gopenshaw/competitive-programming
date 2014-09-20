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


	//--We need a source and target vertex for Bob,
	//-plus one vertex for each person other than Bob,
	//-plus a target_sticker vertex for each sticker,
	final static int NUM_VERTEXES = MAX_PEOPLE + MAX_STICKERS + 1;

	static Vertex[] v = new Vertex[NUM_VERTEXES];
	final static int SOURCE_VERTEX_INDEX = 0;
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
		//--For each person, if they don't have a sticker, we make an edge from t_sticker
		//-to that person's vertex. If a person has more than one sticker, we make an edge
		//-from that person to t_sticker with a capacity of count - 1.
		//--The only exception is that edges from Bob will come from SOURCE and edges from
		//-Bob will go to the TARGET.

		//--Set up Bob's edges (no residual necessary)
		Vertex source = v[SOURCE_VERTEX_INDEX];
		Vertex target = v[TARGET_VERTEX_INDEX];
		for (int i = 0; i < numStickers; i++) {
			Vertex t_n = v[numPeople + i];
			int count = originalStickerCount[0][i];
			if (count == 0)
			{
				Edge edge = new Edge(t_n, target, 1, false);
				t_n.fromHere.add(edge);
			}
			else if (count > 1)
			{
				Edge edge = new Edge(source, t_n, count - 1, false);
				source.fromHere.add(edge);
			}
		}

		//--Set up all other edges (with residuals)
		for (int i = 1; i < numPeople; i++) {
			Vertex person = v[i];
			for (int j = 0; j < numStickers; j++)
			{
				Vertex t_n = v[numPeople + j];
				int count = originalStickerCount[i][j];
				if (count == 0)
				{
					Edge edge = new Edge(t_n, person, 1, true);
					t_n.fromHere.add(edge);
					person.fromHere.add(edge.residual);
				}
				else if (count > 1)
				{
					Edge edge = new Edge(person, t_n, count - 1, true);
					person.fromHere.add(edge);
					t_n.fromHere.add(edge.residual);
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